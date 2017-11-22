package uk.co.gresearch.sentimentanalysis.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.co.gresearch.sentimentanalysis.client.dtos.*;

import java.io.IOException;

public class WebHandler {
    private static final Logger logger = LogManager.getLogger(WebHandler.class);

    private final String serverUrl;

    private final String apiKey;

    private final HttpRequestFactory requestFactory;

    private final JacksonFactory jsonFactory;

    public WebHandler(String serverUrl, String apiKey) {
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalArgumentException("Set your ApiKey in App.java! Find it on https://devrecruitmentchallenge.com/account");
        }
        this.serverUrl = serverUrl;
        this.apiKey = apiKey;

        jsonFactory = new JacksonFactory();
        HttpTransport transport = new NetHttpTransport();
        requestFactory = transport.createRequestFactory(request -> request.setParser(new JsonObjectParser(jsonFactory)));
    }

    public ChallengeListDto getChallenges() throws IOException {
        return getRequest("/api/challenges", ChallengeListDto.class);
    }

    public ChallengeDto getChallenge(int challengeId) throws IOException {
        return getRequest("/api/challenges/" + challengeId, ChallengeDto.class);
    }

    public SubmissionListDto getSubmissions() throws IOException {
        return getRequest("/api/submissions", SubmissionListDto.class);
    }

    public CompanyListDto getCompanyInfo() throws IOException {
        return getRequest("/api/world/companies", CompanyListDto.class);
    }

    public WordListDto getPositiveWords() throws IOException {
        return getRequest("/api/words/positive", WordListDto.class);
    }

    public WordListDto getNeutralWords() throws IOException {
        return getRequest("/api/words/neutral", WordListDto.class);
    }

    public WordListDto getNegativeWords() throws IOException {
        return getRequest("/api/words/negative", WordListDto.class);
    }

    public ChallengeResultDto postSubmission(PerTweetChallengeSubmissionDto submission) throws IOException {
        return postRequest("/api/submissions/pertweet", submission, ChallengeResultDto.class);
    }

    public ChallengeResultDto postSubmission(AggregatedChallengeSubmissionDto submission) throws IOException {
        return postRequest("/api/submissions/aggregated", submission, ChallengeResultDto.class);
    }

    private <T> T getRequest(String url, Class<T> responseType) throws IOException {
        GenericUrl genericUrl = new GenericUrl(serverUrl + url);
        HttpRequest request = requestFactory.buildGetRequest(genericUrl);
        request.getHeaders().setAuthorization("ApiKey " + apiKey);
        request.getHeaders().setAcceptEncoding("gzip");
        HttpResponse response = request.execute();
        logger.info("GET {} {}", response.getStatusCode(), url);
        return response.parseAs(responseType);
    }

    private <T> T postRequest(String url, Object bodyToSerialise, Class<T> responseType) throws IOException {
        GenericUrl genericUrl = new GenericUrl(serverUrl + url);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(bodyToSerialise);
        ByteArrayContent content = ByteArrayContent.fromString("application/json", json);
        HttpRequest request = requestFactory.buildPostRequest(genericUrl, content);
        request.getHeaders().setAuthorization("ApiKey " + apiKey);
        HttpResponse response = request.execute();
        logger.info("POST {} {}", response.getStatusCode(), url);
        return response.parseAs(responseType);
    }
}
