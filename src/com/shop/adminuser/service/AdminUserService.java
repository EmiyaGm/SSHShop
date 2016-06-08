package com.shop.adminuser.service;

import com.shop.adminuser.dao.AdminUserDao;
import com.shop.adminuser.vo.AdminUser;

public class AdminUserService{
	private AdminUserDao adminUserDao = new AdminUserDao();

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	public AdminUser login(AdminUser adminUser) {
		// TODO Auto-generated method stub
		
		return adminUserDao.login(adminUser);
	}
	

}
