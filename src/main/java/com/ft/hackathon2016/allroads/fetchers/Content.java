package com.ft.hackathon2016.allroads.fetchers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Content {

    private String body;
    private String bodyXML;

    public Content(@JsonProperty("body") String body,
                   @JsonProperty("bodyXML") String bodyXML) {
        this.body = body;
        this.bodyXML = bodyXML;
    }

    public String getBody() {
        return body;
    }

    public String getBodyXML() {
        return bodyXML;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Content)) return false;

        Content content = (Content) o;

        if (body != null ? !body.equals(content.body) : content.body != null) return false;
        if (bodyXML != null ? !bodyXML.equals(content.bodyXML) : content.bodyXML != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = body != null ? body.hashCode() : 0;
        result = 31 * result + (bodyXML != null ? bodyXML.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Content{" +
                "body='" + body + '\'' +
                ", bodyXML='" + bodyXML + '\'' +
                '}';
    }
    
    public boolean hasNonBlankBody() {
        return isNotBlank(body) || isNotBlank(bodyXML);
    }

}
