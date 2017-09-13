package com.phicomm.big.data.dao.mysql;

import com.phicomm.big.data.model.authorization.TokenBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * token验证
 * Created by yufei.liu
 */
@Repository
public interface TokenValidateMapper {

    /**
     * 获取用户token
     *
     * @param userId 用户id
     * @param appId  appId
     * @return token
     */
    TokenBean getToken(@Param("userId") String userId,
                       @Param("appId") String appId);

    /**
     * 插入一条新数据
     *
     * @param userId 用户id
     * @param appId  appId
     * @param token  token
     * @return 更新的行数
     */
    int insert(@Param("userId") String userId,
               @Param("appId") String appId,
               @Param("token") String token,
               @Param("time") Date time);

    /**
     * 插入一条新数据
     *
     * @param userId 用户id
     * @param appId  appId
     * @param token  token
     */
    void update(@Param("userId") String userId,
                @Param("appId") String appId,
                @Param("token") String token,
                @Param("time") Date time);

}

