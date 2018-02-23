package com.wutue.service;

import com.wutue.model.bo.StockBO;
import com.wutue.pojo.Stock;

public interface IStockManagerService {

	/**
	 * 根据部门ID得到进出库信息
	 * @param orgId
	 * @param pageindex
	 * @param pagesize
	 * @return
	 * @throws Exception 
	 */
	public StockBO Load(int orgId, int pageindex, int pagesize) throws Exception;

	public Stock Find(int id);

	public void Delete(int id);

	public void AddOrUpdate(Stock model);

}
