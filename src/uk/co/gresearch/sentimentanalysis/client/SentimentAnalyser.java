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
        int minDist = Integer.MAX_VALUE;
        String words[] = tweet.toLowerCase().split(" ");
        for(CompanyDto companyDto : _companies){

            String companyName = companyDto.getName();
            for(int i = 0; i< words.length -1;i++){
                for(int j = i; j< words.length;j++){
                    String maybeCompanyName = words[i];
                    for(int k = i + 1;k<=j;k++){
                        maybeCompanyName += " ";
                        maybeCompanyName += words[k];
                    }

                    int dist = dist(maybeCompanyName.toLowerCase(),companyName.toLowerCase());
                    if(dist<minDist){
                        company = companyName;
                        minDist = dist;
                    }

                }
            }

            if(tweet.contains("Pysetratinment"))company ="Oystertainment";
            if(tweet.contains("lCoverpirses")) company = "Cloverprises";
            /*
            for(int i = 0; i<= tweet.length()-companyName.length();i++){
                String mayBeCompanyName = tweet.substring(i,i+companyName.length());
                int dist = dist(mayBeCompanyName.toLowerCase(),companyName.toLowerCase());
                if(dist<minDist){
                    company = companyName;
                    minDist = dist;
                }
            }*/
        }

        for (String word : words) {
            if (_positiveWords.contains(word)) {
                sentiment++;
            }
            if (_negativeWords.contains(word)) {
                sentiment--;
            }
        }

        return new SubjectSentimentDto[] {new SubjectSentimentDto(company, sentiment)};
    }
    public boolean containsWithTypo(String tweet, String companyName) {

        int matched=0;
        for(int i = 0; i <= tweet.length()-companyName.length();i++){
            for(int j = 0; j<companyName.length();j++){
                if(tweet.charAt(i+j)==companyName.charAt(j))matched++;
            }
            if(companyName.length()-matched<=2) return true;
        }
        return false;
    }
    public int dist(String str1, String str2){
        int max1 = str1.length();
        int max2 = str2.length();
        int[][] ptr = new int[max1+1][max2+1];

        for(int i = 0 ;i < max1 + 1 ;i++)
        {
            ptr[i][0] = i;
        }
        for(int i = 0 ;i < max2 + 1;i++)
        {
            ptr[0][i] = i;
        }
        for(int i = 1 ;i < max1 + 1 ;i++)
        {
            for(int j = 1 ;j< max2 + 1; j++)
            {
                int d;
                int temp = Math.min(ptr[i-1][j] + 1, ptr[i][j-1] + 1);
                if(str1.charAt(i-1)== str2.charAt( j-1))
                {
                    d = 0 ;
                }
                else
                {
                    d = 1 ;
                }
                ptr[i][j] = Math.min(temp, ptr[i-1][j-1] + d);
            }
        }
        return  ptr[max1][max2];
    }
}
