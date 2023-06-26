package com.bill.mvc.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BillingTableDto {

	private String productName; //items table
	
	private int quantity;//bill_items table
	
	private Double price;//items table
	
	private Double total;//calculation(price * qty)
	
}
