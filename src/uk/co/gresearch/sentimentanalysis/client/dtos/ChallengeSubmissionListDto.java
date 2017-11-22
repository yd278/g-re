package uk.co.gresearch.sentimentanalysis.client.dtos;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public class ChallengeSubmissionListDto extends GenericJson {
    @Key
    private int challengeId;
    @Key
    private String challengeType;
    @Key
    private ChallengeResultDto[] submissions;

    public int getChallengeId() {
        return challengeId;
    }

    public String getChallengeType() {
        return challengeType;
    }

    public ChallengeResultDto[] getSubmissions() {
        return submissions;
    }
}

