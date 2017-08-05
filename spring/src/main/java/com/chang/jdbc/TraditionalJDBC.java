package com.chang.jdbc;

import java.sql.*;

public class TraditionalJDBC { 
	
	/*����һ������ֵΪConnection�ķ��� */
	public Connection getConnection() throws SQLException, 
	java.lang.ClassNotFoundException {
		Connection conn = null;
	    try {
	    	/* ����mysql��JDBC���� */
	        Class.forName("com.mysql.jdbc.Driver");
	        System.out.println("���ݿ��������سɹ�");
	        //ȡ�����ӵ�url,�ܷ���MySQL���ݿ���û���,���룻jsj�����ݿ��� 
	        String url = "jdbc:mysql://localhost:3306/mysql_test";
	        String userName = "root";
	        String userPassword = "feixun*123";
	        /* ������Mysql���ݿ��������ʵ�� */
	    	conn = DriverManager.getConnection(url,userName,userPassword);
	        if(conn!=null){
	            System.out.println("���ݿ����ӳɹ�");
	        }
	    } catch (Exception e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } 
	    
	    //����Connection����
	    return conn;
	}

	    public void JDBCProcess() throws SQLException {
	        try {
	        	/*����������ȡ������ʵ��con����con����Statement������ʵ�� sql_statement*/ 
	        	Connection conn = getConnection(); 
	        	Statement sql_statement = conn.createStatement(); 
	        	
	        	/* �����ݿ������ز��� */
	        	//���ͬ�����ݿ���ڣ�ɾ�� 
	        	sql_statement.executeUpdate("drop table if exists student"); 
	        	//ִ����һ��sql���������һ����Ϊstudent�ı�   (��������ʹ�ã���ʵ��.)
	        	sql_statement.executeUpdate("create table student (id int not null auto_increment, "
	        	 + "name varchar(20) not null default 'name', math int not null default 60, primary key (id) ); ");
	        	//����в�������     (��������ʹ�ã���ʵ��.)
	        	sql_statement.executeUpdate("insert student values(1, 'liying', 98)"); 
	        	sql_statement.executeUpdate("insert student values(2, 'jiangshan', 88)"); 
	        	sql_statement.executeUpdate("insert student values(3, 'wangjiawu', 78)"); 
	        	
	        	//���Ĳ���ִ�в�ѯ����ResultSet��Ķ��󣬷��ز�ѯ�Ľ�� 
	        	String query = "select * from student"; 
	        	ResultSet result = sql_statement.executeQuery(query); 
	        	
	        	System.out.println("Student���е���������:"); 
	        	System.out.println("--------------------"); 
	        	System.out.println("ѧ��" + "   " + "����" + "   " + "��ѧ�ɼ� "); 
	        	System.out.println("--------------------"); 
	        	
	        	//�Ի�õĲ�ѯ������д�����Result��Ķ�����в��� 
	        	while (result.next()) 
	        	{ 
		        	int number = result.getInt("id"); 
		        	String name = result.getString("name"); 
		        	String mathScore = result.getString("math"); 
		        	//ȡ�����ݿ��е����� 
		        	System.out.println(" " + number + " " + name + " " + mathScore); 
	        	} 
	        	
	        	//�ر����Ӻ����� 
	        	sql_statement.close(); 
	        	conn.close(); 

		    } catch(java.lang.ClassNotFoundException e) { 
		    	//����JDBC����,��Ҫ�õ�����û���ҵ� 
		    	System.err.print("ClassNotFoundException"); 
		    	//�������� 
		    	System.err.println(e.getMessage()); 
		    } catch (SQLException ex) { 
		    	//��ʾ���ݿ����Ӵ�����ѯ���� 
		    	System.err.println("SQLException: " + ex.getMessage()); 
		    } 
	         
	    }

}
