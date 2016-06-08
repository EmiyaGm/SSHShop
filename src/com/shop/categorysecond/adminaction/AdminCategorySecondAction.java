package com.shop.categorysecond.adminaction;

import java.util.List;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shop.category.service.CategoryService;
import com.shop.category.vo.Category;
import com.shop.categorysecond.service.CategorySecondService;
import com.shop.categorysecond.vo.CategorySecond;
import com.shop.utils.PageBean;

public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{
	private CategorySecond categorySecond = new CategorySecond();
	private CategorySecondService categorySecondService = new CategorySecondService();
	private CategoryService categoryService = new CategoryService();
	

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}


	@Override
	public CategorySecond getModel() {
		// TODO Auto-generated method stub
		return categorySecond;
	}


	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	private Integer page;
	
	public void setPage(Integer page) {
		this.page = page;
	}


	public String findAll(){
		PageBean<CategorySecond> pageBean = categorySecondService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	public String addPage(){
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "addPage";
		
	}
	public String save(){
		categorySecondService.save(categorySecond);
		return "saveSuccess";
	}
	public String delete(){
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		categorySecondService.delete(categorySecond);
		return "deleteSuccess";
	}
	public String edit(){
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "editSuccess";
	}
	public String update(){
		categorySecondService.update(categorySecond);
		return "updateSuccess";
	}

}
