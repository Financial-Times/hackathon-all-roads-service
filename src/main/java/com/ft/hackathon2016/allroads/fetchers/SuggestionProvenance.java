package com.ft.hackathon2016.allroads.fetchers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by itrend on 7/17/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuggestionProvenance {
	private final List<Score> scores;
	private String atTime;
	private final String agentRole;

	public SuggestionProvenance(@JsonProperty("scores") List<Score> scores,
			@JsonProperty("atTime") String atTime,
			@JsonProperty("agentRole") String agentRole) {
		this.scores = scores;
		this.atTime = atTime;
		this.agentRole = agentRole;
	}

	public List<Score> getScores() {
		return scores;
	}

	public String getAtTime() {
		return atTime;
	}

	public String getAgentRole() {
		return agentRole;
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		SuggestionProvenance that = (SuggestionProvenance) o;

		if (scores != null ? !scores.equals(that.scores) : that.scores != null)
			return false;
		if (atTime != null ? !atTime.equals(that.atTime) : that.atTime != null)
			return false;
		return !(agentRole != null ? !agentRole.equals(that.agentRole) : that.agentRole != null);

	}

	@Override public int hashCode() {
		int result = scores != null ? scores.hashCode() : 0;
		result = 31 * result + (atTime != null ? atTime.hashCode() : 0);
		result = 31 * result + (agentRole != null ? agentRole.hashCode() : 0);
		return result;
	}

	@Override public String toString() {
		return "SuggestionProvenance{" +
				"scores=" + scores +
				", atTime=" + atTime +
				", agentRole='" + agentRole + '\'' +
				'}';
	}
}
