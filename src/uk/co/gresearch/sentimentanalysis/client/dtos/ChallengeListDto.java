package uk.co.gresearch.sentimentanalysis.client.dtos;

import com.google.api.client.util.Key;

public class ChallengeListDto {
    @Key
    private ChallengeInfoDto[] challenges;

    public ChallengeInfoDto[] getChallenges() {
        return challenges;
    }
}
