package uk.co.gresearch.sentimentanalysis.client;

import uk.co.gresearch.sentimentanalysis.client.dtos.AggregatedChallengeSubmissionDto;
import uk.co.gresearch.sentimentanalysis.client.dtos.ChallengeDto;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AggregatedChallengeSolver {
    private final SentimentAnalyser sentimentAnalyser;

    private final Random random = new Random();

    public AggregatedChallengeSolver(SentimentAnalyser sentimentAnalyser) {
        this.sentimentAnalyser = sentimentAnalyser;
    }

    public AggregatedChallengeSubmissionDto solveChallenge(ChallengeDto challenge) {
        HashMap<String, Map<Integer, Float>> sentiments = new HashMap<>();

        Integer minTime = Arrays.stream(challenge.getTweets()).map(dto -> dto.getTime()).min(Integer::compare).get();
        Integer maxTime = Arrays.stream(challenge.getTweets()).map(dto -> dto.getTime()).max(Integer::compare).get();

        String[] knownSubjects = new String[]{"Motionmart", "Spherebank", "Purpleworth"};
        for (String subject : knownSubjects) {
            sentiments.put(subject, new HashMap<>());
            for (int time = minTime; time < maxTime; time++) {
                sentiments.get(subject).put(time, NextFloat(-1, 1));
            }
        }


        return new AggregatedChallengeSubmissionDto(challenge.getChallenge().getId(),  sentiments);
    }

    private float NextFloat(float min, float max) {
        return random.nextFloat() * (max - min) + min;
    }
}
