package com.manish.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryNameAndProductNameDTO {
	private String product_name;
	private String category_name;
}
