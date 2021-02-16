package com.jsainsburys.core.product.summary;

import com.jsainsburys.core.product.Product;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ProductSummary {

  private List<Product> products;
  private Total total;

  public ProductSummary(List<Product> products, Total total) {
    this.products = products;
    this.total = total;
  }

}
