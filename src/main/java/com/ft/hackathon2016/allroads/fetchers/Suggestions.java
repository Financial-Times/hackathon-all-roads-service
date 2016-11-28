package com.ft.hackathon2016.allroads.fetchers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableSet;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Set;

public class Suggestions {

	private final Set<Suggestion> suggestions;

	public Suggestions(@JsonProperty("suggestions") Collection<? extends Suggestion> suggestions) {
		this.suggestions = ImmutableSet.copyOf(suggestions);
	}

	@JsonProperty
	@NotNull
	public Set<Suggestion> getSuggestions() {
		return this.suggestions;
	}
}
