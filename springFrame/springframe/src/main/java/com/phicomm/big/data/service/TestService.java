package com.phicomm.big.data.service;

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
    public TestModel getData(long id) {
        return testMapper.getRecordById(id);
    }

    /**
     * 测试分页
     */
/*    public int testSplitTable(long n) {
        return testMapper.testSplitTable(n);
    }*/

}
