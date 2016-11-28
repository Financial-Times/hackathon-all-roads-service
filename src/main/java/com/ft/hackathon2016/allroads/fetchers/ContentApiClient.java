package com.ft.hackathon2016.allroads.fetchers;

import com.ft.hackathon2016.allroads.config.AllRoadsConfiguration;
import com.ft.hackathon2016.allroads.model.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import java.util.List;

import static java.lang.String.format;

public class ContentApiClient {

    private static final Logger logger = LoggerFactory.getLogger(ContentApiClient.class);
    private final AllRoadsConfiguration config;
    private final Client client;

    public ContentApiClient(
            final AllRoadsConfiguration config,
            final Client client) {
        this.config = config;
        this.client = client;
    }

    public Article getArticleByUuid(final String articleId){
        logger.debug("Retrieving content details for article={}", articleId);
        UriBuilder uriBuilder = UriBuilder.fromPath(config.getContentApiUrl())
                .path("content")
                .path(articleId)
                ;
        final Response response = client.target(uriBuilder)
                .request()
                .header("x-api-key",config.getContentApiAuthKey())
                .get();

        if (response.getStatus() != 200) {
            final String responseBody = response.readEntity(String.class);
            throw new RuntimeException(
                    format(
                            "Request to ContentApi failed. Expected 200, but was %d response=%s",
                            response.getStatus(),
                            responseBody)
            );
        }

        final ContentApiGetArticleResponse contentApiResponse = response.readEntity(ContentApiGetArticleResponse.class);

        response.close();
        return contentApiResponseToArticle(contentApiResponse);
    }

    public Article getArticleByUri(final String articleUri){
        logger.debug("Retrieving content details for article={}", articleUri);
        final Response response = client.target(articleUri)
                .request()
                .header("x-api-key",config.getContentApiAuthKey())
                .get();

        if (response.getStatus() != 200) {
            final String responseBody = response.readEntity(String.class);
            throw new RuntimeException(
                    format(
                            "Request to ContentApi failed. Expected 200, but was %d response=%s",
                            response.getStatus(),
                            responseBody)
            );
        }

        final ContentApiGetArticleResponse contentApiResponse = response.readEntity(ContentApiGetArticleResponse.class);

        response.close();
        return contentApiResponseToArticle(contentApiResponse);
    }

    public List<ContentItemIdentifier> getContentItemsByConcept(final String conceptId){
        logger.debug("Retrieving content identifiers for conceptId={}", conceptId);
        UriBuilder uriBuilder = UriBuilder.fromPath(config.getContentApiUrl())
                .path("content")
                .queryParam("isAnnotatedBy",conceptId)

                ;
        final Response response = client.target(uriBuilder)
                .request()
                .header("x-api-key",config.getContentApiAuthKey())
                .get();

        logger.info("CONCEPT request is: " + uriBuilder.build());
        if (response.getStatus() != 200) {
            final String responseBody = response.readEntity(String.class);
            throw new RuntimeException(
                    format(
                            "Request to ContentApi failed. Expected 200, but was %d response=%s",
                            response.getStatus(),
                            responseBody)
            );
        }

        List<ContentItemIdentifier> contentItems = response.readEntity(new GenericType<List<ContentItemIdentifier>>() {
        });
        response.close();
        return contentItems;
    }

    private Article contentApiResponseToArticle(final ContentApiGetArticleResponse contentApiGetArticleResponse) {
       return new Article(
                    contentApiGetArticleResponse.getId(),
                    contentApiGetArticleResponse.getTitle(),
                    contentApiGetArticleResponse.getByline(),
                    contentApiGetArticleResponse.getPublishedDate(),
                    contentApiGetArticleResponse.getWebUrl(),
                    contentApiGetArticleResponse.getStandfirst()
       );

    }
}
