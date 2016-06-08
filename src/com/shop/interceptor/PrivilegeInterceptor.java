package com.shop.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.shop.adminuser.vo.AdminUser;

/*
 * 后台权限的拦截器
 */
public class PrivilegeInterceptor extends MethodFilterInterceptor{

	@Override
	//执行拦截器的方法
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		// 判断session中是否保存了后台用户的信息
		AdminUser existAdminUser = (AdminUser) ServletActionContext.getRequest().getSession().getAttribute("existAdminUser");
		if(existAdminUser==null){
			ActionSupport actionSupport = (ActionSupport) actionInvocation.getAction();
			actionSupport.addActionError("您还没有登录！没有权限访问！");
			return "loginFail";
		}else{
			return actionInvocation.invoke();
		}
	}

}
