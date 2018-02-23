package com.wutue.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import com.wutue.mapping.ModuleElementMapper;
import com.wutue.mapping.ModuleMapper;
import com.wutue.mapping.OrgMapper;
import com.wutue.mapping.RelevanceMapper;
import com.wutue.mapping.ResourceMapper;
import com.wutue.mapping.RoleMapper;
import com.wutue.mapping.UserMapper;
import com.wutue.model.vo.LoginUserVM;
import com.wutue.model.vo.ModuleView;
import com.wutue.pojo.Module;
import com.wutue.pojo.Role;
import com.wutue.pojo.User;
import com.wutue.service.ILoginService;
import com.wutue.common.utils.Md5Utils;

@Service("loginService") 
public class LoginServiceImpl implements ILoginService  {

	@Resource
	private UserMapper _userDao;
	@Resource
	private ModuleMapper _moduleDao;
	@Resource
	private RelevanceMapper _relevanceDao;
	@Resource
	private ModuleElementMapper _moduleElementDao;  
	@Resource
	private ResourceMapper _resourceDao;
	@Resource
	private OrgMapper _orgDao;  
	@Resource
	private RoleMapper _roleDao;

	Mapper _mapper ;
	Md5Utils _Md5Utils = new Md5Utils();

	private  LoginServiceImpl() {

		_mapper=new DozerBeanMapper();	
	}

	public LoginUserVM Login(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		User user = _userDao.FindSingle(username);
		if (user == null){
			throw new Exception("用户帐号不存在");
		}

		CheckPassword(user.getPassword(),  _Md5Utils.EncoderByMd5(password));

		LoginUserVM loginVM = new LoginUserVM();
		loginVM.setUser(user);

		//用户角色
		List<Integer> userRoleIds = _relevanceDao.FindUserRoleIds(user.getId());

		//用户角色与自己分配到的模块ID
		List<Integer> moduleIds = _relevanceDao.FindSecondIds(user.getId(), "UserModule" , "RoleModule" , userRoleIds);

		//用户角色与自己分配到的菜单ID
		List<Integer> elementIds = _relevanceDao.FindSecondIds(user.getId(), "UserElement" , "RoleElement" , userRoleIds);

		List<Role> roles= _roleDao.FindUserRoles(userRoleIds);

		loginVM.setRoles(roles);

		List<ModuleView> moduleViews=null;

		List<Module> modules = _moduleDao.Find(moduleIds);

		if (modules!=null) {
			moduleViews= new ArrayList<ModuleView>();
			List<ModuleView> _moduleView = new ArrayList<ModuleView>();
			for (Module module : modules) {
				ModuleView moduleView= (ModuleView) _mapper.map(module, ModuleView.class);
				if (module.getUrl() != null && module.getUrl().length() == 0) {
					moduleViews.add(moduleView);
				}
			}

			for (ModuleView moduleView : moduleViews) {
				for (Module module : modules) {
					if (module.getUrl() != "" && moduleView.getId() == module.getParentid()) {
						ModuleView _moduleView1= (ModuleView) _mapper.map(module, ModuleView.class);
						moduleView.getChildern().add(_moduleView1);
					}
				}
			}
		}


		loginVM.setModules(moduleViews);

		//用户角色与自己分配到的资源ID
		List<Integer> resourceIds = _relevanceDao.FindSecondIds(user.getId(),"UserResource" ,"UserResource" ,userRoleIds);

		loginVM.setResources(_resourceDao.Find(resourceIds)); 

		//用户角色与自己分配到的机构ID
		List<Integer> orgids = _relevanceDao.FindSecondIds(user.getId(),"UserAccessedOrg" ,"UserAccessedOrg" ,userRoleIds);

		loginVM.setAccessedOrgs(_orgDao.Find(orgids)); 

		return loginVM;
	}


	public void CheckPassword(String sqlpassword, String password) throws Exception{
		if (!sqlpassword.equals(password)){
			throw new Exception("密码错误");
		}
	}


	/**
	 * 开发者登陆
	 */
	public LoginUserVM LoginByDev() {
		// TODO Auto-generated method stub
		LoginUserVM loginUser = new LoginUserVM();
		User user=new User();
		user.setAccount("System");
		user.setName("开发者账号");
		loginUser.setUser(user);

		List<Role> roles= _roleDao.FindAll();

		loginUser.setRoles(roles);

		List<Module> modules = _moduleDao.FindAll();

		List<ModuleView> moduleViews= null;
		//模块包含的菜单     
		if (modules!=null) {	    	
			moduleViews= new ArrayList<ModuleView>();	    
			for (Module module : modules) {
				ModuleView moduleView= (ModuleView) _mapper.map(module,ModuleView.class);
				moduleView .setElements(_moduleElementDao.FindByModuleId(module.getId()));
				moduleViews.add(moduleView);
			}
		}

		loginUser.setModules(moduleViews);

		loginUser.setResources(_resourceDao.FindAll());

		loginUser.setAccessedOrgs(_orgDao.FindAll());

		return loginUser;
	}

	@Override
	public User FindSingle(String account) {
		// TODO Auto-generated method stub
		return _userDao.FindSingle(account);
	}

	@Override
	public void Register(String username, String password) throws Exception {
		User user = _userDao.FindSingle(username);
		if (user != null){
			throw new Exception("用户帐号已存在");
		}

		if (username == null || username.length() <= 6) {
			throw new Exception(username);
		}
		
		if (password == null || password.length() <= 6) {
			throw new Exception("密码不能低于6位");
		}
		
		
		User _user = new User();
		_user.setAccount(username);
		_user.setPassword(_Md5Utils.EncoderByMd5(password));
		_userDao.insert(_user);
	}

	@Override
	public User loadCurrentUser() {
		// TODO Auto-generated method stub
		return null;
	}
}
