package com.manish.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Productsorted {
	private BigInteger product_id;
	private String product_code;
	private String product_description;
	private String product_name;
	private BigDecimal product_price;
	private String currency;
	private BigDecimal inventory_available;
	private String location;
	private String category_name;	

}
