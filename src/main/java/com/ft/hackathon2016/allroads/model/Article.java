package com.ft.hackathon2016.allroads.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Article {

    private final String contentId;
    private final String title;
    private final String byline;
    private final String publicationDate;
    private final String webUrl;
    private final String standfirst;

    public Article(
           String contentId,
           String title,
           String byline,
           String publicationDate,
           String webUrl,
           String standfirst) {
        this.contentId = contentId;
        this.title = title;
        this.byline = byline;
        this.publicationDate = publicationDate;
        this.webUrl = webUrl;
        this.standfirst = standfirst;
    }

    public String getContentId() {
        return contentId;
    }

    public String getTitle() {
        return title;
    }

    public String getByline() {
        return byline;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getStandfirst() {
        return standfirst;
    }
}
