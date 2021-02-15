package com.jsainsburys.core.product.summary;

import com.jsainsburys.core.product.Product;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ProductSummary {

  private List<Product> products;
  private Totals totals;

  public ProductSummary(List<Product> products, Totals totals) {
    this.products = products;
    this.totals = totals;
  }

}
