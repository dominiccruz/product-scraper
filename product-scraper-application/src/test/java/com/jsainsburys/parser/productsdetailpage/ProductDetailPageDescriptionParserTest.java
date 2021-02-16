package com.jsainsburys.parser.productsdetailpage;

import com.jsainsburys.core.product.detail.ProductDescription;
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
import static org.mockito.Mockito.when;

public class ProductDetailPageDescriptionParserTest {

    @Test
    public void getProductDescription_firstSelectorIsUsed() {
        //Arrange
        String descriptionSelector = "selector";

        String subSelector1 = "subSelector1";
        String subSelector2 = "subSelector2";

        List<String> subSelectors = new ArrayList<>();
        subSelectors.add(subSelector1);
        subSelectors.add(subSelector2);

        Elements descriptionElement = mock(Elements.class);
        when(descriptionElement.text()).thenReturn("description");

        Elements productDescriptionElement = mock(Elements.class);
        when(productDescriptionElement.select(subSelector1)).thenReturn(descriptionElement);

        Document mockDocument = mock(Document.class);
        when(mockDocument.select(descriptionSelector)).thenReturn(productDescriptionElement);

        //Act
        ProductDetailPageDescriptionParser productDetailPageDescriptionParser = new ProductDetailPageDescriptionParser(descriptionSelector, subSelectors);
        Optional<ProductDescription> productDescription = productDetailPageDescriptionParser.getProductDescription(mockDocument);

        //Assert
        assertThat(productDescription.isPresent(), is(true));
        ProductDescription description = productDescription.get();
        assertThat(description.getDescription(), is("description"));

        verify(productDescriptionElement, times(0)).select(subSelector2);
    }

    @Test
    public void getProductDescription_secondSelectorIsUsedIfFirstDoesnotWork() {
        //Arrange
        String descriptionSelector = "selector";

        String subSelector1 = "subSelector1";
        String subSelector2 = "subSelector2";

        List<String> subSelectors = new ArrayList<>();
        subSelectors.add(subSelector1);
        subSelectors.add(subSelector2);

        Elements descriptionElement = mock(Elements.class);
        when(descriptionElement.text()).thenReturn(null);

        Elements descriptionElement2 = mock(Elements.class);
        when(descriptionElement2.text()).thenReturn("description");

        Elements productDescriptionElement = mock(Elements.class);
        when(productDescriptionElement.select(subSelector1)).thenReturn(descriptionElement);
        when(productDescriptionElement.select(subSelector2)).thenReturn(descriptionElement2);

        Document mockDocument = mock(Document.class);
        when(mockDocument.select(descriptionSelector)).thenReturn(productDescriptionElement);

        //Act
        ProductDetailPageDescriptionParser productDetailPageDescriptionParser = new ProductDetailPageDescriptionParser(descriptionSelector, subSelectors);
        Optional<ProductDescription> productDescription = productDetailPageDescriptionParser.getProductDescription(mockDocument);

        //Assert
        assertThat(productDescription.isPresent(), is(true));
        ProductDescription description = productDescription.get();
        assertThat(description.getDescription(), is("description"));
    }
}