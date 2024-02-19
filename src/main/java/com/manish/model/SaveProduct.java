package com.manish.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaveProduct {

	private String productCode;
	private String productDescription;
	private String categoryName;
	private BigDecimal productPrice;
	private String currency;
	private String location;
	private BigDecimal invaentoryAvailable;
	private String productName;

}
