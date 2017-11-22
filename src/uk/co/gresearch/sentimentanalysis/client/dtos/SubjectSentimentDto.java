package uk.co.gresearch.sentimentanalysis.client.dtos;

public class SubjectSentimentDto {
    private String subject;
    private float sentiment;

    public SubjectSentimentDto(String subject, float sentiment) {
        this.subject = subject;
        this.sentiment = sentiment;
    }

    public String getSubject() {
        return subject;
    }

    public float getSentiment() {
        return sentiment;
    }
}
