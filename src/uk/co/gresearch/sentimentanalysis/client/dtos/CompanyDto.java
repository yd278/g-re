package uk.co.gresearch.sentimentanalysis.client.dtos;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public class CompanyDto extends GenericJson {
    @Key
    private String name;

    @Key
    private String ticker;

    @Key
    private String industry;

    @Key
    private ProductDto[] products;

    public ProductDto[] getProducts() {
        return products;
    }

    public String getIndustry() {
        return industry;
    }

    public String getTicker() {
        return ticker;
    }

    public String getName() {
        return name;
    }
}

