package com.ft.hackathon2016.allroads.service;

import com.ft.hackathon2016.allroads.config.AllRoadsConfiguration;
import com.ft.hackathon2016.allroads.fetchers.ContentApiClient;
import com.ft.hackathon2016.allroads.fetchers.ContentItemIdentifier;
import com.ft.hackathon2016.allroads.fetchers.SuggestorApiClient;
import com.ft.hackathon2016.allroads.model.Annotation;
import com.ft.hackathon2016.allroads.model.Article;
import com.ft.hackathon2016.allroads.model.SuggestedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.ClientBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SuggestedContentSvc {

  private static final Logger logger = LoggerFactory.getLogger(SuggestedContentSvc.class);
  private final ContentApiClient contentApiClient;
  private final SuggestorApiClient suggestorApiClient;
  private final AllRoadsConfiguration config;

  public SuggestedContentSvc (AllRoadsConfiguration config){
    this.config = config;
    contentApiClient = new ContentApiClient(config, ClientBuilder.newClient());
    suggestorApiClient = new SuggestorApiClient(ClientBuilder.newClient(), config.getSuggestorHost(), config.getSuggestorPort());
  }

  public List<SuggestedContent> getSuggestedContent(String inputText){

    String conceptId;
    if (config.isMockSuggestorApi()){
      conceptId = "2384fa7a-d514-3d6a-a0ea-3a711f66d0d8";
    }
    else{
      logger.warn("IN GETSUGGESTEDCONTENT, ABOUT TO CALL SUGGESTOR CLIENT");
      List<String> concepts = suggestorApiClient.suggestConcepts(inputText);
      logger.warn(String.format("FOUND %s CONCEPTS",concepts.size()));
      if (concepts.isEmpty()) {
        return Collections.emptyList();
      }
      String conceptUri = concepts.get(0);
      conceptId = conceptUri.replaceFirst(".+things/","");
    }

    List<ContentItemIdentifier> allContentItemIdentifiers = contentApiClient.getContentItemsByConcept(conceptId);
    logger.info(allContentItemIdentifiers.size() + " items found for that concept");
    // Only take 5 .. it could be a very long list
    List<ContentItemIdentifier> contentItemIdentifiers = allContentItemIdentifiers.subList(0,5);

    ArrayList<SuggestedContent> suggestedContents = new ArrayList<SuggestedContent>();
    for (int i = 0; i < contentItemIdentifiers.size(); i++){

      //String articleUri = "http://api.ft.com/enrichedcontent/3aa9db96-b25f-11e6-9c37-5787335499a0";
      String articleUri = contentItemIdentifiers.get(i).getEnrichedContentApiUrl();
      Article article = getArticle(articleUri);

      suggestedContents.add(new SuggestedContent(
              article.getTitle(),
              article.getWebUrl(),
              article.getPublicationDate(),
              article.getByline(),
              article.getStandfirst(),
              article.getAnnotation()
      ));

    }

    return suggestedContents;
  }



  private Article getArticle(String articleUri){
    return contentApiClient.getArticleByUri(articleUri);
  }

}