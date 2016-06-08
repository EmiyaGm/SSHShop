package com.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shop.product.vo.Product;
import com.shop.utils.PageHibernateCallback;

public class ProductDao extends HibernateDaoSupport{

	public List<Product> findHot() {
		//使用离线条件查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		criteria.add(Restrictions.eq("is_hot", 1));
		//倒叙排序输出
		criteria.addOrder(Order.desc("pdate"));
		//执行查询
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		
		return list;
	}

	public List<Product> findNew() {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		criteria.addOrder(Order.desc("pdate"));
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return list;
	}

	public Product findByPid(Integer pid) {
		// TODO Auto-generated method stub
		
		return this.getHibernateTemplate().get(Product.class, pid);
	}

	//根据分类ID，查询商品个数
	public int findCountCid(Integer cid) {
		// TODO Auto-generated method stub

		String hql = "select count(*) from Product p where p.categorySecond.category.cid = ?";
		List<Long> list =  this.getHibernateTemplate().find(hql, cid.toString());  //????????
		if(list!=null&&list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}

	//根据分类ID，查询商品集合
	public List<Product> findByPageCid(Integer cid, int begin, int limit) {
		// TODO Auto-generated method stub
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		//分页的另一种写法
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{cid.toString()}, begin, limit));
		if(list!=null&&list.size()>0){
			return list;
		}
		return null;
	}

	public int findCountCsid(Integer csid) {
		String hql = "select count(*) from Product p where p.categorySecond.csid = ?";
		List<Long> list =  this.getHibernateTemplate().find(hql, csid);
		if(list!=null&&list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs where cs.csid = ?";
		//分页的另一种写法
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{csid}, begin, limit));
		if(list!=null&&list.size()>0){
			return list;
		}
		return null;
	}

	public int findCount() {
		String hql = "select count(*) from Product";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Product> findByPage(int begin, int limit) {
		String hql = "from Product order by pdate desc";
		List<Product> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, null, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	public void save(Product product) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(product);
	}

	public void delete(Product product) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(product);
	}

	public void update(Product product) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(product);
	}

}
