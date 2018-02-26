package com.mmks.jdbc.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

//@Component("jdbcDaoSupportImpl") // Also works!
@Repository("jdbcDaoSupportImpl")  //Or use spring bean definition
public class SimpleJdbcDaoImpl extends JdbcDaoSupport {
		
	@Autowired
	public void setDs(DataSource dataSource) {
	     setDataSource(dataSource);
	}
	
	public int getCircleCount(int circleId) {
		String query = "select count(*) from circle where circle_id > ?";
		Integer rows = getJdbcTemplate().queryForObject(query, Integer.class, circleId);
		return rows.intValue();
	}
}
