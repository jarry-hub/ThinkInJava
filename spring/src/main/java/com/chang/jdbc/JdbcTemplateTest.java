package com.chang.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;  

public class JdbcTemplateTest { 
	
    private static JdbcTemplate jdbcTemplate; 
    
    //@BeforeClass: �����в��Է���֮ǰִ�У���ִֻ��һ��
    @BeforeClass  
    public static void setUpClass() {  
        String url = "jdbc:mysql://localhost:3306/mysql_test";
        String userName = "root";
        String userPassword = "feixun*123";
        DriverManagerDataSource dataSource = new DriverManagerDataSource(url, userName, userPassword);  
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");  
        jdbcTemplate = new JdbcTemplate(dataSource);  
    }
    
    //@Test  
    public void test() {  
        //1.����SQL  
        String sql = "select * from INFORMATION_SCHEMA.TABLES";  
        jdbcTemplate.query(sql, new RowCallbackHandler() {  
            @Override  
            public void processRow(ResultSet rs) throws SQLException {  
                //2.��������  
                String value = rs.getString("TABLE_NAME");  
                System.out.println("Column TABLENAME:" + value);  
            }  
        });  
    }
    
    //@Before @After �ڲ��Է���֮ǰ��֮��ִ�еķ���������ÿ�����Է�������ִ��һ�Σ�
    @Before  
    public void setUp() {  
       String createTableSql = "CREATE TABLE temp_test (" + "`id` INT NOT NULL AUTO_INCREMENT, " + "`name` VARCHAR(100)," + "PRIMARY KEY (`id`))";  
       jdbcTemplate.update(createTableSql);  
    }  
    @After  
    public void tearDown() {  
      String dropTableSql = "drop table temp_test";  
      jdbcTemplate.execute(dropTableSql);  
    }
    
    @Test  
    public void testCURD() {  
        insert(); select();
        System.out.println("");
        delete(); select();
        System.out.println("");
        update(); select(); 
    }
    
    private void insert() {  
	  jdbcTemplate.update("insert into temp_test(name) values('name1')");  
	  jdbcTemplate.update("insert into temp_test(name) values('name2')");  
	  Assert.assertEquals(2, jdbcTemplate.queryForInt("select count(*) from temp_test"));  
	}
    
    private void delete() {  
	  jdbcTemplate.update("delete from temp_test where name=?", new Object[]{"name2"});  
	  Assert.assertEquals(1, jdbcTemplate.queryForInt("select count(*) from temp_test"));  
	}
    
    private void update() {  
	  jdbcTemplate.update("update temp_test set name='name3' where name=?", new Object[]{"name1"});  
	  Assert.assertEquals(1, jdbcTemplate.queryForInt("select count(*) from temp_test where name='name3'"));  
	}
    
    private void select() {  
	  jdbcTemplate.query("select * from temp_test", new RowCallbackHandler(){  
	    @Override  
	    public void processRow(ResultSet rs) throws SQLException {  
	      System.out.print("====id:" + rs.getInt("id"));  
	      System.out.println(", name:" + rs.getString("name"));  
	    }  
	  });  
	}
    
    @Test  
    public void testPpreparedStatement1() {  
		int count = jdbcTemplate.execute(
		new PreparedStatementCreator() {  
			@Override  
	        public PreparedStatement createPreparedStatement(Connection conn)  
	            throws SQLException {  
	            return conn.prepareStatement("select count(*) from temp_test");  
	        }
	    }, 
		new PreparedStatementCallback<Integer>() {  
	         @Override  
	         public Integer doInPreparedStatement(PreparedStatement pstmt)  
	             throws SQLException, DataAccessException {  
	             pstmt.execute();  
	             ResultSet rs = pstmt.getResultSet();  
	             rs.next();  
	             return rs.getInt(1);  
	      	 }
	    }
		);	//execute����2������      PreparedStatementCreator() �� PreparedStatementCallback<Integer>()
		Assert.assertEquals(0, count);  
    }
    
