package com.shop.cart.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart implements Serializable{
	private Map<Integer,CartItem> map = new LinkedHashMap<Integer,CartItem>();
	private double total;
	
	
	public double getTotal() {
		return total;
	}
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	public void addCart(CartItem cartItem){
		//判断购物车中是否已经存在购物项
		//如果存在，数量增加,总计增加
		Integer pid = cartItem.getProduct().getPid();
		if(map.containsKey(pid)){
			CartItem _cartItem = map.get(pid);
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
		}else{
			map.put(pid, cartItem);
		}
		total += cartItem.getSubtotal();
	}
	
	public void removeCart(Integer pid){
		CartItem cartItem = map.remove(pid);
		//移除谁，返回谁
		total=total-cartItem.getSubtotal();
	}
	
	public void clearCart(){
		map.clear();
		total=0;
	}
	

}
