package uk.co.gresearch.sentimentanalysis.client.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class PerTweetChallengeSubmissionDto {
    @JsonProperty
    private final int challengeId;
    @JsonProperty
    private final Map<Integer, SubjectSentimentDto[]> perTweetSentiment;

    public PerTweetChallengeSubmissionDto(int challengeId, HashMap<Integer, SubjectSentimentDto[]> perTweetSentiment) {
        this.challengeId = challengeId;
        this.perTweetSentiment = perTweetSentiment;
    }
}
