package com.wutue.mapping;

import com.wutue.pojo.Dicindex;

public interface DicindexMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dicindex record);

    int insertSelective(Dicindex record);

    Dicindex selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dicindex record);

    int updateByPrimaryKey(Dicindex record);
}