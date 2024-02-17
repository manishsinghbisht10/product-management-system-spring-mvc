package com.manish.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Productsorted {
	private String product_code;
	private String product_description;
	private String category_name;
	private BigDecimal category_code;
	private BigDecimal product_price;
	private String currency;
	private String location;
	private BigDecimal inventory_available;
	private String product_name;
}
