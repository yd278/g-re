package uk.co.gresearch.sentimentanalysis.client;

import uk.co.gresearch.sentimentanalysis.client.dtos.ChallengeDto;
import uk.co.gresearch.sentimentanalysis.client.dtos.ChallengeTweetDto;
import uk.co.gresearch.sentimentanalysis.client.dtos.PerTweetChallengeSubmissionDto;
import uk.co.gresearch.sentimentanalysis.client.dtos.SubjectSentimentDto;

import java.util.HashMap;

public class PerTweetChallengeSolver {
    private final SentimentAnalyser sentimentAnalyser;

    public PerTweetChallengeSolver(SentimentAnalyser sentimentAnalyser) {
        this.sentimentAnalyser = sentimentAnalyser;
    }

    public PerTweetChallengeSubmissionDto solveChallenge(ChallengeDto challenge)
    {
        HashMap<Integer, SubjectSentimentDto[]> result = new HashMap<>();

        for (ChallengeTweetDto tweet : challenge.getTweets()) {
            SubjectSentimentDto[] sentiments = sentimentAnalyser.AnalyseTweet(tweet.getTweet());
            result.put(tweet.getId(), sentiments);
    }

        return new PerTweetChallengeSubmissionDto(challenge.getChallenge().getId(), result);
    }
}
