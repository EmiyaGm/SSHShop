package com.shop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shop.user.vo.User;
import com.shop.utils.PageHibernateCallback;

public class UserDao extends HibernateDaoSupport{
	//按名称查询是否有该用户
	public User findByUserName(String username){
		String hql="from User where username = ?";
		
		List<User> list = this.getHibernateTemplate().find(hql,username);
		
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
		
		
	}
	
	//用户存入数据库代码实现
	public void save(User user) {
		this.getHibernateTemplate().save(user);
		
	}

	public User findByCode(String code) {
		String hql="from User where code = ?";
		
		List<User> list = this.getHibernateTemplate().find(hql,code);
		
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public void update(User existUser) {
		//修改用户状态
		
		this.getHibernateTemplate().update(existUser);

	}

	public User login(User user) {
		String hql="from User where username = ? and password = ? and state = ?";
		List<User> list = this.getHibernateTemplate().find(hql, user.getUsername(),user.getPassword(),1);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public List<User> findByPage(int begin, int limit) {
		String hql = "from User";
		List<User> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<User>(hql, null, begin, limit));
		return list;
	}

	public User findByUid(Integer uid) {
		return this.getHibernateTemplate().get(User.class, uid);
	}

	public void delete(User existUser) {
		this.getHibernateTemplate().delete(existUser);
		
	}

	public int findCount() {
		String hql = "select count(*) from User";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

}
