package com.msr.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Cart implements Serializable{
	private Map<String, CartItem> map;
	private double total;
	private int count;
	
	public void addCartItem(CartItem ct) {
		String pid = ct.getProduct().getPid();
		if(map.containsKey(pid)) {
			CartItem ct1 = map.get(pid);
			ct1.setNum(ct1.getNum() + ct.getNum());
			ct1.setSubTotal(ct1.getSubTotal()+ ct.getNum()*ct.getProduct().getShop_price());;
		}else {
			map.put(pid, ct);
		}
		count++;
	}
	public void modify(CartItem ct , int num) {
		String pid = ct.getProduct().getPid();
		CartItem ct1 = map.get(pid);
		ct1.setNum(num);
		ct1.setSubTotal(num*ct.getProduct().getShop_price());
	}
	
	
	public int getCount() {
		count=0;
		for(String a : map.keySet()) {
			count +=map.get(a).getNum();
		}
		
		return count;
	}

	public void removeCartItem(String pid) {
		total += map.get(pid).getSubTotal();
		map.remove(pid);
		count--;
	}
	
	public void clearCart() {
		total = 0;
		map.clear();
		count = 0;
	}
	
	
	public Map<String, CartItem> getMap() {
		return map;
	}
	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}
	public double getTotal() {
		total = 0.0;
		for(CartItem ci :  map.values()) {
			total += ci.getSubTotal();
		}
		
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Cart(Map<String, CartItem> map, double total) {
		super();
		this.map = map;
		this.total = total;
	}
	
	
	public Cart() {
		super();
		map = new HashMap<String,CartItem>();
		count = 0;
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
