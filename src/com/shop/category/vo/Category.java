package com.shop.category.vo;

import java.util.HashSet;
import java.util.Set;

import com.shop.categorysecond.vo.CategorySecond;

public class Category {
	private String cid;
	private String cname;
	//一级分类中存放二级分类的集合
	private Set<CategorySecond> categorySeconds = new HashSet<CategorySecond>();
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Set<CategorySecond> getCategorySeconds() {
		return categorySeconds;
	}
	public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
		this.categorySeconds = categorySeconds;
	}
	

}
