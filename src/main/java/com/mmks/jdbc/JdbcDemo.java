package com.mmks.jdbc;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mmks.jdbc.dao.SimpleJdbcDaoImpl;
import com.mmks.jdbc.dao.SpringDaoImpl;
import com.mmks.jdbc.model.Circle;

public class JdbcDemo {

	public static void main(String[] args) {
		// List<Circle> circles = new JDBCDaoImpl().getCircles(6);

		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		SpringDaoImpl dao = context.getBean("springDaoImpl", SpringDaoImpl.class);
//		List<Circle> circles = dao.getCircles(7);

		List<Circle> circles = dao.getCirclesByRowMapper(7);
		
		System.out.println(circles);
		
		System.out.println("Count " + dao.getCircleCount(7));
		
		for (int j = 320; j < 330; j++) {
//			dao.insertCircle(new Circle(j, "Circle name " + j));
			dao.insertNamedParameterCircle(new Circle(j, "Circle name " + j));
		}		
		
		SimpleJdbcDaoImpl daoImpl = context.getBean("jdbcDaoSupportImpl", SimpleJdbcDaoImpl.class);
		System.out.println(daoImpl.getCircleCount(5));

	}
}
