package uk.co.gresearch.sentimentanalysis.client.dtos;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public class ChallengeDto extends GenericJson {
    @Key
    private ChallengeInfoDto challenge;
    @Key
    private ChallengeTweetDto[] tweets;

    public ChallengeInfoDto getChallenge() {
        return challenge;
    }

    public ChallengeTweetDto[] getTweets() {
        return tweets;
    }
}
