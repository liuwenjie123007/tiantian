package com.msr.test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import com.msr.bean.OrderItem;
import com.msr.bean.Orders;
import com.msr.bean.Product;
import com.msr.bean.User;
import com.msr.dao.OrderItemDao;
import com.msr.dao.OrdersDao;
import com.msr.dao.ProductDao;
import com.msr.dao.UserDao;
import com.msr.dao.impl.OrderItemDaoImpl;
import com.msr.dao.impl.OrdersDaoImpl;
import com.msr.dao.impl.ProductDaoImpl;
import com.msr.dao.impl.UserDaoImpl;
import com.msr.utils.ExcelUtil;

public class Test {

	public static void main(String[] args) {
		ExcelUtil eu = new ExcelUtil();
		eu.output(eu.printAll());
	}

}
