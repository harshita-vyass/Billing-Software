package com.bill.mvc.dao;

import com.bill.mvc.entity.CategoryEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class CategoryDao {

	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public List<CategoryEntity> view() {
		String sql = "select category.id,category.name from category";

		List<CategoryEntity> categoryList = template.query(sql, new RowMapper<CategoryEntity>() {

			public CategoryEntity mapRow(ResultSet rs, int rowNum) throws SQLException {

				CategoryEntity category = new CategoryEntity();

				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
				return category;
			}

		});

		return categoryList;
	}

}
