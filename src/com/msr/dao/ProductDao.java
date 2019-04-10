package com.msr.dao;

import java.util.List;
import java.util.Map;

import com.msr.bean.Category;
import com.msr.bean.Product;

public interface ProductDao {
	// 按6个类别获取首页商品数据
	Map<String, List> getFI(String cid);

	// 根据商品序号查询单个商品
	Product getone(String pid);

	// 根据商品种类查询素有的商品集合
	List<Product> findByCid(String cid);

	// 根据商品种类查询所有的商品集合，商品根据时间降序排序
	List<Product> OrderbyDate(String cid);

	// 根据商品序号查询商品集合，商品数量等于每页显示数量
	List<Product> findByCidPage(String cid, int pageIndex, int everySize);
	
	List<Product> findAll();
	
	void ModifyProduct(Product product);
	
	void addProduct(Product product);
	
	void deleteProduct(String pid);

	Product findByPname(String pname);
	
	
}
