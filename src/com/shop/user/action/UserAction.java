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
	//ģ������ʹ�õĶ���
	private User user = new User();
	//ע��UserService
	private UserService userService;
	
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getModel(){
		return user;
	}
	
	/*
	 * ������֤��
	 */
	
	private String checkcode;
	
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	/**
	 * ��ת��ע��ҳ���ִ�з���
	 */
	
	public String registPage(){
		return "registPage";
	}
	
	public String loginPage(){
		return "loginPage";
	}
	
	public String findByName() throws IOException{
		//����service���в�ѯ
		User existUser=userService.findByUsername(user.getUsername());
		//���response������ҳ�����
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		if(existUser!=null){
			response.getWriter().println("<font color='red'>�û����Ѿ�����</font>");
			
		}else{
			response.getWriter().println("<font color='green'>�û�������ʹ��</font>");
		}
		return NONE;
		
	}
	/**
	 * �û�ע�᷽��
	 */
	
	public String regist(){
		//�ж���֤�����
		//��session�л����֤�������
		String checkcode1 = ServletActionContext.getRequest().getSession().getAttribute("checkcode").toString();
		if(!checkcode.equalsIgnoreCase(checkcode1)){
			this.addActionError("��֤���������");
			return "checkcodeFail";
		}
		userService.save(user);
		this.addActionMessage("ע��ɹ�����ȥ���伤��");
		
		
		return "msg";
		
	}
	
	/*
	 * �û������
	 */
	public String active(){
		//���ݼ������ѯ�û�
		User existUser = userService.findByCode(user.getCode());
		
		if(existUser==null){
			this.addActionMessage("����ʧ��");
		}else{
			existUser.setState(1);
			existUser.setCode(null);
			userService.update(existUser);
			this.addActionMessage("����ɹ�");

		}
		return "msg";
	}
	
	public String login(){
		User existUser = userService.login(user);
		if(existUser==null){
			this.addActionError("��¼ʧ�ܣ�����û��������������û�δ���");
			return LOGIN;
		}else{
			//���û���Ϣ����session��
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			return "loginSuccess";
		}
	}
	
	public String quit(){
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
	
	

}
