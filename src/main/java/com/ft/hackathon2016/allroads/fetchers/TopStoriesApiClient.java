package com.ft.hackathon2016.allroads.fetchers;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.UriBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ft.hackathon2016.allroads.model.Annotation;
import com.ft.hackathon2016.allroads.model.Article;
import com.ft.hackathon2016.allroads.model.SuggestedContent;

public class TopStoriesApiClient {
  private static final Logger LOG = LoggerFactory.getLogger(TopStoriesApiClient.class);
  
  private final Client client;
  private final ContentApiClient contentApiClient;
  private final String apiKey;
  private final List<URI> topStoriesURIs;
  private final Map<String,Set<SuggestedContent>> topStoriesSuggestions = new HashMap<>();
  
  public TopStoriesApiClient(Client client, ContentApiClient contentApiClient, String apiBaseUrl, String apiKey, Set<UUID> topStoriesLists) {
    this.client = client;
    this.contentApiClient = contentApiClient;
    this.apiKey = apiKey;
    topStoriesURIs = new ArrayList<>();
    UriBuilder builder = UriBuilder.fromPath(apiBaseUrl)
        .path("lists/{uuid}");
    
    for (UUID u : topStoriesLists) {
      URI listURI = builder.build(u);
      topStoriesURIs.add(listURI);
    }
  }
  
  public void refresh() {
    for (URI listURI : topStoriesURIs) {
      ListResponse listResponse = client.target(listURI).request()
          .header("X-Api-Key", apiKey)
          .get(ListResponse.class);
      
      for (ListItem item : listResponse.getItems()) {
        Article article = contentApiClient.getArticleByUri(item.getApiUrl().replace("content", "enrichedcontent"));
        
        SuggestedContent suggestion = new SuggestedContent(article.getTitle(),
            article.getWebUrl(), article.getPublicationDate(), article.getByline(),
            article.getStandfirst(),
            ContentApiClient.getMostPertinentAnnotation(article.getTitle(), article.getWebUrl(), article.getAnnotations()));
        
        for (Annotation ann : article.getAnnotations()) {
          String annotationId = ann.getId();
          
          Set<SuggestedContent> matchingContent = topStoriesSuggestions.get(annotationId);
          if (matchingContent == null) {
            matchingContent = new HashSet<>();
            topStoriesSuggestions.put(annotationId, matchingContent);
          }
          
          matchingContent.add(suggestion);
        }
      }
    }
    
    LOG.info("there are {} concepts for top stories suggestions", topStoriesSuggestions.size());
  }
  
  public List<SuggestedContent> getTopStoriesMatchingConcepts(int max, String... conceptIds) {
    List<SuggestedContent> suggestions = new ArrayList<>();
    
    for (String id : conceptIds) {
      Set<SuggestedContent> forConcept = topStoriesSuggestions.get(id);
      if (forConcept != null) {
        suggestions.addAll(forConcept);
      }
    }
    
    if (suggestions.size() > max) {
      suggestions = suggestions.subList(0, max);
    }
    
    return suggestions;
  }
}
