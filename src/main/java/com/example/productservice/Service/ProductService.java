package com.example.productservice.Service;

import java.util.List;

import com.example.productservice.Entitys.Product;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProduct(Long id);

    Product getProduct(String name);

    Product saveProduct(Product product);

    Product updateProduct(Long productId, Product product);

    void deleteProduct(Long id);
}
