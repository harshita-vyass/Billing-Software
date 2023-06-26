package com.bill.mvc.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BillViewDto {
	
	private Integer billingid;//done bill table

	private LocalDateTime billingDate;//done bill table
	
	private Double totalBillAmount;//done bill table
	
	private List<BillingTableDto> billTableItems;

}
