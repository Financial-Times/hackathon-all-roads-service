package com.ft.hackathon2016.allroads.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Annotation {
    private final String type;
    private final String prefLabel;
    private final String id;
    private final String predicate;

    public Annotation(@JsonProperty("type") final String type,
                      @JsonProperty("prefLabel") final String prefLabel,
                      @JsonProperty("id") final String id,
                      @JsonProperty("predicate") final String predicate
    ) {
        this.type = type;
        this.prefLabel = prefLabel;
        this.id = id;
        this.predicate = predicate;
    }

    public String getType() {
        return type;
    }

    public String getPrefLabel() {
        return prefLabel;
    }

    public String getId() {
        return id;
    }

    public String getPredicate() {
        return predicate;
    }
}
