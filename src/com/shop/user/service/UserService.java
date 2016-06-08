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
	//注入UserDao
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao){
		this.userDao=userDao;
	}
	
	//用户名查询用户的方法
	
	public User findByUsername(String username){
		return userDao.findByUserName(username);
	}
	
	
	//业务层完成用户注册代码
	public void save(User user) {
		// 将我们的数据存入数据库
		
		user.setState(0);//0代表用户未激活，1代表用户已经激活
		String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		//发送激活邮件
		MailUtils.sendMail(user.getEmail(), code);
		
	}

	public User findByCode(String code) {
		// TODO Auto-generated method stub
		return userDao.findByCode(code);
	}
	
	//修改用户状态方法

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
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		// 显示5个
		int limit = 5;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = userDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置每页显示数据集合:
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
