package com.jsainsburys.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"gross", "vat"})
public class TotalDto {

    @JsonProperty("gross")
    private BigDecimal gross;

    @JsonProperty("vat")
    private BigDecimal vat;
}
