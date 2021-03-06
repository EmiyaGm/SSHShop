package com.shop.categorysecond.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shop.categorysecond.vo.CategorySecond;
import com.shop.utils.PageHibernateCallback;

public class CategorySecondDao extends HibernateDaoSupport{

	// DAO中的统计二级分类个数的方法
		public int findCount() {
			String hql = "select count(*) from CategorySecond";
			List<Long> list = this.getHibernateTemplate().find(hql);
			if (list != null && list.size() > 0) {
				return list.get(0).intValue();
			}
			return 0;
		}

		// DAO中分页查询的方法
		public List<CategorySecond> findByPage(int begin, int limit) {
			String hql = "from CategorySecond order by csid desc";
			List<CategorySecond> list = this.getHibernateTemplate().execute(
					new PageHibernateCallback<CategorySecond>(hql, null, begin,
							limit));
			return list;
		}

		public void save(CategorySecond categorySecond) {
			// TODO Auto-generated method stub
			this.getHibernateTemplate().save(categorySecond);
		}

		public CategorySecond findByCsid(Integer csid) {
			// TODO Auto-generated method stub
			return this.getHibernateTemplate().get(CategorySecond.class, csid);
		}

		public void delete(CategorySecond categorySecond) {
			// TODO Auto-generated method stub
			this.getHibernateTemplate().delete(categorySecond);
		}

		public void update(CategorySecond categorySecond) {
			// TODO Auto-generated method stub
			this.getHibernateTemplate().update(categorySecond);
		}

		public List<CategorySecond> findAll() {
			String hql = "from CategorySecond";
			return this.getHibernateTemplate().find(hql);
		}

}
