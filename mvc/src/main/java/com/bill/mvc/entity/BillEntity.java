package com.bill.mvc.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BillEntity {

	@Column(name="bill_id")
	private Integer billingid;

	@Column(name="created_date")
	private LocalDateTime billingDate;

	@Column(name="amount")
	private Double totalAmount;

}
