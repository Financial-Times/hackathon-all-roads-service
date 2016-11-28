package com.ft.hackathon2016.allroads.fetchers;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by itrend on 7/15/15.
 */
public class Score {
	public static final String SYSTEM_RELEVANCE = "http://api.ft.com/scoringsystem/FT-RELEVANCE-SYSTEM";
	public static final String SYSTEM_CONFIDENCE = "http://api.ft.com/scoringsystem/FT-CONFIDENCE-SYSTEM";

	private final String scoringSystem;
	private final double value;

	public Score(@JsonProperty("scoringSystem") String scoringSystem,
			@JsonProperty("value") double value) {
		this.scoringSystem = scoringSystem;
		this.value = value;
	}

	public String getScoringSystem() {
		return scoringSystem;
	}

	public double getValue() {
		return value;
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Score score = (Score) o;

		if (Double.compare(score.value, value) != 0)
			return false;
		return !(scoringSystem != null ? !scoringSystem.equals(score.scoringSystem) : score.scoringSystem != null);

	}

	@Override public int hashCode() {
		int result;
		long temp;
		result = scoringSystem != null ? scoringSystem.hashCode() : 0;
		temp = Double.doubleToLongBits(value);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override public String toString() {
		return "Score{" +
				"scoringSystem='" + scoringSystem + '\'' +
				", value=" + value +
				'}';
	}
}