    @Test  
    public void testPreparedStatement2() {  
	    String insertSql = "insert into temp_test(name) values (?)";  
	    int count = jdbcTemplate.update(insertSql, 
	    	new PreparedStatementSetter() {  
			    @Override  
			    public void setValues(PreparedStatement pstmt) throws SQLException {  
			    pstmt.setObject(1, "name4");  
			    }
		    }
	    );
	    Assert.assertEquals(1, count);      
	    String deleteSql = "delete from temp_test where name=?";  
	    count = jdbcTemplate.update(deleteSql, new Object[] {"name4"});  
	    Assert.assertEquals(1, count);  
    }
    
    @Test  
    public void testResultSet1() {  
	    jdbcTemplate.update("insert into temp_test(name) values('name5')");  
	    String listSql = "select * from temp_test";
	    
	    List<?> result = jdbcTemplate.query(listSql, 
	    		new RowMapper<Map>() {  
				    @Override  
				    public Map mapRow(ResultSet rs, int rowNum) throws SQLException {  
					    Map row = new HashMap();  
					    row.put(rs.getInt("id"), rs.getString("name"));  
					    return row;  
					}
				}
	    );  
	    Assert.assertEquals(1, result.size());  
	    jdbcTemplate.update("delete from temp_test where name='name5'");       
    }
    
    @Test  
    public void testResultSet2() {  
	    jdbcTemplate.update("insert into temp_test(name) values('name5')");  
	    String listSql = "select * from temp_test";  
	    final List result = new ArrayList();
	    
	    jdbcTemplate.query(listSql, 
	    		new RowCallbackHandler() {  
				    @Override  
				    public void processRow(ResultSet rs) throws SQLException {  
					    Map row = new HashMap();  
					    row.put(rs.getInt("id"), rs.getString("name"));  
					    result.add(row);  
				    }
				}
	    );  
	    Assert.assertEquals(1, result.size());  
	    jdbcTemplate.update("delete from temp_test where name='name5'");  
    }
    
    @Test  
    public void testResultSet3() {  
	    jdbcTemplate.update("insert into temp_test(name) values('name5')");  
	    String listSql = "select * from temp_test";  
	    
	    List result = jdbcTemplate.query(listSql, 
	    		new ResultSetExtractor<List>() {  
				    @Override  
				    public List extractData(ResultSet rs) throws SQLException, DataAccessException {  
					    List result = new ArrayList();  
					    while(rs.next()) {  
						    Map row = new HashMap();  
						    row.put(rs.getInt("id"), rs.getString("name"));  
						    result.add(row);  
					    }
					    return result;  
				    }
				}
	    );  
	    Assert.assertEquals(1, result.size());  
	    jdbcTemplate.update("delete from temp_test where name='name5'");  
    }
    
    @Test
    public void testResultFormate() {
    	//1.��ѯһ�����ݲ�����int�ͽ��
    	jdbcTemplate.queryForInt("select count(*) from temp_test");   //�ѷ���
    	Integer count = jdbcTemplate.queryForObject("select count(*) from temp_test", Integer.class);
    	
    	//2. ��ѯһ�����ݲ�����������ת��ΪMap����  
    	jdbcTemplate.update("insert into temp_test (`name`) values ('name5_1')");		//Ϊ�˹�Test������ӵ�
    	Map<String, Object> map = jdbcTemplate.queryForMap("select * from temp_test where name='name5_1'");  //��û�鵽���ܱ���
    	
    	//3.��ѯ  **һ��**  �κ����͵����ݣ����һ������ָ�����ؽ������  
    	jdbcTemplate.queryForObject("select count(*) from temp_test", Integer.class);  
    	
    	//4.��ѯһ�����ݣ�Ĭ�Ͻ�ÿ������ת��ΪMap
    	jdbcTemplate.update("insert into temp_test (`name`) values ('name5_2')");
    	jdbcTemplate.update("insert into temp_test (`name`) values ('name5_3')");
    	List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from temp_test");  
    	
    	//5.ֻ��ѯһ�������б���������String���ͣ���������name  
    	List<String> res = jdbcTemplate.queryForList("select name from temp_test where name=?", new Object[]{"name5_1"}, String.class);  
    	
    	//6.��ѯһ�����ݣ�����ΪSqlRowSet��������ResultSet�������ٰ󶨵�������  
    	SqlRowSet rs = jdbcTemplate.queryForRowSet("select * from temp_test");  
    }
    
    
}
