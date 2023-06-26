package com.bill.mvc.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bill.mvc.dao.BillDao;
import com.bill.mvc.dao.BillItemsDao;
import com.bill.mvc.dao.ProductDao;
import com.bill.mvc.dto.BillViewDto;
import com.bill.mvc.dto.BillingDto;
import com.bill.mvc.dto.BillingTableDto;
import com.bill.mvc.entity.BillEntity;
import com.bill.mvc.entity.BillItemsEntity;
import com.bill.mvc.entity.ProductEntity;

public class BillingServiceImpl {

	@Autowired
	BillDao billDao;

	@Autowired
	BillItemsDao billItemsDao;

	@Autowired
	ProductDao productDao;

	public List<BillEntity> getAllBills() {
		return billDao.findAll();
	}

	public BillEntity getBillById(int id) {
		return billDao.findById(id);
	}

	public int generateBill(List<BillingDto> billingDtoList) {

		BillEntity billEntity = new BillEntity();
		billEntity.setBillingDate(LocalDateTime.now());
		Double total = billingDtoList.stream().map(billItem -> billItem.getItem().getPrice() * billItem.getQuantity())
				.reduce(Double::sum).orElse(Double.valueOf(0));
		billEntity.setTotalAmount(total);

		int billId = billDao.save(billEntity);

		billingDtoList.forEach(billItem -> {
			BillItemsEntity billItemsEntity = new BillItemsEntity();

			billItemsEntity.setBillId(billId);
			billItemsEntity.setProductId(billItem.getItem().getId());
			billItemsEntity.setQuantity(billItem.getQuantity());

			billItemsDao.save(billItemsEntity, billId);
			
			productDao.updateProductQuantity(billItem.getItem().getId(), billItem.getItem().getQuantity()-billItem.getQuantity());
		});

		return billId;
	}

	public BillViewDto viewBill(int billId) {

		BillViewDto billViewDto = new BillViewDto();
		BillEntity billEntity = billDao.findById(billId);

		billViewDto.setBillingid(billEntity.getBillingid());
		billViewDto.setBillingDate(billEntity.getBillingDate());
		billViewDto.setTotalBillAmount(billEntity.getTotalAmount());

		List<BillingTableDto> billTableList = new ArrayList<>();

		List<BillItemsEntity> billItemList = billItemsDao.findByBillId(billId);

		billItemList.forEach(billItem -> {
			ProductEntity productEntity = productDao.getProductById(billItem.getProductId());
			BillingTableDto billingTableDto = new BillingTableDto();
			
			billingTableDto.setProductName(productEntity.getName());
			billingTableDto.setPrice(productEntity.getPrice());
			billingTableDto.setQuantity(billItem.getQuantity());
			billingTableDto.setTotal(productEntity.getPrice()*billItem.getQuantity());
			
			billTableList.add(billingTableDto);
		});
		
		billViewDto.setBillTableItems(billTableList);
		
		return billViewDto;

	}
}
