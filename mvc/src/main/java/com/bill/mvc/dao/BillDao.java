package com.bill.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.bill.mvc.entity.BillEntity;

public class BillDao {

	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	public BillEntity findById(int billId) {

		String sql = "select bill.bill_id,bill.created_date,bill.amount from bill where bill.bill_id=" + billId;

		List<BillEntity> billList = template.query(sql, new RowMapper<BillEntity>() {

			@Override
			public BillEntity mapRow(ResultSet rs, int rowNum) throws SQLException {

				BillEntity bill = new BillEntity();

				bill.setBillingid(rs.getInt("bill_id"));
				bill.setBillingDate(rs.getTimestamp("created_date").toLocalDateTime());
				bill.setTotalAmount(rs.getDouble("amount"));
				return bill;
			}

		});

		return billList.get(0);
	}

	public List<BillEntity> findAll() {

		String sql = "select bill.bill_id,bill.created_date,bill.amount from bill order by bill.created_date DESC;";

		List<BillEntity> billList = template.query(sql, new RowMapper<BillEntity>() {

			@Override
			public BillEntity mapRow(ResultSet rs, int rowNum) throws SQLException {

				BillEntity bill = new BillEntity();

				bill.setBillingid(rs.getInt("bill_id"));
				bill.setBillingDate(rs.getTimestamp("created_date").toLocalDateTime());
				bill.setTotalAmount(rs.getDouble("amount"));
				return bill;
			}

		});

		return billList;
	}

	public int save(BillEntity billEntity) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO bill (created_date,amount) VALUES (?, ?)";
		template.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, new String[] { "bill_id" });
				ps.setTimestamp(1, Timestamp.valueOf(billEntity.getBillingDate()));
				ps.setDouble(2, billEntity.getTotalAmount());
				return ps;
			}
		}, keyHolder);

		return keyHolder.getKey().intValue();

	}
	
	
	

	
	
	
}
