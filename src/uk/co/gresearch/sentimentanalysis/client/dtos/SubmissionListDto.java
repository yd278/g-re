package uk.co.gresearch.sentimentanalysis.client.dtos;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public class SubmissionListDto extends GenericJson {
    @Key
    ChallengeSubmissionListDto[] results;

    public ChallengeSubmissionListDto[] getResults() {
        return results;
    }
}
