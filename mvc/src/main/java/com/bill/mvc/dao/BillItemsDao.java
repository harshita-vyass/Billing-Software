package com.bill.mvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.bill.mvc.entity.BillItemsEntity;

public class BillItemsDao {

	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public void save(BillItemsEntity billItemsEntity, int billId) {

		String sql = "INSERT INTO bill_items (bill_id, product_id, quantity) VALUES (?, ?, ?)";
		template.update(sql, billId, billItemsEntity.getProductId(), billItemsEntity.getQuantity());

	}

	public List<BillItemsEntity> findByBillId(int billId) {
		String sql = "select bill_items.bill_id,bill_items.product_id,bill_items.quantity from bill_items where bill_id="
				+ billId;

		List<BillItemsEntity> billItemList = template.query(sql, new RowMapper<BillItemsEntity>() {

			public BillItemsEntity mapRow(ResultSet rs, int rowNum) throws SQLException {

				BillItemsEntity billItem = new BillItemsEntity();

				billItem.setBillId(rs.getInt("bill_id"));
				billItem.setProductId(rs.getInt("product_id"));
				billItem.setQuantity(rs.getInt("quantity"));

				return billItem;
			}

		});

		return billItemList;
	}

}
