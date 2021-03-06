package com.wutue.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wutue.model.BjuiResponse;
import com.wutue.model.bo.ResourceBO;
import com.wutue.model.vo.ResourceVM;
import com.wutue.service.IResourceManagerService;

@Controller
@RequestMapping("/resourcemanager")
public class ResourceManagerController {

	@Autowired 
	IResourceManagerService _resourceManagerService;

	BjuiResponse _bjuiResponse= new BjuiResponse();	

	@RequestMapping(value="/index.do")
	public String Index(){
		return "resourcemanager/index";
	}

	@RequestMapping(value="/add.do")
	public String Add(Integer id,ModelMap model) {
		if (id == null) {
			id = 0;
		}
		com.wutue.pojo.Resource resource= _resourceManagerService.Find(id);
		model.addAttribute("model",resource);
		return "resourcemanager/add";
	}

	/**
	 * 添加或修改Resource
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add.do",method = RequestMethod.POST)
	public BjuiResponse Add(com.wutue.pojo.Resource model){
		try{
			_resourceManagerService.AddOrUpdate(model);
		}
		catch (Exception e) {
			_bjuiResponse.setMessage(e.getMessage());
			_bjuiResponse.setStatusCode("300");
		}
		return _bjuiResponse;
	}


	/**
	 * 加载某分类的所有Resources
	 * @param categoryId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/load.do")
	public ResourceBO Load(Integer categoryid,  Integer pageCurrent , Integer pageSize ) {
		if (pageCurrent == null) {
			pageCurrent = 1;
		}
		if (pageSize == null) {
			pageSize = 30;
		}
		return _resourceManagerService.Load(categoryid, pageCurrent, pageSize);
	}

	@ResponseBody
	@RequestMapping(value="/loadfortree.do")
	public List<com.wutue.pojo.Resource> LoadForTree(){
		List<com.wutue.pojo.Resource>  resources = _resourceManagerService.LoadAll();
		//添加根节点
		com.wutue.pojo.Resource module = new com.wutue.pojo.Resource();
		module.setId(0);
		module.setParentid(-1);
		module.setName("根结点");
		module.setCascadeid("0");
		resources.add(module);
		return resources;
	}

	@ResponseBody
	@RequestMapping(value="/delete.do")
	public BjuiResponse Delete(int id) {
		try {
			_resourceManagerService.Delete(id);
		}
		catch (Exception e) {
			_bjuiResponse.setMessage(e.getMessage());
			_bjuiResponse.setStatusCode("300");
		}
		return _bjuiResponse;
	}

	/**
	 * 为用户分配资源
	 * @param userid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/lookupmultiforuser.do")
	public String LookUpMultiForUser(int userid,ModelMap model){
		model.addAttribute("userid", userid);
		return "resourcemanager/lookUpMultiForUser";
	}

	@ResponseBody
	@RequestMapping(value="/loadwithuseraccess.do")
	public List<ResourceVM> LoadWithUserAccess(int cId, int userid){
		return _resourceManagerService.LoadWithAccess("UserResource",userid, cId);
	}

	@ResponseBody
	@RequestMapping(value="/accessforuser.do")
	public BjuiResponse AccessForUser(int userid, String moduleIds){		
		try{
			List<Integer> ids =new ArrayList<Integer>();        	 
			String[] strs = moduleIds.split("|");
			for (String str : strs) {
				ids.add(Integer.getInteger(str));
			}         
			_resourceManagerService.AssignResForUser(userid, ids);
		}
		catch (Exception e) {
			_bjuiResponse.setMessage(e.getMessage());
			_bjuiResponse.setStatusCode("300");
		}
		return _bjuiResponse;
	}
	
	/**
	 * 为角色分配资源
	 * @param roleid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/lookupmultiforrole.do")
	public String LookUpMultiForRole(int roleid,ModelMap model){
		model.addAttribute("roleid", roleid);
		return "resourcemanager/lookUpMultiForRole";
	}

	@ResponseBody
	@RequestMapping(value="/loadwithroleaccess.do")
	public List<ResourceVM> LoadWithRoleAccess(int cId, int roleid){
		return _resourceManagerService.LoadWithAccess("RoleResource",roleid, cId);
	}

	@ResponseBody
	@RequestMapping(value="/accessforrole.do")
	public BjuiResponse AccessForRole(int roleid, String moduleIds){		
		try{
			List<Integer> ids =new ArrayList<Integer>();        	 
			String[] strs = moduleIds.split("|");
			for (String str : strs) {
				ids.add(Integer.getInteger(str));
			}         
			_resourceManagerService.AssignResForRole(roleid, ids);
		}
		catch (Exception e) {
			_bjuiResponse.setMessage(e.getMessage());
			_bjuiResponse.setStatusCode("300");
		}
		return _bjuiResponse;
	}
}
