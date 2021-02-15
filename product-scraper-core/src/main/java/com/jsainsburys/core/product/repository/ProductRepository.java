package com.jsainsburys.core.product.repository;

import com.jsainsburys.core.product.Product;

import java.util.List;

/**
 * Implement this interface to add and retrieve products
 */
public interface ProductRepository {
    List<Product> getAllProducts();
    boolean addProduct(Product product);
}