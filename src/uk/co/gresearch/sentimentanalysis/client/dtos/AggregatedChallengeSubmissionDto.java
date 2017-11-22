package uk.co.gresearch.sentimentanalysis.client.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class AggregatedChallengeSubmissionDto {
    @JsonProperty
    private int challengeId;

    @JsonProperty
    private Map<String, Map<Integer, Float>> sentiments;

    public AggregatedChallengeSubmissionDto(int challengeId, Map<String, Map<Integer, Float>> sentiments) {
        this.challengeId = challengeId;
        this.sentiments = sentiments;
    }
}
