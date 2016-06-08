package com.shop.index.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shop.category.service.CategoryService;
import com.shop.category.vo.Category;
import com.shop.product.service.ProductService;
import com.shop.product.vo.Product;

/**
 * 
 * @author gm
 * 首页访问action
 */
public class IndexAction extends ActionSupport{
	//注入以及分类的service
	
	private CategoryService categoryService;
	
	private ProductService productService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}



	/**
	 * 执行的访问首页的方法
	 */
	
	public String execute(){
		//查询一级分类
		List<Category> cList = categoryService.findAll();
		//查询热门商品
		List<Product> hList = productService.findHot();
		//查询最新商品
		List<Product> nList = productService.findNew();
		//将一级分类存入session的范围
		ActionContext.getContext().getSession().put("cList", cList);
		
		ActionContext.getContext().getValueStack().set("hList", hList);
		
		ActionContext.getContext().getValueStack().set("nList", nList);
		return "index";
	}

}
