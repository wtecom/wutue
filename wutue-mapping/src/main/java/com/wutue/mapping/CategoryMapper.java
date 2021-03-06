package com.wutue.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wutue.common.orm.PageInfo;
import com.wutue.pojo.Category;

public interface CategoryMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);
    
    List<Category>  LoadCategoryListPage(@Param("page")PageInfo page);
       
    List<Category>  LoadInOrgListPage(@Param("page")PageInfo page,@Param("ids")List<Integer> orgIds) ;

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
    
    List<Category> SameLevels(@Param("parentId")Integer parentId,@Param("id")Integer id);
    
    List<Category> LoadAll();

	Integer GetCategoryCntInOrg(@Param("ids")List<Integer> getSubOrgIds);
	
	List<Integer> GetSubOrgIds(@Param("cascadeId")String cascadeId);
}