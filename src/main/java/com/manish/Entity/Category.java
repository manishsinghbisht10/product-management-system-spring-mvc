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



    @Column(name = "category_name")
    private String categoryName;

    @Id
    @Column(name = "category_code")
    private BigDecimal categoryCode;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> product;
}
