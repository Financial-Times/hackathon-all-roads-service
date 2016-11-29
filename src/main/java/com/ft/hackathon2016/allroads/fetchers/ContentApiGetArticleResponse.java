package com.ft.hackathon2016.allroads.fetchers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ft.hackathon2016.allroads.model.Annotation;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentApiGetArticleResponse {

    private final String id;
    private final String title;
    private final String byline;
    private final String publishedDate;
    private final String webUrl;
    private final String standfirst;
    private final List<Annotation> annotations;

    public ContentApiGetArticleResponse(
            @JsonProperty("id") final String id,
            @JsonProperty("title") final String title,
            @JsonProperty("byline") final String byline,
            @JsonProperty("publishedDate") final String publishedDate,
            @JsonProperty("webUrl") final String webUrl,
            @JsonProperty("standfirst") final String standfirst,
            @JsonProperty("annotations") final List<Annotation> annotations
            ) {

        this.id = id;
        this.title = title;
        this.byline = byline;
        this.publishedDate = publishedDate;
        this.webUrl = webUrl;
        this.standfirst = standfirst;
        this.annotations = annotations;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getByline() {
        return byline;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getStandfirst() {
        return standfirst;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }
}
