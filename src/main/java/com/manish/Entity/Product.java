package com.manish.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import jakarta.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "product_code") })
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long productId;

	@Column(name = "product_code")
	private String productCode;

	@Column(name = "product_description")
	private String productDescription;

	@Column(name = "product_name")
	private String productName;

//	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH })
//	@JoinColumn(name = "ç", nullable = false)
//	private Category category;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "product_category", 
	           joinColumns = @JoinColumn(name = "product_id"), 
	           inverseJoinColumns = @JoinColumn(name = "category_code"))
	private List<Category> category;


	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
	private Price price;

	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
	private Stock stock;
	
    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productCode='" + productCode + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productName='" + productName + '\'' +
                '}';
    }
}
