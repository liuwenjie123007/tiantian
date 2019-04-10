package com.msr.dao;

import java.util.List;

import com.msr.bean.OrderItem;

public interface OrderItemDao {
	void addOrderItems(List<OrderItem> oiList);
	List<OrderItem> findAllByOid(String oid);
	void updateOrderItem(String oid, OrderItem oi);
	
	
	void deleteItem(OrderItem oitem);
	
	List<OrderItem> findAll();
	
	
}
