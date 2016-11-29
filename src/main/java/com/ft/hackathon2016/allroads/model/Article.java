package com.ft.hackathon2016.allroads.model;

import java.util.Set;

import java.util.Collection;
import java.util.HashSet;

public class Article {

    private final String contentId;
    private final String title;
    private final String byline;
    private final String publicationDate;
    private final String webUrl;
    private final String standfirst;
    private final Annotation annotation;
    private final Set<Annotation> annotations;
    
    public Article(
           String contentId,
           String title,
           String byline,
           String publicationDate,
           String webUrl,
           String standfirst,
           Annotation annotation,
           Collection<Annotation> annotations) {
        this.contentId = contentId;
        this.title = title;
        this.byline = byline;
        this.publicationDate = publicationDate;
        this.webUrl = webUrl;
        this.standfirst = standfirst;
        this.annotation = annotation;
        this.annotations = new HashSet<>(annotations);
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

    public Annotation getAnnotation() {
        return annotation;
    }
    
    public Set<Annotation> getAnnotations() {
      return annotations;
    }
}
