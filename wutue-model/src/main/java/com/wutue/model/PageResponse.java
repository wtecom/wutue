package com.wutue.model;

public class PageResponse {

	 private String path;
	 
	 public String getPath() {
         return path;
     }
     
     public void setPath(String path){    	 
    	 this.path=path;
     }
        
     public PageResponse() {	
    	 path = "/";
	 }
     
}
