package com.jsainsburys.core.product.detail;

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

    public Integer getNutrition() {
        return nutrition;
    }
}