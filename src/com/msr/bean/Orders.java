package com.msr.bean;

import java.io.Serializable;
import java.util.List;

public class Orders implements Serializable{
	private String oid;
	private String orderTime;
	private double total;
	private int state;
	private String address;
	private String name;
	private String telephone;
	private User user;
	private List<OrderItem> oiList;
	
	
	
	public Orders(String oid, String orderTime, double total, int state, String address, String name, String telephone,
			User user, List<OrderItem> oiList) {
		super();
		this.oid = oid;
		this.orderTime = orderTime;
		this.total = total;
		this.state = state;
		this.address = address;
		this.name = name;
		this.telephone = telephone;
		this.user = user;
		this.oiList = oiList;
	}
	public List<OrderItem> getOiList() {
		return oiList;
	}
	public void setOiList(List<OrderItem> oiList) {
		this.oiList = oiList;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Orders(String oid, String orderTime, double total, int state, String address, String name, String telephone,
			User user) {
		super();
		this.oid = oid;
		this.orderTime = orderTime;
		this.total = total;
		this.state = state;
		this.address = address;
		this.name = name;
		this.telephone = telephone;
		this.user = user;
	}
	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
