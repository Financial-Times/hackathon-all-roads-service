package com.ft.hackathon2016.allroads.fetchers;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.Set;

/**
 * Created by itrend on 7/15/15.
 */
public class Thing {
	private final String id;
	private final String prefLabel;
	private final Set<String> types;

	public Thing(@JsonProperty("id") String id,
			@JsonProperty("prefLabel") String prefLabel,
			@JsonProperty("types") Set<String> types) {
		this.id = Objects.requireNonNull(id);
		this.prefLabel = prefLabel;
		this.types = types;
	}

	public String getId() {
		return id;
	}

	public String getPrefLabel() {
		return prefLabel;
	}

	public Set<String> getTypes() {
		return types;
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Thing thing = (Thing) o;
		return id.equals(thing.id);
	}

	@Override public int hashCode() {
		return id.hashCode();
	}

	@Override public String toString() {
		return "Thing{" +
				"id='" + id + '\'' +
				", prefLabel='" + prefLabel + '\'' +
				", types=" + types +
				'}';
	}
}
