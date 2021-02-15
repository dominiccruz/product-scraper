package com.jsainsburys.schema;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "title",
        "kcal_per_100g",
        "unit_price",
        "description"
})
public class ProductDto {

    @JsonProperty("title")
    private String title;

    @JsonProperty("kcal_per_100g")
    private Integer kcalPer100g;

    @JsonProperty("unit_price")
    private BigDecimal unitPrice;

    @JsonProperty("description")
    private String description;

}
