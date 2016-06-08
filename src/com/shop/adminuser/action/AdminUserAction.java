package com.shop.adminuser.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shop.adminuser.service.AdminUserService;
import com.shop.adminuser.vo.AdminUser;

public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser>{
	private AdminUser adminUser = new AdminUser();
	private AdminUserService adminUserService = new AdminUserService();
	@Override
	public AdminUser getModel() {
		return adminUser;
	}
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	
	public String login(){
		AdminUser existAdminUser = adminUserService.login(adminUser);
		if(existAdminUser==null){
			this.addActionError("用户名或者密码错误");
			return "loginFail";
		}else{
			ServletActionContext.getRequest().getSession().setAttribute("existAdminUser", existAdminUser);
			return "loginSuccess";
		}
		
	}

}
