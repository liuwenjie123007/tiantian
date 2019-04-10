package com.msr.dao;

import java.util.List;

import com.msr.bean.OrderItem;
import com.msr.bean.Orders;

public interface OrdersDao {
	void addOrders(Orders orders);
	List<Orders> getAllOrders(String username);
	
	List<Orders> getOrderPage(String username,int pageIndex, int everySize);
	
	List<Orders> findAll();
	
	void updateOrders(Orders orders);
	Orders findByOid(String oid);
	void deleteOrders(String oid);
}
