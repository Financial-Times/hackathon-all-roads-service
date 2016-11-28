package com.ft.hackathon2016.allroads.fetchers;

import java.net.URI;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SuggestorApiClient {
  private static final Logger LOG = LoggerFactory.getLogger(SuggestorApiClient.class);
  
  private static final String NOVELTY = "http://www.ft.com/ontology/extraction/Novelty";
  
  private static final String RELEVANCE = "http://api.ft.com/scoringsystem/FT-RELEVANCE-SYSTEM";
  private static final String CONFIDENCE = "http://api.ft.com/scoringsystem/FT-CONFIDENCE-SYSTEM";
  
  private static final Comparator<Suggestion> SUGGESTION_COMPARATOR = (a, b) -> {
    return (int)Math.signum(score(a.getProvenances()) - score(b.getProvenances()));
  };
  
  private static double score(List<SuggestionProvenance> provenances) {
    double score = 0.0;
    for (SuggestionProvenance p : provenances) {
      double relevance = 0.0;
      double confidence = 0.0;
      for (Score s : p.getScores()) {
        switch (s.getScoringSystem()) {
          case RELEVANCE:
            relevance = s.getValue();
            break;
            
          case CONFIDENCE:
            confidence = s.getValue();
            break;
            
          default:
            break;
        }
      }
      
      double value = relevance * confidence;
      score = Math.max(score, value);
    }
    
    return score;
  }
  
  private final Client client;
  private final URI suggestorURI;
  
  
  public SuggestorApiClient(Client client, String host, int port) {
    this.client = client;
    
    UriBuilder builder = UriBuilder.fromPath("/suggest")
        .scheme("http").host(host).port(port);
    
    suggestorURI = builder.build();
  }
  
  public List<String> suggestConcepts(String text) {
    Invocation.Builder request = client.target(suggestorURI)
        .request()
        .header("Host", "concept-suggestion-api");
    
    Suggestions response = request.post(
        Entity.entity(Collections.singletonMap("body", text), MediaType.APPLICATION_JSON),
        Suggestions.class);
    
    List<Suggestion> suggestions = response.getSuggestions().stream()
      .filter(s -> !s.getThing().getTypes().contains(NOVELTY))
      .collect(Collectors.toList());
    
    Set<Suggestion> novelties = new LinkedHashSet<>(response.getSuggestions());
    novelties.removeAll(suggestions);
    
    Set<String> noveltyLabels = novelties.stream().map(s -> s.getThing().getPrefLabel()).collect(Collectors.toSet());
    LOG.info("removed novelties: {}", noveltyLabels);
    
    Collections.sort(suggestions, Collections.reverseOrder(SUGGESTION_COMPARATOR));
    Set<String> suggestionLabels = suggestions.stream().map(s -> s.getThing().getPrefLabel()).collect(Collectors.toSet());
    LOG.info("sorted suggestions: {}", suggestionLabels);
    
    return suggestions.stream()
        .map(s -> s.getThing().getId())
        .collect(Collectors.toList());
  }
}
