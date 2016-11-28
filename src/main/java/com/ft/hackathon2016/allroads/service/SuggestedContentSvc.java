package com.ft.hackathon2016.allroads.service;

import com.ft.hackathon2016.allroads.config.AllRoadsConfiguration;
import com.ft.hackathon2016.allroads.fetchers.ContentApiClient;
import com.ft.hackathon2016.allroads.fetchers.ContentItemIdentifier;
import com.ft.hackathon2016.allroads.fetchers.SuggestorApiClient;
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

  public SuggestedContentSvc (AllRoadsConfiguration config){
    contentApiClient = new ContentApiClient(config, ClientBuilder.newClient());
    suggestorApiClient = new SuggestorApiClient(ClientBuilder.newClient(), config.getSuggestorHost(), config.getSuggestorPort());
  }

  public List<SuggestedContent> getSuggestedContent(String inputText){
    List<String> concepts = suggestorApiClient.suggestConcepts(inputText);
    if (concepts.isEmpty()) {
      return Collections.emptyList();
    }

    String conceptUri = concepts.get(0);
//        String conceptUri = "http://api.ft.com/things/2384fa7a-d514-3d6a-a0ea-3a711f66d0d8";
    String conceptId = conceptUri.replaceFirst(".+things/","");

    List<ContentItemIdentifier> allContentItemIdentifiers = contentApiClient.getContentItemsByConcept(conceptId);
    logger.info(allContentItemIdentifiers.size() + "items found for taht concept");
    // Only take 5 .. it could be a very long list
    List<ContentItemIdentifier> contentItemIdentifiers = allContentItemIdentifiers.subList(0,4);

    ArrayList<SuggestedContent> suggestedContents = new ArrayList<SuggestedContent>();
    for (int i = 0; i < contentItemIdentifiers.size(); i++){

      //String articleUri = "http://api.ft.com/content/3aa9db96-b25f-11e6-9c37-5787335499a0";
      String articleUri = contentItemIdentifiers.get(i).getApiUrl();
      Article article = getArticle(articleUri);

      suggestedContents.add(new SuggestedContent(
              article.getTitle(),
              article.getWebUrl(),
              article.getPublicationDate(),
              article.getByline(),
              article.getStandfirst()
      ));

    }

    return suggestedContents;
  }

  private Article getArticle(String articleUri){
    return contentApiClient.getArticleByUri(articleUri);
  }

}