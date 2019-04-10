package com.msr.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.msr.bean.Category;
import com.msr.dao.CategoryDao;
import com.msr.utils.DBHepler;

public class CategoryDaoImpl implements CategoryDao {

	@Override
	public List<Category> findAll() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Category> cList = new ArrayList<Category>();

		conn = DBHepler.getConn();
		String sql = "select * from category";

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				String cid = rs.getString(1);
				String cname = rs.getString(2);
				cList.add(new Category(cid, cname));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBHepler.getClose(conn, ps, rs);
		}
		return cList;
	}

	@Override
	public Category findByCid(String cid) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Category category = null;

		conn = DBHepler.getConn();
		String sql = "select * from category where cid = ?";
	

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cid);
			rs = ps.executeQuery();

			if (rs.next()) {
				String ccid = rs.getString(1);
				String cname = rs.getString(2);
				category = new Category(ccid, cname);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBHepler.getClose(conn, ps, rs);
		}
		return category;
	}

}
