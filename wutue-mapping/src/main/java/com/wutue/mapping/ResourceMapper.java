package com.wutue.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wutue.common.orm.PageInfo;
import com.wutue.pojo.Resource;

public interface ResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Integer id);
    
    List<Resource> LoadResourceListPage(@Param("page")PageInfo page);
    
    List<Resource> LoadInOrgListPage(@Param("page")PageInfo page,@Param("ids")List<Integer> orgIds) ;
    
    List<Resource> LoadInOrgs(@Param("ids")List<Integer> orgIds);
    
    List<Resource> Find(@Param("ids")List<Integer> resourceIds);
    
    List<Resource> FindAll();

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKeyWithBLOBs(Resource record);

    int updateByPrimaryKey(Resource record);
    
    Integer GetResourceCntInOrgs(@Param("ids")List<Integer> getSubOrgIds);

}