package com.msr.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PageUtils {

	/**
	 * 獲取總記錄數
	 * 
	 * @param cid
	 *            需要顯示的商品分類序號
	 * @return 每種商品的總記錄數
	 */
	public static int getTotalSize(String cid) {
		String sql = "select count(*) from product where cid =?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		conn = DBHepler.getConn();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cid);
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBHepler.getClose(conn, ps, rs);
		}

		return 0;
	}
	
	public static int getTotalSizeOrder(String username) {
		String sql = "select count(*) from user u,orders o where u.uid=o.uid and u.username=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = DBHepler.getConn();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBHepler.getClose(conn, ps, rs);
		}
		

		
		
		
		return 0;
	}
	
	public static int getTotalPageOrder(String username, int everySize) {
		int totalPage = getTotalSizeOrder(username);
		return (totalPage % everySize == 0) ? totalPage / everySize : totalPage / everySize + 1;
	}

	/**
	 * 獲取總頁數
	 * 
	 * @param cid
	 *            需要顯示的商品分類序號
	 * @param everySize
	 *            每種商品的總記錄數
	 * @return 在商品顯示頁面中商品的總頁數
	 */
	public static int getTotalPage(String cid, int everySize) {
		int totalPage = getTotalSize(cid);
		return (totalPage % everySize == 0) ? totalPage / everySize : totalPage / everySize + 1;
	}
	
	
}
