package com.msr.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.msr.bean.OrderItem;
import com.msr.bean.Orders;
import com.msr.bean.User;
import com.msr.dao.OrderItemDao;
import com.msr.dao.OrdersDao;
import com.msr.utils.DBHepler;

public class OrdersDaoImpl implements OrdersDao {

	@Override
	public void addOrders(Orders orders) {
		Connection conn = null;
		PreparedStatement ps = null;
		int rs = 0;

		conn = DBHepler.getConn();
		String sql = "insert into orders values(?,?,?,?,?,?,?,?)";

		/*
		 * private String oid; private String orderTime; private double total; private
		 * int state; private String address; private String name; private String
		 * telephone; private User user;
		 */

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, orders.getOid());
			ps.setString(2, orders.getOrderTime());
			ps.setDouble(3, orders.getTotal());
			ps.setInt(4, orders.getState());
			ps.setString(5, orders.getAddress());
			ps.setString(6, orders.getName());
			ps.setString(7, orders.getTelephone());
			ps.setString(8, orders.getUser().getUid());
			rs = ps.executeUpdate();
			if (rs > 0) {
				System.out.println("orders插入成功！");
			} else {
				System.out.println("orders插入失败！");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBHepler.getClose(conn, ps, null);
		}

	}

	@Override
	public List<Orders> getAllOrders(String username) {
		Connection conn = DBHepler.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from user u,orders o where u.uid=o.uid and u.username=?";
		List<Orders> oList = new ArrayList<Orders>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				String oid = rs.getString("oid");
				String orderTime = rs.getString("ordertime");
				double total = rs.getDouble("total");
				int o_state = rs.getInt(14);
				String address = rs.getString("address");
				String o_name = rs.getString(16);
				String o_telephone = rs.getString(17);

				String uid = rs.getString("uid"); // varchar(32)
				String username1 = rs.getString("username"); // varchar(20)
				String password = rs.getString("password"); // varchar(20)
				String u_name = rs.getString("name"); // varchar(20)
				String email = rs.getString("email"); // varchar(30)
				String u_telephone = rs.getString("telephone"); // varchar(20)
				String birthday = rs.getString("birthday"); // date
				String sex = rs.getString("sex"); // varchar(10)
				int u_state = rs.getInt("state"); // int(11)
				String code = rs.getString("code");// varchar(64)

				User user = new User(uid, username1, password, u_name, email, u_telephone, birthday, sex, u_state,
						code);

				Orders orders = new Orders(oid, orderTime, total, o_state, address, o_name, o_telephone, user);
				oList.add(orders);
			}

			for (Orders orders : oList) {
				String oid = orders.getOid();
				OrderItemDao odid = new OrderItemDaoImpl();
				orders.setOiList(odid.findAllByOid(oid));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBHepler.getClose(conn, ps, rs);
		}

		return oList;
	}

	@Override
	public List<Orders> getOrderPage(String username, int pageIndex, int everySize) {

		int biginIndex = everySize * (pageIndex - 1);

		Connection conn = DBHepler.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from user u,orders o where u.uid=o.uid and u.username=? order by ordertime desc limit ?,?";
		List<Orders> oList = new ArrayList<Orders>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setInt(2, biginIndex);
			ps.setInt(3, everySize);
			rs = ps.executeQuery();
			while (rs.next()) {
				String oid = rs.getString("oid");
				String orderTime = rs.getString("ordertime");
				double total = rs.getDouble("total");
				int o_state = rs.getInt(14);
				String address = rs.getString("address");
				String o_name = rs.getString(16);
				String o_telephone = rs.getString(17);

				String uid = rs.getString("uid"); // varchar(32)
				String username1 = rs.getString("username"); // varchar(20)
				String password = rs.getString("password"); // varchar(20)
				String u_name = rs.getString("name"); // varchar(20)
				String email = rs.getString("email"); // varchar(30)
				String u_telephone = rs.getString("telephone"); // varchar(20)
				String birthday = rs.getString("birthday"); // date
				String sex = rs.getString("sex"); // varchar(10)
				int u_state = rs.getInt("state"); // int(11)
				String code = rs.getString("code");// varchar(64)

				User user = new User(uid, username1, password, u_name, email, u_telephone, birthday, sex, u_state,
						code);

				Orders orders = new Orders(oid, orderTime, total, o_state, address, o_name, o_telephone, user);
				oList.add(orders);
			}

			for (Orders orders : oList) {
				String oid = orders.getOid();
				OrderItemDao odid = new OrderItemDaoImpl();
				orders.setOiList(odid.findAllByOid(oid));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBHepler.getClose(conn, ps, rs);
		}

		return oList;
	}

	@Override
	public List<Orders> findAll() {
		Connection conn = DBHepler.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from user u,orders o where u.uid=o.uid";
		List<Orders> oList = new ArrayList<Orders>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				String oid = rs.getString("oid");
				String orderTime = rs.getString("ordertime");
				double total = rs.getDouble("total");
				int o_state = rs.getInt(14);
				String address = rs.getString("address");
				String o_name = rs.getString(16);
				String o_telephone = rs.getString(17);

				String uid = rs.getString("uid"); // varchar(32)
				String username1 = rs.getString("username"); // varchar(20)
				String password = rs.getString("password"); // varchar(20)
				String u_name = rs.getString("name"); // varchar(20)
				String email = rs.getString("email"); // varchar(30)
				String u_telephone = rs.getString("telephone"); // varchar(20)
				String birthday = rs.getString("birthday"); // date
				String sex = rs.getString("sex"); // varchar(10)
				int u_state = rs.getInt("state"); // int(11)
				String code = rs.getString("code");// varchar(64)

				User user = new User(uid, username1, password, u_name, email, u_telephone, birthday, sex, u_state,
						code);

				Orders orders = new Orders(oid, orderTime, total, o_state, address, o_name, o_telephone, user);
				oList.add(orders);
			}

			for (Orders orders : oList) {
				String oid = orders.getOid();
				OrderItemDao odid = new OrderItemDaoImpl();
				orders.setOiList(odid.findAllByOid(oid));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBHepler.getClose(conn, ps, rs);
		}

		return oList;
	}

	@Override
	public void updateOrders(Orders orders) {
		Connection conn = DBHepler.getConn();
		PreparedStatement ps = null;
		int rs = 0;
		String sql = "update orders set ordertime=? , total=? , state=? , address=? , name=? , telephone = ? where oid=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, orders.getOrderTime());
			ps.setDouble(2, orders.getTotal());
			ps.setInt(3, orders.getState());
			ps.setString(4, orders.getAddress());
			ps.setString(5, orders.getName());
			ps.setString(6, orders.getTelephone());
			ps.setString(7, orders.getOid());
			rs = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBHepler.getClose(conn, ps, null);
		}
		OrderItemDao odid = new OrderItemDaoImpl();
		for(OrderItem oi : orders.getOiList()) {
			System.out.println("-------oi---------------:"+oi);
			odid.updateOrderItem(orders.getOid(),oi);
		}
	}

	@Override
	public Orders findByOid(String oid) {
		Connection conn = DBHepler.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from user u,orders o where u.uid=o.uid and oid = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, oid);
			rs = ps.executeQuery();
			if (rs.next()) {
				String orderTime = rs.getString("ordertime");
				double total = rs.getDouble("total");
				int o_state = rs.getInt(14);
				String address = rs.getString("address");
				String o_name = rs.getString(16);
				String o_telephone = rs.getString(17);

				String uid = rs.getString("uid"); // varchar(32)
				String username1 = rs.getString("username"); // varchar(20)
				String password = rs.getString("password"); // varchar(20)
				String u_name = rs.getString("name"); // varchar(20)
				String email = rs.getString("email"); // varchar(30)
				String u_telephone = rs.getString("telephone"); // varchar(20)
				String birthday = rs.getString("birthday"); // date
				String sex = rs.getString("sex"); // varchar(10)
				int u_state = rs.getInt("state"); // int(11)
				String code = rs.getString("code");// varchar(64)

				User user = new User(uid, username1, password, u_name, email, u_telephone, birthday, sex, u_state,
						code);
				Orders orders = new Orders(oid, orderTime, total, o_state, address, o_name, o_telephone, user);
				OrderItemDao odid = new OrderItemDaoImpl();
				orders.setOiList(odid.findAllByOid(oid));
				
				return orders;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBHepler.getClose(conn, ps, rs);
		}

		return null;
	}

	@Override
	public void deleteOrders(String oid) {
		Connection conn = DBHepler.getConn();
		PreparedStatement ps = null;
		int rs = 0;
		
		Orders orders = findByOid(oid);
		OrderItemDao odid = new OrderItemDaoImpl();
		for(OrderItem oi :orders.getOiList()) {
			odid.deleteItem(oi);
		}
		
		String sql = "delete from orders where oid = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, oid);
			rs = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBHepler.getClose(conn, ps, null);
		}
		
		
		
	}
}
