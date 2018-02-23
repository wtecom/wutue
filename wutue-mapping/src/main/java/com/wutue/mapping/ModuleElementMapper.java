package com.wutue.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wutue.pojo.ModuleElement;
import com.wutue.pojo.ModuleElementWithBLOBs;

public interface ModuleElementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ModuleElementWithBLOBs record);

    int insertSelective(ModuleElementWithBLOBs record);

    ModuleElementWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ModuleElementWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ModuleElementWithBLOBs record);

    int updateByPrimaryKey(ModuleElement record);
    
    List<ModuleElementWithBLOBs> Find(@Param("moduleId")Integer moduleId, @Param("ids")List<Integer> elementIds);
    
    List<ModuleElementWithBLOBs> FindByModuleId(Integer moduleId);
    
    List<Integer> FindIdsByModuleId(Integer moduleId);
}