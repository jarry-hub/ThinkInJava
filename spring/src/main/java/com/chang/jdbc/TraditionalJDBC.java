package com.chang.jdbc;

import java.sql.*;

public class TraditionalJDBC { 
	
	/*创建一个返回值为Connection的方法 */
	public Connection getConnection() throws SQLException, 
	java.lang.ClassNotFoundException {
		Connection conn = null;
	    try {
	    	/* 加载mysql的JDBC驱动 */
	        Class.forName("com.mysql.jdbc.Driver");
	        System.out.println("数据库驱动加载成功");
	        //取得连接的url,能访问MySQL数据库的用户名,密码；jsj：数据库名 
	        String url = "jdbc:mysql://localhost:3306/mysql_test";
	        String userName = "root";
	        String userPassword = "feixun*123";
	        /* 创建于Mysql数据库连接类的实例 */
	    	conn = DriverManager.getConnection(url,userName,userPassword);
	        if(conn!=null){
	            System.out.println("数据库连接成功");
	        }
	    } catch (Exception e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } 
	    
	    //返回Connection对象
	    return conn;
	}

	    public void JDBCProcess() throws SQLException {
	        try {
	        	/*第三步：获取连接类实例con，用con创建Statement对象类实例 sql_statement*/ 
	        	Connection conn = getConnection(); 
	        	Statement sql_statement = conn.createStatement(); 
	        	
	        	/* 对数据库进行相关操作 */
	        	//如果同名数据库存在，删除 
	        	sql_statement.executeUpdate("drop table if exists student"); 
	        	//执行了一个sql语句生成了一个名为student的表   (仅做测试使用，不实用.)
	        	sql_statement.executeUpdate("create table student (id int not null auto_increment, "
	        	 + "name varchar(20) not null default 'name', math int not null default 60, primary key (id) ); ");
	        	//向表中插入数据     (仅做测试使用，不实用.)
	        	sql_statement.executeUpdate("insert student values(1, 'liying', 98)"); 
	        	sql_statement.executeUpdate("insert student values(2, 'jiangshan', 88)"); 
	        	sql_statement.executeUpdate("insert student values(3, 'wangjiawu', 78)"); 
	        	
	        	//第四步：执行查询，用ResultSet类的对象，返回查询的结果 
	        	String query = "select * from student"; 
	        	ResultSet result = sql_statement.executeQuery(query); 
	        	
	        	System.out.println("Student表中的数据如下:"); 
	        	System.out.println("--------------------"); 
	        	System.out.println("学号" + "   " + "姓名" + "   " + "数学成绩 "); 
	        	System.out.println("--------------------"); 
	        	
	        	//对获得的查询结果进行处理，对Result类的对象进行操作 
	        	while (result.next()) 
	        	{ 
		        	int number = result.getInt("id"); 
		        	String name = result.getString("name"); 
		        	String mathScore = result.getString("math"); 
		        	//取得数据库中的数据 
		        	System.out.println(" " + number + " " + name + " " + mathScore); 
	        	} 
	        	
	        	//关闭连接和声明 
	        	sql_statement.close(); 
	        	conn.close(); 

		    } catch(java.lang.ClassNotFoundException e) { 
		    	//加载JDBC错误,所要用的驱动没有找到 
		    	System.err.print("ClassNotFoundException"); 
		    	//其他错误 
		    	System.err.println(e.getMessage()); 
		    } catch (SQLException ex) { 
		    	//显示数据库连接错误或查询错误 
		    	System.err.println("SQLException: " + ex.getMessage()); 
		    } 
	         
	    }

}
