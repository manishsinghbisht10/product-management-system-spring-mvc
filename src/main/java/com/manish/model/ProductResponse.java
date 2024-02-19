package com.manish.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductResponse {
	private String productName;
	private String productCode;
	private String productDescription;
	private String categoryName;
	private BigDecimal price;
	private String currency;
	private String location;
	private BigDecimal inventory;
	private Long productId;
}
