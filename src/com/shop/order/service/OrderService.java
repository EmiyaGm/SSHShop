package com.shop.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.shop.order.dao.OrderDao;
import com.shop.order.vo.Order;
import com.shop.order.vo.OrderItem;
import com.shop.utils.PageBean;
@Transactional
public class OrderService {
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public void save(Order order) {
		// 保存订单业务层代码
		orderDao.save(order);
		
	}

	public PageBean<Order> findByPageUid(Integer uid, Integer page) {
		// TODO Auto-generated method stub
		PageBean<Order> pageBean = new PageBean<Order>();
		
		pageBean.setPage(page);
		Integer limit = 5;
		pageBean.setLimit(limit);
		Integer totalCount = null;
		totalCount = orderDao.findByCountUid(uid);
		pageBean.setTotalCount(totalCount);
		Integer totalPage = null;
		if(totalCount%limit==0){
			totalPage=totalCount / limit;
		}else{
			totalPage=totalCount / limit +1;
		}
		pageBean.setTotalPage(totalPage);
		//设置每页显示数据集合
		Integer begin = (page-1)*limit;
		List<Order> list = orderDao.findByPageUid(uid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	public Order findByOid(Integer oid) {
		
		return orderDao.findByOid(oid);
	}

	public void update(Order currOrder) {
		// TODO Auto-generated method stub
		orderDao.update(currOrder);
	}

	public List<OrderItem> findOrderItem(Integer oid) {
		// TODO Auto-generated method stub
		return orderDao.findOrderItem(oid);
	}

	public PageBean<Order> findAll(Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		// 设置参数
		pageBean.setPage(page);
		// 设置每页显示的记录数:
		int limit = 10;
		pageBean.setLimit(limit);
		// 设置总记录数
		int totalCount = orderDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置每页显示数据集合
		int begin = (page - 1) * limit;
		List<Order> list = orderDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	

}
