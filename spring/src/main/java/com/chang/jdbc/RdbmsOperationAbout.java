/**
 * 关系数据库对象化其实就是用面向对象方式表示关系数据库操作，从而可以复用
 * Spring JDBC框架将数据库操作封装为一个RdbmsOperation，该对象是线程安全的、可复用的对象，是所有数据库对象的父类。
 * SqlOperation继承了RdbmsOperation，代表了数据库SQL操作，如select、update、call
 */
package com.chang.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.object.StoredProcedure;

import com.chang.jdbc.data.model.UserModel;

public class RdbmsOperationAbout {
	
	private static JdbcTemplate jdbcTemplate; 
	
	 //@BeforeClass: 在所有测试方法之前执行，且只执行一次
    @BeforeClass  
    public static void setUpClass() {  
        String url = "jdbc:mysql://localhost:3306/mysql_test";
        String userName = "root";
        String userPassword = "feixun*123";
        DriverManagerDataSource dataSource = new DriverManagerDataSource(url, userName, userPassword);  
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");  
        jdbcTemplate = new JdbcTemplate(dataSource);  
    }
    
  //@Before @After 在测试方法之前和之后执行的方法，对于每个测试方法都将执行一次；
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
    
    /************************************************ query ********************************************************/
    @Test  
    public void testSqlQuery() {  
        SqlQuery query = new UserModelSqlQuery(jdbcTemplate);  
        List<UserModel> result = query.execute("name5");  
        Assert.assertEquals(0, result.size());  
    } 
 
    @Test
    public void testMappingSqlQuery() {
        jdbcTemplate.update("insert into temp_test(name) values('name5')");
        SqlQuery<UserModel> query = new UserModelMappingSqlQuery(jdbcTemplate);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("name", "name5");
        UserModel result = query.findObjectByNamedParam(paramMap);
        Assert.assertNotNull(result);
    }
    
    /************************************************ update ********************************************************/
    @Test  
    public void testSqlUpdate() {         
        SqlUpdate insert = new InsertUserModel(jdbcTemplate);  
        insert.update("name5");  
             
        String updateSql = "update temp_test set name=? where name=?";  
        SqlUpdate update = new SqlUpdate(jdbcTemplate.getDataSource(), updateSql, new int[]{Types.VARCHAR, Types.VARCHAR});  
        update.update("name6", "name5");  
        String deleteSql = "delete from temp_test where name=:name";  
       
        SqlUpdate delete = new SqlUpdate(jdbcTemplate.getDataSource(), deleteSql, new int[]{Types.VARCHAR});  
        Map<String, Object> paramMap = new HashMap<String, Object>();  
        paramMap.put("name", "name5");  
        delete.updateByNamedParam(paramMap);  
    }
    
    /************************************************ StoredProcedure ********************************************************/
    @Test
    public void testStoredProcedure() {
    	StoredProcedure lengthFunction = new MySqlLengthFunction(jdbcTemplate);
    	Map<String, Object> outValues = lengthFunction.execute("test");
    	Assert.assertEquals(4, outValues.get("result"));
    }
    
    @Test  
    public void testStoredProcedure2() {  
        String createFunctionSql =  
            "CREATE FUNCTION FUNCTION_TEST(str VARCHAR(100)) " +  
            "returns INT return LENGTH(str)";  
        String dropFunctionSql = "DROP FUNCTION IF EXISTS FUNCTION_TEST";  
        jdbcTemplate.update(dropFunctionSql);  
        jdbcTemplate.update(createFunctionSql);  
        StoredProcedure lengthFunction = new MySqlLengthFunction(jdbcTemplate);  
        Map<String,Object> outValues = lengthFunction.execute("test");  
        Assert.assertEquals(4, outValues.get("result"));  
    }
    
}

/**
 * 一下类仅在本测试用例中使用，正常应新建 .java文件
 */
class UserModelSqlQuery extends SqlQuery<UserModel> {
	
	public UserModelSqlQuery(JdbcTemplate jdbcTemplate) {  
		// 1. setJdbcTemplate/ setDataSource：首先设置数据源或JdbcTemplate；
        //super.setDataSource(jdbcTemplate.getDataSource());  
        super.setJdbcTemplate(jdbcTemplate);  
        // 2. 定义sql语句，所以定义的sql语句都将被编译为PreparedStatement
        super.setSql("select * from temp_test where name=?");
        // 3. 对PreparedStatement参数描述，使用SqlParameter来描述参数类型，支持命名参数、占位符描述
        super.declareParameter(new SqlParameter(Types.VARCHAR));
        // 4. 编译：可选，当执行相应查询方法时会自动编译，用于将sql编译为PreparedStatement，对于编译的SqlQuery不能再对参数进行描述了
        compile();  
    }  

	@Override  
    protected RowMapper<UserModel> newRowMapper(Object[] parameters, Map context) {  
        //return new UserRowMapper();
		return null;
    }
	
}

/**
 * 用于简化SqlQuery中RowMapper创建，可以直接在实现mapRow(ResultSet rs, int rowNum)来将行数据映射为需要的形式；
 * MappingSqlQuery所有查询方法完全继承于SqlQuery
 *
 */
class UserModelMappingSqlQuery extends MappingSqlQuery<UserModel> {  
	public UserModelMappingSqlQuery(JdbcTemplate jdbcTemplate) {  
		super.setDataSource(jdbcTemplate.getDataSource());  
		super.setSql("select * from temp_test where name=:name");  
		super.declareParameter(new SqlParameter("name", Types.VARCHAR));  
		compile();  
	}  
	
	@Override  
	protected UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {  
		UserModel model = new UserModel();  
		model.setId(rs.getInt("id"));  
		model.setName(rs.getString("name"));  
		return model;  
	}   
}

/**
 * SqlUpdate类用于支持数据库更新操作，即增删改（insert、delete、update）操作，该方法类似于SqlQuery，只是职责不一样。
 * SqlUpdate提供了update及updateByNamedParam方法用于数据库更新操作，其中updateByNamedParam用于命名参数类型更新
 *
 */
class InsertUserModel extends SqlUpdate {  
	public InsertUserModel(JdbcTemplate jdbcTemplate) {  
		super.setJdbcTemplate(jdbcTemplate);  
		super.setSql("insert into temp_test(name) values(?)");  
		super.declareParameter(new SqlParameter(Types.VARCHAR));  
		compile();  
	}  
}

class MySqlLengthFunction extends StoredProcedure {
	public MySqlLengthFunction(JdbcTemplate jdbcTemplate) {
		super.setJdbcTemplate(jdbcTemplate);
		super.setSql("FUNCTION_TEST");
		super.setFunction(true);
		super.declareParameter(new SqlOutParameter("result", Types.INTEGER));  
        super.declareParameter(new SqlParameter("str", Types.VARCHAR));  
        compile();
	}
}


