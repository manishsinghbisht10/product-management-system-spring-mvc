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
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column(name = "location")
	private String location;

	@Column(name = "inventory_available")
	private BigDecimal inventoryAvailable;

	@OneToOne
	@JoinColumn(name = "product_code")
	private Product product;
}
