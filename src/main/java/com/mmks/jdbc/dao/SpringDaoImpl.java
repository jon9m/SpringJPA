package com.mmks.jdbc.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.mmks.jdbc.model.Circle;

@Component
public class SpringDaoImpl {

	@Autowired
	private DataSource datasource;

	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	public List<Circle> getCircles(int circleId) {

		List<Circle> circles = new ArrayList<Circle>();

		// String query = "select * from circle where circle_id=?";
		String query = "select * from circle where circle_id > ?";

		jdbcTemplate.setDataSource(datasource);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, circleId);

		for (Map row : rows) {
			Circle circle = new Circle();
			circle.setId((Integer) (row.get("circle_id")));
			circle.setName((String) row.get("name"));
			circles.add(circle);
		}

		return circles;
	}

	public DataSource getDatasource() {
		return datasource;
	}

	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
