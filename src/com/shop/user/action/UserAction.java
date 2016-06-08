package com.shop.user.action;

import java.io.IOException;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shop.user.service.UserService;
import com.shop.user.vo.User;

public class UserAction extends ActionSupport implements ModelDriven<User>{
	//模型驱动使用的对象
	private User user = new User();
	//注入UserService
	private UserService userService;
	
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getModel(){
		return user;
	}
	
	/*
	 * 接收验证码
	 */
	
	private String checkcode;
	
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	/**
	 * 跳转到注册页面的执行方法
	 */
	
	public String registPage(){
		return "registPage";
	}
	
	public String loginPage(){
		return "loginPage";
	}
	
	public String findByName() throws IOException{
		//调用service进行查询
		User existUser=userService.findByUsername(user.getUsername());
		//获得response对象，向页面输出
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		if(existUser!=null){
			response.getWriter().println("<font color='red'>用户名已经存在</font>");
			
		}else{
			response.getWriter().println("<font color='green'>用户名可以使用</font>");
		}
		return NONE;
		
	}
	/**
	 * 用户注册方法
	 */
	
	public String regist(){
		//判断验证码程序
		//从session中获得验证码随机至
		String checkcode1 = ServletActionContext.getRequest().getSession().getAttribute("checkcode").toString();
		if(!checkcode.equalsIgnoreCase(checkcode1)){
			this.addActionError("验证码输入错误");
			return "checkcodeFail";
		}
		userService.save(user);
		this.addActionMessage("注册成功，请去邮箱激活");
		
		
		return "msg";
		
	}
	
	/*
	 * 用户激活方法
	 */
	public String active(){
		//根据激活码查询用户
		User existUser = userService.findByCode(user.getCode());
		
		if(existUser==null){
			this.addActionMessage("激活失败");
		}else{
			existUser.setState(1);
			existUser.setCode(null);
			userService.update(existUser);
			this.addActionMessage("激活成功");

		}
		return "msg";
	}
	
	public String login(){
		User existUser = userService.login(user);
		if(existUser==null){
			this.addActionError("登录失败，你的用户名或密码错误或用户未激活！");
			return LOGIN;
		}else{
			//将用户信息存入session中
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			return "loginSuccess";
		}
	}
	
	public String quit(){
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
	
	

}
