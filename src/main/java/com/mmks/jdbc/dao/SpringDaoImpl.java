package com.mmks.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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

	/*
	 * public List<Circle> getCirclesByRowMapper(int circleId) {
	 * 
	 * List<Circle> circles = new ArrayList<Circle>();
	 * 
	 * // String query = "select * from circle where circle_id=?"; String query =
	 * "select * from circle where circle_id > ?"; circles = (List<Circle>)
	 * jdbcTemplate.query(query, new Integer[] { circleId }, new CircleMapper());
	 * 
	 * return circles; }
	 */

	public List<Circle> getCirclesByRowMapper(int circleId) {

		List<Circle> circles = new ArrayList<Circle>();

		// String query = "select * from circle where circle_id=?";
		String query = "select * from circle where circle_id > ?";
		circles = (List<Circle>) jdbcTemplate.query(query, new Integer[] { circleId }, new RowMapper<Circle>() {
			public Circle mapRow(ResultSet rs, int rowNum) throws SQLException {
				Circle circle = new Circle();
				circle.setId((Integer) (rs.getInt("circle_id")));
				circle.setName((String) rs.getString("name") + " :rowNum " + rowNum);
				return circle;
			}
		});

		return circles;
	}

	public int getCircleCount(int circleId) {
		String query = "select count(*) from circle where circle_id > ?";
		Integer rows = jdbcTemplate.queryForObject(query, Integer.class, circleId);
		return rows.intValue();
	}

	private static final class CircleMapper implements RowMapper<Circle> {
		public Circle mapRow(ResultSet rs, int rowNum) throws SQLException {
			Circle circle = new Circle();
			circle.setId((Integer) (rs.getInt("circle_id")));
			circle.setName((String) rs.getString("name") + " :rowNum " + rowNum);
			return circle;
		}
	}

	// Insert opertations
	public void insertCircle(Circle circle) {
		String query = "insert into circle values(?, ?) " + "ON CONFLICT (circle_id) DO NOTHING;";
		jdbcTemplate.update(query, new Object[] { circle.getId(), circle.getName() });

	}
}
