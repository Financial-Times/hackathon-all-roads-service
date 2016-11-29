package com.ft.hackathon2016.allroads.fetchers;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListResponse {
  private final Set<ListItem> items;

  public ListResponse(@JsonProperty("items") Collection<? extends ListItem> items) {
      this.items = new LinkedHashSet<>(items);
  }

  @JsonProperty
  @NotNull
  public Set<ListItem> getItems() {
      return items;
  }
}
