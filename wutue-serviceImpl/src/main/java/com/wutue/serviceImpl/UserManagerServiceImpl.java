package com.wutue.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import com.wutue.common.orm.PageInfo;
import com.wutue.mapping.UserMapper;
import com.wutue.mapping.OrgMapper;
import com.wutue.model.bo.UserBO;
import com.wutue.model.vo.UserView;
import com.wutue.pojo.User;
import com.wutue.pojo.Org;
import com.wutue.service.IUserManagerService;

@Service("userManagerService") 
public class UserManagerServiceImpl implements IUserManagerService {

	@Resource
	UserMapper _UserDao;
	@Resource
	OrgMapper _orgDao;

	Mapper _mapper ;

	private  UserManagerServiceImpl() {

		_mapper=new DozerBeanMapper();	
	}

	@Override
	public UserBO Load(int orgId, int pageindex,
			int pagesize) {
		// TODO Auto-generated method stub
		if (pageindex < 1) pageindex = 1;
		List<User> users = new ArrayList<User>();;
		int total = 0;
		if (orgId == 0){
			PageInfo page=new PageInfo(pageindex,pagesize);
			users = _UserDao.LoadUserListPage(page);
			total = page.getTotalPage();
		}
		else{
			PageInfo page=new PageInfo(pageindex,pagesize);
			List<Integer> ids =GetSubOrgIds(orgId);
			if (ids!=null && ids.size()>0) {
				users = _UserDao.LoadInOrgListPage(page, ids);
			}	
			total = page.getTotalPage();
		}
		List<UserView> userViews =new ArrayList<UserView>();
		for (User user : users) {
			UserView userView =  (UserView) _mapper.map(user,UserView.class);
			userViews.add(userView);
		}				
		for (UserView userView : userViews) {
			List<Org> orgs = _orgDao.LoadByUser(userView.getId());
			List<String> orgNames = new ArrayList<String>();
			for (Org org : orgs) {
				orgNames.add(org.getName());
			}
			userView.setOrganizationIds(StringUtils.join(orgNames, ","));
		}
		UserBO UserBO=new UserBO();
		UserBO.setTotal(total);
		UserBO.setList(userViews);
		UserBO.setPageCurrent(pageindex);
		return UserBO;
	}

	@Override
	public List<Integer> GetSubOrgIds(int orgId) {
		// TODO Auto-generated method stub
		Org org = _orgDao.selectByPrimaryKey(orgId);
		if (org!=null) {
			return _orgDao.GetSubOrgIds(org.getCascadeid(), 0);
		}
		else {
			return null;
		}
	}

	@Override
	public UserView Find(int id) {
		// TODO Auto-generated method stub
		User model = _UserDao.selectByPrimaryKey(id);
		return (UserView) _mapper.map(model,UserView.class);
	}

	@Override
	public void Delete(int id) {
		// TODO Auto-generated method stub
		_UserDao.deleteByPrimaryKey(id);
	}

	@Override
	public void AddOrUpdate(UserView userView){
		// TODO Auto-generated method stub
		User model= (User) _mapper.map(userView,User.class);
		if (model.getId() == 0) {
			_UserDao.insert(model);
		}
		else {
			_UserDao.updateByPrimaryKey(model);
		}
	}

	@Override
	public int GetUserCntInOrg(int orgId) {
		// TODO Auto-generated method stub
		if (orgId == 0){
			return _UserDao.GetUserCntInOrg(null);
		}
		else{
			return _UserDao.GetUserCntInOrg(GetSubOrgIds(orgId));
		}
	}

}
