package uk.co.gresearch.sentimentanalysis.client.dtos;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WordListDto extends GenericJson {
    @Key
    private String[] words;

    public Set<String> getWords() {
        return new HashSet<String>(Arrays.asList(words));
    }
}
