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
 * ��ҳ����action
 */
public class IndexAction extends ActionSupport{
	//ע���Լ������service
	
	private CategoryService categoryService;
	
	private ProductService productService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}



	/**
	 * ִ�еķ�����ҳ�ķ���
	 */
	
	public String execute(){
		//��ѯһ������
		List<Category> cList = categoryService.findAll();
		//��ѯ������Ʒ
		List<Product> hList = productService.findHot();
		//��ѯ������Ʒ
		List<Product> nList = productService.findNew();
		//��һ���������session�ķ�Χ
		ActionContext.getContext().getSession().put("cList", cList);
		
		ActionContext.getContext().getValueStack().set("hList", hList);
		
		ActionContext.getContext().getValueStack().set("nList", nList);
		return "index";
	}

}
