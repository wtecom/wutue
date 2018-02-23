package com.wutue.mapping;

import com.wutue.pojo.UserExt;
import com.wutue.pojo.UserExtWithBLOBs;

public interface UserExtMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserExtWithBLOBs record);

    int insertSelective(UserExtWithBLOBs record);

    UserExtWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserExtWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(UserExtWithBLOBs record);

    int updateByPrimaryKey(UserExt record);
}