package com.shop.user.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.shop.user.dao.UserDao;
import com.shop.user.vo.User;
import com.shop.utils.MailUtils;
import com.shop.utils.PageBean;
import com.shop.utils.UUIDUtils;


@Transactional
public class UserService {
	//ע��UserDao
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao){
		this.userDao=userDao;
	}
	
	//�û�����ѯ�û��ķ���
	
	public User findByUsername(String username){
		return userDao.findByUserName(username);
	}
	
	
	//ҵ�������û�ע�����
	public void save(User user) {
		// �����ǵ����ݴ������ݿ�
		
		user.setState(0);//0�����û�δ���1�����û��Ѿ�����
		String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		//���ͼ����ʼ�
		MailUtils.sendMail(user.getEmail(), code);
		
	}

	public User findByCode(String code) {
		// TODO Auto-generated method stub
		return userDao.findByCode(code);
	}
	
	//�޸��û�״̬����

	public void update(User existUser) {
		// TODO Auto-generated method stub
		userDao.update(existUser);
		
	}

	public User login(User user) {
		return userDao.login(user);
	}

	public PageBean<User> findByPage(Integer page) {
		// TODO Auto-generated method stub
		PageBean<User> pageBean = new PageBean<User>();
		// ���õ�ǰҳ��:
		pageBean.setPage(page);
		// ����ÿҳ��ʾ��¼��:
		// ��ʾ5��
		int limit = 5;
		pageBean.setLimit(limit);
		// �����ܼ�¼��:
		int totalCount = 0;
		totalCount = userDao.findCount();
		pageBean.setTotalCount(totalCount);
		// ������ҳ��
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// ����ÿҳ��ʾ���ݼ���:
		int begin = (page - 1)*limit;
		List<User> list = userDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	public User findByUid(Integer uid) {
		return userDao.findByUid(uid);
	}

	public void delete(User existUser) {
		// TODO Auto-generated method stub
		userDao.delete(existUser);
	}

}
