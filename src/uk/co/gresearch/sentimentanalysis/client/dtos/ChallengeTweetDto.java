package uk.co.gresearch.sentimentanalysis.client.dtos;

import com.google.api.client.util.Key;

public class ChallengeTweetDto {
    @Key
    private int id;
    @Key
    private int time;
    @Key
    private String source;
    @Key
    private String tweet;

    public int getId() {
        return id;
    }

    public int getTime() {
        return time;
    }

    public String getSource() { return source; }

    public String getTweet() {
        return tweet;
    }
}
