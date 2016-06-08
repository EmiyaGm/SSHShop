package com.shop.product.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shop.category.service.CategoryService;
import com.shop.product.service.ProductService;
import com.shop.product.vo.Product;
import com.shop.utils.PageBean;

public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	private Product product = new Product();
	private ProductService productService;
	
	//���շ���cid
	private Integer cid;
	//ע���Լ�����service
	private CategoryService categoryService;
	//���յ�ǰ��ҳ��
	private int page;
	//���ն�������id
	private Integer csid;
	
	
	
	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}
	

	public Integer getCid() {
		return cid;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	public Product getModel(){
		return product;
	}
	
	public String findByPid(){
		product = productService.findByPid(product.getPid());
		return "findByPid";
	}
	public String findByCid(){
		//List<Category> cList = categoryService.findAll();
		PageBean<Product> pageBean = productService.findByPageCid(cid,page);//����һ�������ѯ��Ʒ,����ҳ��ѯ
		//��pageBean����ֵջ
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	public String findByCsid(){
		//List<Category> cList = categoryService.findAll();
		PageBean<Product> pageBean = productService.findByPageCsid(csid,page);//���ݶ��������ѯ��Ʒ,����ҳ��ѯ
		//��pageBean����ֵջ
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}

}
