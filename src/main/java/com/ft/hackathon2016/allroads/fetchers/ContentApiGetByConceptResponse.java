package com.ft.hackathon2016.allroads.fetchers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentApiGetByConceptResponse {

    private final List<ContentItemIdentifier> content;

    public ContentApiGetByConceptResponse(@JsonProperty("content") final List<ContentItemIdentifier> contentItemIdentifiers) {
        this.content = contentItemIdentifiers;
    }

    public List<ContentItemIdentifier> getContent() {
        return content;
    }

}
