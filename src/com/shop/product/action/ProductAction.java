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
	
	//接收分类cid
	private Integer cid;
	//注入以及分类service
	private CategoryService categoryService;
	//接收当前的页数
	private int page;
	//接收二级分类id
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
		PageBean<Product> pageBean = productService.findByPageCid(cid,page);//根据一级分类查询商品,带分页查询
		//将pageBean存入值栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	public String findByCsid(){
		//List<Category> cList = categoryService.findAll();
		PageBean<Product> pageBean = productService.findByPageCsid(csid,page);//根据二级分类查询商品,带分页查询
		//将pageBean存入值栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}

}
