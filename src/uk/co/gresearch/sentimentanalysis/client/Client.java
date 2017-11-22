package uk.co.gresearch.sentimentanalysis.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.co.gresearch.sentimentanalysis.client.dtos.*;

import java.io.IOException;

public class Client {
    private static final Logger logger = LogManager.getLogger(Client.class);

    private final WebHandler webHandler;

    private final PerTweetChallengeSolver perTweetChallengeSolver;

    private final AggregatedChallengeSolver aggregatedChallengeSolver;

    public Client(WebHandler webHandler) throws IOException {
        this.webHandler = webHandler;
        SentimentAnalyser sentimentAnalyser = new SentimentAnalyser(webHandler);
        perTweetChallengeSolver = new PerTweetChallengeSolver(sentimentAnalyser);
        aggregatedChallengeSolver = new AggregatedChallengeSolver(sentimentAnalyser);
    }

    public void run() throws IOException {
        logger.info("Getting Challenge list");
        ChallengeListDto challenges = webHandler.getChallenges();
        logger.info("There are {} challenges", challenges.getChallenges().length);

        for (ChallengeInfoDto challengeInfo : challenges.getChallenges()) {
            logger.info("Solving challenge {} - {}", challengeInfo.getId(), challengeInfo.getChallengeType());
            ChallengeDto challenge = webHandler.getChallenge(challengeInfo.getId());

            switch (challenge.getChallenge().getChallengeType().toLowerCase()) {
                case "pertweet":
                    handlePerTweetChallenge(challenge);
                    break;
                case "aggregated":
                    handleAggregatedChallenge(challenge);
                    break;
                default:
                    logger.warn("Encountered unknown challenge type '{}'", challenge.getChallenge().getChallengeType());
                    break;
            }
        }
    }

    private void handlePerTweetChallenge(ChallengeDto challenge) throws IOException {
        PerTweetChallengeSubmissionDto submission = perTweetChallengeSolver.solveChallenge(challenge);
        ChallengeResultDto result = webHandler.postSubmission(submission);
        logger.info("Got {} correct", result.getMark());
    }

    private void handleAggregatedChallenge(ChallengeDto challenge) throws IOException {
        AggregatedChallengeSubmissionDto submission = aggregatedChallengeSolver.solveChallenge(challenge);
        ChallengeResultDto result = webHandler.postSubmission(submission);
        logger.info("Got {} as a mark", result.getMark());
    }
}
