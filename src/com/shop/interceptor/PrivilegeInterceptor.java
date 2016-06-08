package com.shop.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.shop.adminuser.vo.AdminUser;

/*
 * ��̨Ȩ�޵�������
 */
public class PrivilegeInterceptor extends MethodFilterInterceptor{

	@Override
	//ִ���������ķ���
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		// �ж�session���Ƿ񱣴��˺�̨�û�����Ϣ
		AdminUser existAdminUser = (AdminUser) ServletActionContext.getRequest().getSession().getAttribute("existAdminUser");
		if(existAdminUser==null){
			ActionSupport actionSupport = (ActionSupport) actionInvocation.getAction();
			actionSupport.addActionError("����û�е�¼��û��Ȩ�޷��ʣ�");
			return "loginFail";
		}else{
			return actionInvocation.invoke();
		}
	}

}
