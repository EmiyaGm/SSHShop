package com.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.shop.cart.vo.Cart;
import com.shop.cart.vo.CartItem;
import com.shop.product.service.ProductService;
import com.shop.product.vo.Product;

public class CartAction extends ActionSupport{
	private Integer pid;
	private Integer count;
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String addCart(){
		CartItem cartItem = new CartItem();
		cartItem.setCount(count);
		Product product = productService.findByPid(pid);
		cartItem.setProduct(product);
		//购物车应该存在session中
		Cart cart = getCart();
		cart.addCart(cartItem);
		return "addCart";
	}
	
	//从session中获得购物车

	private Cart getCart() {
		Cart cart = (Cart)ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart==null){
			cart = new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	
	public String clearCart(){
		Cart cart = getCart();
		cart.clearCart();
		return "clearCart";
	}
	public String removeCart(){
		Cart cart =getCart();
		cart.removeCart(pid);
		return "removeCart";
	}
	public String myCart(){
		
		return "myCart";
	}

}
