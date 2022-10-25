package com.qa.woo.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data 
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
	
	private int id;
	private String name;
	private float price;
	private float rating;
	private String category;
	private boolean inStock;
	private float discountPercentage;
	private String deliveryMode;
	private boolean isReturnAccepted;
	private String sellerName;
	

}
