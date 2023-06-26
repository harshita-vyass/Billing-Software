package com.bill.mvc.dto;

import com.bill.mvc.entity.ProductEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BillingDto {
	
	private ProductEntity item;
	
	private Integer quantity;
	
}
