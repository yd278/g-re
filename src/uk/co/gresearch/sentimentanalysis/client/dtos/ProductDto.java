package uk.co.gresearch.sentimentanalysis.client.dtos;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public class ProductDto extends GenericJson {
    @Key
    private String name;

    @Key
    private String productType;

    public String getName() {
        return name;
    }

    public String getProductType() {
        return productType;
    }
}
