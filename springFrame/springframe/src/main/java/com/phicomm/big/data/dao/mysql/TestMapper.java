package com.phicomm.big.data.dao.mysql;

import org.springframework.stereotype.Repository;

import com.phicomm.big.data.module.test.TestModel;

@Repository
public interface TestMapper {

    TestModel getRecordById(long id);

    /**
     * 获取分表的大小
     */
    //Integer testSplitTable(@Param("memberId") Long memberId);

}
