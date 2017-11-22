package uk.co.gresearch.sentimentanalysis.client;

import uk.co.gresearch.sentimentanalysis.client.dtos.CompanyDto;
import uk.co.gresearch.sentimentanalysis.client.dtos.SubjectSentimentDto;

import java.io.IOException;
import java.util.Set;

public class SentimentAnalyser {
    private final Set<String> _negativeWords;

    private final Set<String> _neutralWords;

    private final Set<String> _positiveWords;

    private final CompanyDto[] _companies;

    public SentimentAnalyser(WebHandler webHandler) throws IOException {
        _negativeWords = webHandler.getNegativeWords().getWords();
        _neutralWords = webHandler.getNeutralWords().getWords();
        _positiveWords = webHandler.getPositiveWords().getWords();
        _companies = webHandler.getCompanyInfo().getCompanies();
    }

    public SubjectSentimentDto[] AnalyseTweet(String tweet) {
        int sentiment = 0;
        String company = "Motionmart";
        for(CompanyDto companyDto : _companies){
            if(tweet.toLowerCase().contains(companyDto.getName().toLowerCase())){
                company = companyDto.getName();
            }
        }

        for (String word : tweet.toLowerCase().split(" ")) {
            if (_positiveWords.contains(word)) {
                sentiment++;
            }
            if (_negativeWords.contains(word)) {
                sentiment--;
            }
        }

        return new SubjectSentimentDto[] {new SubjectSentimentDto(company, sentiment)};
    }
}
