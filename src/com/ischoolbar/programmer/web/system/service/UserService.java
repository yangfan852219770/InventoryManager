package com.ischoolbar.programmer.web.system.service;

import com.ischoolbar.programmer.core.common.service.CommonService;
import com.ischoolbar.programmer.web.system.pojo.base.TSUser;
/**
 * 
 * @author   
 *
 */
public interface UserService extends CommonService{

	public TSUser checkUserExits(TSUser user);
	public String getUserRole(TSUser user);
	public void pwdInit(TSUser user, String newPwd);
	/**
	 * 判断这个角色是不是还有用户使用
	 *@param id
	 *@return
	 */
	public int getUsersOfThisRole(String id);
}
