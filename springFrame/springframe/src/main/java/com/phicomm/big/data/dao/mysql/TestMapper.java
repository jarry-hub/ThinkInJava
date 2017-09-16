package com.phicomm.big.data.dao.mysql;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.phicomm.big.data.module.test.TestModel;

@Repository
public interface TestMapper {

    TestModel getRecordById(@Param("splitTableFlag") long splitTableFlag,
    						@Param("id") long id);
    
    /**
     * 插入数据
     *
     * @param data     数据id
     */
    void insert(@Param("splitTableFlag") long splitTableFlag, 
    			@Param("data") TestModel data);

    /**
     * 批量插入
     *
     * @param dataSet  批量数据
     */
    void insertBatch(@Param("dataSet") List<TestModel> dataSet);

}
