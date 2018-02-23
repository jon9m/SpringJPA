package com.mmks.jdbc;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mmks.jdbc.dao.SpringDaoImpl;
import com.mmks.jdbc.model.Circle;

public class JdbcDemo {

	public static void main(String[] args) {
		// List<Circle> circles = new JDBCDaoImpl().getCircles(6);

		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		SpringDaoImpl dao = context.getBean("springDaoImpl", SpringDaoImpl.class);
		List<Circle> circles = dao.getCircles(7);
		System.out.println(circles);

	}
}
