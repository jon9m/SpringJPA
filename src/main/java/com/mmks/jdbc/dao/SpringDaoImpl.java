package com.mmks.jdbc.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.mmks.jdbc.model.Circle;

@Component
public class SpringDaoImpl {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Circle> getCircles(int circleId) {

		List<Circle> circles = new ArrayList<Circle>();

		// String query = "select * from circle where circle_id=?";
		String query = "select * from circle where circle_id > ?";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, circleId);

		for (Map row : rows) {
			Circle circle = new Circle();
			circle.setId((Integer) (row.get("circle_id")));
			circle.setName((String) row.get("name"));
			circles.add(circle);
		}

		return circles;
	}
}
