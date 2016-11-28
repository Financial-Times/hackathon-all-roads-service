package com.ft.hackathon2016.allroads.fetchers;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Created by itrend on 7/15/15.
 */
public class Suggestion {
	private final Thing thing;
	private final List<SuggestionProvenance> provenances;

	public Suggestion(@JsonProperty("thing") Thing thing,
			@JsonProperty("provenances") List<SuggestionProvenance> provenances) {
		this.thing = Objects.requireNonNull(thing);
		this.provenances = provenances == null ? Collections.<SuggestionProvenance>emptyList()
				: Collections.unmodifiableList(provenances);
	}

	public Thing getThing() {
		return thing;
	}

	public List<SuggestionProvenance> getProvenances() {
		return provenances;
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Suggestion that = (Suggestion) o;
		return thing.equals(that.thing);
	}

	@Override public int hashCode() {
		return thing.hashCode();
	}

	@Override public String toString() {
		return "Suggestion{" +
				"thing=" + thing +
				", provenances=" + provenances +
				'}';
	}

	public static Builder builder() { return new Builder(); }

	public static class Builder {
		private String id;
		private String label;
		private Set<String> types;
		private List<SuggestionProvenance> provenances = new ArrayList<>();

		public Builder withId(String id) {
			this.id = id;
			return this;
		}

		public Builder withLabel(String label) {
			this.label = label;
			return this;
		}

		public Builder withTypes(String... types) {
			return withTypes(Arrays.asList(types));
		}

		public Builder withTypes(Collection<String> types) {
			this.types = new HashSet<>(types);
			return this;
		}

		public Builder withProvenance(Double relevanceScore, Double confidenceScore, String agentRole) {
			return withProvenance(relevanceScore, confidenceScore, DateTime.now(DateTimeZone.UTC).toString(), agentRole);
		}

		public Builder withProvenance(Double relevanceScore, Double confidenceScore, String atTime, String agentRole) {
			provenances.add(new SuggestionProvenance(buildScores(relevanceScore, confidenceScore), atTime, agentRole));
			return this;
		}

		public Suggestion build() {
			return new Suggestion(new Thing(id, label, types), provenances);
		}

		private List<Score> buildScores(Double relevanceScore, Double confidenceScore) {
			List<Score> scores = new ArrayList<>();
			addScore(scores, Score.SYSTEM_RELEVANCE, relevanceScore);
			addScore(scores, Score.SYSTEM_CONFIDENCE, confidenceScore);
			return scores;
		}

		private void addScore(List<Score> scores, String system, Double value) {
			if (value != null) {
				scores.add(new Score(system, value));
			}
		}
	}

	public static Suggestion withId(String id) {
		return new Suggestion(new Thing(id, "", Collections.<String>emptySet()), Collections.<SuggestionProvenance>emptyList());
	}

}
