package com.msr.dao;

import java.util.List;

import com.msr.bean.Category;

public interface CategoryDao {
	List<Category> findAll();

	Category findByCid(String cid);
}
