package com.shop.category.vo;

import java.util.HashSet;
import java.util.Set;

import com.shop.categorysecond.vo.CategorySecond;

public class Category {
	private String cid;
	private String cname;
	//һ�������д�Ŷ�������ļ���
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
