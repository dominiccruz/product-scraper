package com.jsainsburys.core.product.detail;

import lombok.Getter;

@Getter
public class ProductNutrition {

    private final Integer nutrition;

    public ProductNutrition(Integer nutrition) {
        this.nutrition = nutrition;
    }

    /**
     * Creates a valid Product Nutrition Object
     * throws {@link NumberFormatException}
     *
     * @param nutrition
     */
    public ProductNutrition(String nutrition) {
        this.nutrition = Integer.parseInt(nutrition);
    }

}