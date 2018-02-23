package com.wutue.model;

import java.util.List;

import com.wutue.model.vo.ModuleView;
import com.wutue.pojo.User;

public class JsonResponse {

	 private String status;

	 private String message ;
	 
	 private User user;
	 
	 private List<ModuleView> moduleViews;
	 
	 public String getStatus() {
         return status;
     }
     
     public void setStatus(String status){    	 
    	 this.status=status;
     }
        
     public String getMessage() {
         return message;
     }
     
     public void setMessage(String message){    	 
    	 this.message=message;
     }

     public User getUser() {
         return user;
     }
     
     public void setUser(User user){    	 
    	 this.user=user;
     }
     
     public List<ModuleView> getModuleViews() {
    	 return moduleViews;
     }
     
     public void setModuleView(List<ModuleView> moduleViews) {
    	 this.moduleViews = moduleViews;
     }
     
     public JsonResponse() {	
    	 status = "200";
         message = "success";
         user = null;
         moduleViews = null;
	 }
     
}
