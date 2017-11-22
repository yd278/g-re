package uk.co.gresearch.sentimentanalysis.client;

import org.junit.Assert;
import org.junit.Test;
import uk.co.gresearch.sentimentanalysis.client.dtos.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class IntegrationTest {
    private static WebHandler CreateWebHandler() {
        return new WebHandler("http://localhost:5700",
                "4fa85fe8-7d69-43b5-b230-43b805444f92");
    }

    @Test
    public void shouldHaveAtLeastOneSubmissionForEachChallengeWhenClientIsRun() throws IOException {
        WebHandler webHandler = CreateWebHandler();
        SubmissionListDto submissions = webHandler.getSubmissions();
        // Ensure that we don't get fooled by submissions that already exist
        Integer maxSubmissionId = Arrays.stream(submissions.getResults())
                .map(listDto -> Arrays.stream(listDto.getSubmissions())
                        .map(challengeResultDto -> challengeResultDto.getSubmissionId())
                        .max(Integer::compare).get())
                .max(Integer::compare).orElse(0);

        Client client = new Client(webHandler);
        client.run();

        ChallengeListDto challenges = webHandler.getChallenges();
        submissions = webHandler.getSubmissions();
        ArrayList<String> errors = new ArrayList<>();
        for (ChallengeInfoDto challenge : challenges.getChallenges()) {
            long recentSubmissionsCount = Arrays.stream(submissions.getResults())
                    .filter(listDto -> listDto.getChallengeId() == challenge.getId())
                    .flatMap(x -> Arrays.stream(x.getSubmissions()))
                    .filter(x -> x.getSubmissionId() > maxSubmissionId)
                    .count();
            if (recentSubmissionsCount == 0) {
                String error = "There are no submissions for challenge #" + challenge.getId();
                errors.add(error);
                System.out.println(error);
            }
        }

        Assert.assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldFindApexiInCompanyInfo() throws IOException {
        WebHandler webHandler = CreateWebHandler();
        CompanyListDto companies = webHandler.getCompanyInfo();
        Optional<CompanyDto> maybeApexi = Arrays.stream(companies.getCompanies())
                .filter(c -> c.getName().equals("Apexi")
                        && c.getIndustry().equals("Car Manufacturer")
                        && c.getTicker().equals("APEX")
                        && c.getProducts().length == 2
                        && c.getProducts()[0].getName().equals("Overture")
                        && c.getProducts()[0].getProductType().equals("Car")
                        && c.getProducts()[1].getName().equals("Encore")
                        && c.getProducts()[1].getProductType().equals("Car")).findFirst();

        Assert.assertTrue(maybeApexi.isPresent());
    }

    @Test
    public void shouldGetWordLists() throws IOException {
        WebHandler webHandler = CreateWebHandler();

        WordListDto words = webHandler.getPositiveWords();
        Assert.assertTrue(words.getWords().contains("good"));

        words = webHandler.getNeutralWords();
        Assert.assertTrue(words.getWords().contains("ok"));

        words = webHandler.getNegativeWords();
        Assert.assertTrue(words.getWords().contains("terrible"));
    }
}
