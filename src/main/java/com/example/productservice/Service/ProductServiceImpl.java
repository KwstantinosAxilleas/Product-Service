package com.example.productservice.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.productservice.Entitys.Product;
import com.example.productservice.Exceptions.EntityNotFoundException;
import com.example.productservice.Repository.ProductRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;
    private final List<Product> inMemoryProducts = new ArrayList<>();


    @Override
    public List<Product> getAllProducts() {
    List<Product> all = new ArrayList<>(inMemoryProducts);
    productRepository.findAll().forEach(all::add);
    return all;
}


    @Override
    public Product getProduct(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        return unwrapProduct(product, productId);
    }

    @Override
    public Product getProduct(String productName) {
        Optional<Product> product = productRepository.findByName(productName);
        return unwrapProduct(product, productName);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long productId, Product updatedProduct) {
        Optional<Product> databaseProduct = productRepository.findById(productId);
        Product existingProduct = unwrapProduct(databaseProduct, productId);
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }


    static Product unwrapProduct(Optional<Product> entity, Long id) {
        if (entity.isPresent())
            return entity.get();
        else
            throw new EntityNotFoundException(id, Product.class);
    }
    static Product unwrapProduct(Optional<Product> entity, String name) {
        if (entity.isPresent())
            return entity.get();
        else
            throw new EntityNotFoundException(name, Product.class);
    }

}