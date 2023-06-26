package com.bill.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "bill_items")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BillItemsEntity {
	
	@Id
	@Column(name="bill_id")
	private int billId;
	
	@Id
	@Column(name="product_id")
	private int productId;
	
	private int quantity;
	
}
