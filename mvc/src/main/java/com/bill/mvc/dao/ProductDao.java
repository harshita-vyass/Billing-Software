package com.bill.mvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.bill.mvc.entity.ProductEntity;

public class ProductDao {

	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public List<ProductEntity> getProductListByCategoryId(final int categoryId) {
		String sql = "select Items.id,Items.category_id,Items.name,Items.price,Items.quantity from Items where category_id="
				+ categoryId;

		List<ProductEntity> productList = template.query(sql, new RowMapper<ProductEntity>() {

			public ProductEntity mapRow(ResultSet rs, int rowNum) throws SQLException {

				ProductEntity product = new ProductEntity();

				product.setId(rs.getInt("id"));
				product.setCategoryId(rs.getInt("category_id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getDouble("price"));
				product.setQuantity(rs.getInt("quantity"));
				return product;
			}

		});

		return productList;
	}

	public ProductEntity getProductById(final int id) {
		String sql = "select Items.id,Items.category_id,Items.name,Items.price,Items.quantity from Items where id="
				+ id;

		List<ProductEntity> productList = template.query(sql, new RowMapper<ProductEntity>() {

			public ProductEntity mapRow(ResultSet rs, int rowNum) throws SQLException {

				ProductEntity product = new ProductEntity();

				product.setId(rs.getInt("id"));
				product.setCategoryId(rs.getInt("category_id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getDouble("price"));
				product.setQuantity(rs.getInt("quantity"));
				return product;
			}

		});

		return productList.get(0);
	}
	 
	//inventory
	public List<ProductEntity> view() {
		String sql = "select Items.id,Items.category_id,Items.name,Items.price,Items.quantity from Items \n";

		List<ProductEntity> productList = template.query(sql, new RowMapper<ProductEntity>() {

			public ProductEntity mapRow(ResultSet rs, int rowNum) throws SQLException {

				ProductEntity product = new ProductEntity();

				product.setId(rs.getInt("id"));
				product.setCategoryId(rs.getInt("category_id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getDouble("price"));
				product.setQuantity(rs.getInt("quantity"));
				return product;
			}

		});

		return productList;
	}
	
	public void updateProductQuantity(int productId, int quantity) {
		String sql = "UPDATE items set items.quantity= ? where items.id=?";
		Object[] params = {quantity, productId};
		
		template.update(sql, params);
	}

}
