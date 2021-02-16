package com.jsainsburys.schema.converter;

import com.jsainsburys.core.product.Product;
import com.jsainsburys.core.product.summary.ProductSummary;
import com.jsainsburys.schema.ProductDto;
import com.jsainsburys.schema.ProductListDto;
import com.jsainsburys.schema.TotalDto;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ProductSummaryToProductListDtoConverter {

    private final ProductToProductDtoConverter productToProductDtoConverter;

    public Optional<ProductListDto> convert(ProductSummary productSummary) {
        Optional<ProductListDto> productListDto = Optional.empty();
        List<Product> products = productSummary.getProducts();
        if(products != null && !products.isEmpty()) {
            List<ProductDto> results = products.stream()
                    .map(productToProductDtoConverter::convert)
                    .filter(result -> result.isPresent())
                    .map(result -> result.get())
                    .collect(Collectors.toList());

            TotalDto totalDto = new TotalDto(productSummary.getTotals().getGross().getValue(), productSummary.getTotals().getVat().getValue());
            productListDto = Optional.of(new ProductListDto(results, totalDto));
        }
        return productListDto;
    }
}
