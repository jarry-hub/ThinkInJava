/**
 * ��ϵ���ݿ������ʵ�������������ʽ��ʾ��ϵ���ݿ�������Ӷ����Ը���
 * Spring JDBC��ܽ����ݿ������װΪһ��RdbmsOperation���ö������̰߳�ȫ�ġ��ɸ��õĶ������������ݿ����ĸ��ࡣ
 * SqlOperation�̳���RdbmsOperation�����������ݿ�SQL��������select��update��call
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
 * һ������ڱ�����������ʹ�ã�����Ӧ�½� .java�ļ�
 */
class UserModelSqlQuery extends SqlQuery<UserModel> {
	
	public UserModelSqlQuery(JdbcTemplate jdbcTemplate) {  
		// 1. setJdbcTemplate/ setDataSource��������������Դ��JdbcTemplate��
        //super.setDataSource(jdbcTemplate.getDataSource());  
        super.setJdbcTemplate(jdbcTemplate);  
        // 2. ����sql��䣬���Զ����sql��䶼��������ΪPreparedStatement
        super.setSql("select * from temp_test where name=?");
        // 3. ��PreparedStatement����������ʹ��SqlParameter�������������ͣ�֧������������ռλ������
        super.declareParameter(new SqlParameter(Types.VARCHAR));
        // 4. ���룺��ѡ����ִ����Ӧ��ѯ����ʱ���Զ����룬���ڽ�sql����ΪPreparedStatement�����ڱ����SqlQuery�����ٶԲ�������������
        compile();  
    }  

	@Override  
    protected RowMapper<UserModel> newRowMapper(Object[] parameters, Map context) {  
        //return new UserRowMapper();
		return null;
    }
	
}

/**
 * ���ڼ�SqlQuery��RowMapper����������ֱ����ʵ��mapRow(ResultSet rs, int rowNum)����������ӳ��Ϊ��Ҫ����ʽ��
 * MappingSqlQuery���в�ѯ������ȫ�̳���SqlQuery
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
 * SqlUpdate������֧�����ݿ���²���������ɾ�ģ�insert��delete��update���������÷���������SqlQuery��ֻ��ְ��һ����
 * SqlUpdate�ṩ��update��updateByNamedParam�����������ݿ���²���������updateByNamedParam���������������͸���
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


