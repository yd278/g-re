package uk.co.gresearch.sentimentanalysis.client.dtos;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public class ChallengeInfoDto extends GenericJson {
    @Key
    private int id;
    @Key
    private String challengeType;

    public int getId() {
        return id;
    }

    public String getChallengeType() {
        return challengeType;
    }
}

