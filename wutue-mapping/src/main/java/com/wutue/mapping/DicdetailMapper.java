package com.wutue.mapping;

import com.wutue.pojo.Dicdetail;

public interface DicdetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dicdetail record);

    int insertSelective(Dicdetail record);

    Dicdetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dicdetail record);

    int updateByPrimaryKey(Dicdetail record);
}