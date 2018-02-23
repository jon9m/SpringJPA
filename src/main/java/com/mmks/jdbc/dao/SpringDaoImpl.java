package com.mmks.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mmks.jdbc.model.Circle;

@Component
public class SpringDaoImpl {
	
	@Autowired
	private DataSource datasource;
	
	public List<Circle> getCircles(int circleId) {

		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		List<Circle> circles = new ArrayList<Circle>();

		try {

			conn = datasource.getConnection();
			
			// String query = "select * from circle where circle_id=?";
			String query = "select * from circle where circle_id>?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, circleId);

			rs = ps.executeQuery();

			while (rs.next()) {
				circles.add(new Circle(rs.getInt(1), rs.getString(2)));
			}

			return circles;

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				ps.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public DataSource getDatasource() {
		return datasource;
	}

	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}
	
	
}
