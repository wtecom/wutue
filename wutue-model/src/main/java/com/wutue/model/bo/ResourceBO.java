package com.wutue.model.bo;

import java.util.List;

import com.wutue.pojo.Resource;

public class ResourceBO {

private int total;
	
	private List<Resource> list;
	
	private int pageCurrent;
	
	public  int getTotal() {
	    return total;
	}

	public void setTotal(int total) {	    	
	    this.total = total;
    }
	
	public  List<Resource> getList() {
	    return list;
	}

	public void setList(List<Resource> list) {	    	
	    this.list = list;
    }
	
	public  int getPageCurrent() {
	    return pageCurrent;
	}

	public void setPageCurrent(int pageCurrent) {	    	
	    this.pageCurrent = pageCurrent;
    }
}
