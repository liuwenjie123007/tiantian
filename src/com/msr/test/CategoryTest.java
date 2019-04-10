package com.msr.test;

import com.msr.dao.CategoryDao;
import com.msr.dao.impl.CategoryDaoImpl;

public class CategoryTest {
	public static void main(String[] args) {
		CategoryDao categoryDao = new CategoryDaoImpl();
		System.out.println(categoryDao.findAll().size());
	}
}
