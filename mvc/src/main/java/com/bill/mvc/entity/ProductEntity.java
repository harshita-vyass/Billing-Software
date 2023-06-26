package com.bill.mvc.entity;

import org.springframework.beans.factory.annotation.Qualifier;

public class ProductEntity {
	
	private Integer id;
	
	@Qualifier("category-id")
	private Integer categoryId;
	
	private String name;
	
	private Double price;
	
	private Integer quantity;
	

	public ProductEntity(Integer id, Integer categoryId, String name, Double price, Integer quantity) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public ProductEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ProductEntity [id=" + id + ", categoryId=" + categoryId + ", name=" + name + ", price=" + price
				+ ", quantity=" + quantity + "]";
	}
	
	
	
}
