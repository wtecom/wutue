/**
 * 
 */
package com.wutue.service;

import com.wutue.model.vo.LoginUserVM;
import com.wutue.pojo.User;

/**
 * @author liuyunlong
 *
 */
public interface ILoginService {

	public LoginUserVM Login(String username, String password) throws Exception;

	public void CheckPassword(String sqlpassword, String password) throws Exception;
	
	public void Register(String username, String password) throws Exception;

	public LoginUserVM LoginByDev();

	public User FindSingle(String account);
	
	public User loadCurrentUser();
}
