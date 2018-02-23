package com.wutue.mapping;

import com.wutue.pojo.UserCfg;

public interface UserCfgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserCfg record);

    int insertSelective(UserCfg record);

    UserCfg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserCfg record);

    int updateByPrimaryKey(UserCfg record);
}