package com.example.productservice.Entitys;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @NonNull
    @Column(name = "name", nullable = false)
    private String name;


    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be in range (1-99999)")
    @Digits(integer = 10, fraction = 2)
    @Column(name = "price", nullable = false)
    private BigDecimal price;

     public Product(Product item) {
        this.name = item.getName();
        this.price = item.getPrice();
    }


}