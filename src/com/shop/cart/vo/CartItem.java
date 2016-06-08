package com.shop.cart.vo;

import com.shop.product.vo.Product;

public class CartItem {
	private Product product;
	private int count;
	private double subtotal;
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product porduct) {
		this.product = porduct;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getSubtotal() {
		return count*product.getShop_price();
	}
	/*public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}*/
	

}
