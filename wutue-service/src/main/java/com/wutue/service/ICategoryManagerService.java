package com.wutue.service;

import java.util.List;
import com.wutue.model.bo.CategoryBO;
import com.wutue.pojo.Category;

public interface ICategoryManagerService {

	public int GetCategoryCntInOrg(int orgId);

	public List<Category> LoadAll();

	public CategoryBO Load(int orgId, int pageindex, int pagesize);

	public List<Integer> GetSubOrgIds(int orgId);

	public Category Find(int id);

	public void Delete(int id);

	public void AddOrUpdate(Category model) throws Exception;

}
