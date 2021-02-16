package com.jsainsburys.parser.productsdetailpage;

import com.jsainsburys.core.product.detail.Money;
import com.jsainsburys.core.product.detail.ProductNutrition;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

public class ProductDetailPageNutritionParserTest {

    @Test
    public void getProductNutrition_firstSelectorIsUsed() throws IOException {
        //Arrange
        String nutritionSelector = "selector";

        String subSelector1 = "subSelector1";
        String subSelector2 = "subSelector1";

        List<String> subSelectors = new ArrayList<>();
        subSelectors.add(subSelector1);
        subSelectors.add(subSelector2);

        Elements nutritionElement = mock(Elements.class);
        when(nutritionElement.text()).thenReturn("10kcal");

        Elements productNutritionElement = mock(Elements.class);
        when(productNutritionElement.select(subSelector1)).thenReturn(nutritionElement);

        Document mockDocument = mock(Document.class);
        when(mockDocument.select(nutritionSelector)).thenReturn(productNutritionElement);

        //Act
        ProductDetailPageNutritionParser productDetailPageNutritionParser = new ProductDetailPageNutritionParser(nutritionSelector, subSelectors);
        Optional<ProductNutrition> productNutrition = productDetailPageNutritionParser.getProductNutrition(mockDocument);

        //Assert
        assertThat(productNutrition.isPresent(), is(true));
        ProductNutrition nutrition = productNutrition.get();
        assertThat(nutrition.getNutrition(), is(10));

        verify(productNutritionElement, times(1)).select(subSelector1);
        verifyNoMoreInteractions(productNutritionElement);
    }
}