package com.manish.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "currency")
    private String currency;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
