package com.msr.bean;

import java.io.Serializable;

public class OrderItem implements Serializable{
	private String itemid;
	private int quantity;
	private double total;
	private Product product;
	private Orders oders;
	public OrderItem(String itemid, int quantity, double total, Product product, Orders oders) {
		super();
		this.itemid = itemid;
		this.quantity = quantity;
		this.total = total;
		this.product = product;
		this.oders = oders;
	}
	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Orders getOders() {
		return oders;
	}
	public void setOders(Orders oders) {
		this.oders = oders;
	}
	
	
	
}
