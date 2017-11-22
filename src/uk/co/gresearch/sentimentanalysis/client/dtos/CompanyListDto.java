package uk.co.gresearch.sentimentanalysis.client.dtos;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public class CompanyListDto extends GenericJson {
    @Key
    private CompanyDto[] companies;

    public CompanyDto[] getCompanies() {
        return companies;
    }
}
