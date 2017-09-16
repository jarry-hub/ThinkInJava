package com.phicomm.big.data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.phicomm.big.data.dao.mysql.TestMapper;
import com.phicomm.big.data.module.test.TestModel;

@Service
public class TestService {
	
	private final TestMapper testMapper;

    @Autowired
    public TestService(TestMapper testMapper) {
        this.testMapper = testMapper;
        Assert.notNull(this.testMapper);
    }

    /**
     * 获取数据
     */
    public TestModel getData(long splitTableFlag, long id) {
        TestModel testModel = testMapper.getRecordById(splitTableFlag, id);
        return testModel;
    }
    
    /**
     * 保存数据
     */
    public void saveData(TestModel data) {
    	testMapper.insert(data.getSplitTableFlag(), data);
    }
    
    /**
     * 批量保存数据
     */
    public void saveDataSet(List<TestModel> list) {
    	testMapper.insertBatch(list);
    }

    /**
     * 测试分页
     */
/*    public int testSplitTable(long n) {
        return testMapper.testSplitTable(n);
    }*/

}
