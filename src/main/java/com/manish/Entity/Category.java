package com.manish.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "category_seq")
    @SequenceGenerator(name = "category_seq", sequenceName = "category_seq", initialValue = 1000)
    @Column(name = "category_code")
    private Long categoryCode;

    @Column(name = "category_name")
    private String categoryName;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> product;
}
