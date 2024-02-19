package com.manish.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {



	@Id
	@Column(name = "product_code")
	private String productCode;

	@Column(name = "product_description")
	private String productDescription;

	@Column(name = "product_name")
	private String productName;

	@ManyToOne
	@JoinColumn(name = "category", nullable = false)
	private Category category;

	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
	private Price price;

	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
	private Stock stock;
}
