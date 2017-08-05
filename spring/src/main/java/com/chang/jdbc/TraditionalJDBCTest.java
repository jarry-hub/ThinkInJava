package com.chang.jdbc;

import java.sql.SQLException;

import org.junit.Test;

public class TraditionalJDBCTest {
	
	@Test
	public void test() {
		TraditionalJDBC traditionalJDBC = new TraditionalJDBC();
		try {
			traditionalJDBC.JDBCProcess();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
