package com.shop.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.shop.category.dao.CategoryDao;
import com.shop.category.vo.Category;

@Transactional
public class CategoryService {
	
	private CategoryDao categoryDao;

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	//ҵ����ѯ����һ������ķ���
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		
		return categoryDao.findAll();
	}

	public void save(Category category) {
		// TODO Auto-generated method stub
		categoryDao.save(category);
		
	}

	public Category findByCid(String cid) {
		return categoryDao.findByCid(cid);
	}

	public void delete(Category category) {
		// TODO Auto-generated method stub
		categoryDao.delete(category);
	}

	public void update(Category category) {
		// TODO Auto-generated method stub
		categoryDao.update(category);
		
	}

	
	

}
