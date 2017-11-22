package uk.co.gresearch.sentimentanalysis.client.dtos;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public class ChallengeResultDto extends GenericJson {
    @Key
    private int submissionId;
    @Key
    private int mark;

    public int getSubmissionId() {
        return submissionId;
    }

    public int getMark() {
        return mark;
    }
}
