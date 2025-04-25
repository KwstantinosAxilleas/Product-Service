package com.example.productservice.Controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.productservice.Entitys.Product;
import com.example.productservice.Service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@Tag(name = "Product's Controller", description = "Manages products")
@RequestMapping("/products")
public class ProductController {

    ProductService productService;

    @Operation(summary = "Retrieves all products", description = "Provides information about all products in database.")
	@ApiResponses(value ={
		@ApiResponse(responseCode = "404", description = "Products don't exist", content = @Content(schema = @Schema(implementation = Product.class))),
        @ApiResponse(responseCode = "200", description = "Successful retrieval of products", content = @Content(schema = @Schema(implementation = Product.class))),
	})
    @GetMapping()
    public ResponseEntity<List<Product>> getProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @Operation(summary = "Retrieves a product by it's id", description = "Provides information about a product.")
	@ApiResponses(value ={
		@ApiResponse(responseCode = "404", description = "Product doesn't exist", content = @Content(schema = @Schema(implementation = Product.class))),
        @ApiResponse(responseCode = "200", description = "Successful retrieval of product", content = @Content(schema = @Schema(implementation = Product.class))),
	})
    @GetMapping("/id/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
    }

    @Operation(summary = "Retrieves a product by it's name", description = "Provides information about a product.")
	@ApiResponses(value ={
		@ApiResponse(responseCode = "404", description = "Product doesn't exist", content = @Content(schema = @Schema(implementation = Product.class))),
        @ApiResponse(responseCode = "200", description = "Successful retrieval of product", content = @Content(schema = @Schema(implementation = Product.class))),
	})
    @GetMapping("/name/{name}")
    public ResponseEntity<Product> getProduct(@PathVariable String name) {
        return new ResponseEntity<>(productService.getProduct(name), HttpStatus.OK);
    }


    @Operation(summary = "Creates a product", description = "Saves a product to the database.")
	@ApiResponse(responseCode = "201", description = "Product created successfully", content = @Content(schema = @Schema(implementation = Product.class)))
    @PostMapping()
    public ResponseEntity<Product> saveProduct(@Valid @RequestBody Product item) {
        return new ResponseEntity<>(productService.saveProduct(item), HttpStatus.CREATED);
    }

    @Operation(summary = "Updates a product", description = "Updates a product to the database.")
	@ApiResponse(responseCode = "201", description = "Product updated successfully", content = @Content(schema = @Schema(implementation = Product.class)))
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody Product item) {
        return new ResponseEntity<>(productService.updateProduct(id, item), HttpStatus.OK);
    }

    @Operation(summary = "Deletes a product", description = "Deletes a product from the database.")
	@ApiResponse(responseCode = "204", description = "Product deleted successfully", content = @Content(schema = @Schema(implementation = Product.class)))
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

