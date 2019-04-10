package com.msr.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.msr.bean.OrderItem;
import com.msr.bean.Orders;
import com.msr.bean.Product;
import com.msr.dao.OrderItemDao;
import com.msr.dao.OrdersDao;
import com.msr.dao.ProductDao;
import com.msr.utils.DBHepler;

public class OrderItemDaoImpl implements OrderItemDao{

	@Override
	public void addOrderItems(List<OrderItem> oiList) {
		Connection conn = DBHepler.getConn();
		PreparedStatement ps = null;
		int rs = 0 ;
		try {
			for(OrderItem orderitem : oiList) {
				/*private String itemid;
				private int quantity;
				private double total;
				private Product product;
				private Orders oders;*/
				
				String sql = "insert into orderitem values(?,?,?,?,?)";
				ps = conn.prepareStatement(sql);
				ps.setString(1, orderitem.getItemid());
				ps.setInt(2, orderitem.getQuantity());
				ps.setDouble(3, orderitem.getTotal());
				ps.setString(4, orderitem.getProduct().getPid());
				ps.setString(5, orderitem.getOders().getOid());
				
				rs = ps.executeUpdate();
				if(rs>0) {
					System.out.println("orderitem数据插入成功");
				}else {
					System.out.println("orderitem数据插入失败");
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBHepler.getClose(conn, ps, null);
		}
	}

	@Override
	public List<OrderItem> findAllByOid(String oid) {
		List<OrderItem> oList = new ArrayList<OrderItem>();
		Connection conn = DBHepler.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from orderitem where oid = ?";
		ProductDao pd = new ProductDaoImpl();
		OrdersDao od = new OrdersDaoImpl();
		Orders orders = new Orders();
		orders.setOid(oid);
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, oid);
			rs = ps.executeQuery();
			while(rs.next()) {
				String itemid = rs.getString("itemid");
				int quantity = rs.getInt("quantity");
				double total = rs.getDouble("total");
				
				String pid = rs.getString("pid");
				Product product = pd.getone(pid);
				
				OrderItem oi =new OrderItem(itemid, quantity, total, product, orders);
				oList.add(oi);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBHepler.getClose(conn, ps, rs);
		}
		
		return oList;
	}

	@Override
	public void updateOrderItem(String oid, OrderItem oi) {
		Connection conn = DBHepler.getConn();
		PreparedStatement ps = null;
		int rs = 0;
		String sql = "update orderitem set quantity=? , total = ? , pid = ? where oid = ? and itemid = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, oi.getQuantity());
			ps.setDouble(2, oi.getTotal());
			ps.setString(3, oi.getProduct().getPid());
			ps.setString(4, oid);
			ps.setString(5, oi.getItemid());
			rs = ps.executeUpdate();
			if(rs==0) {
				String sql1 = "insert into orderitem values(?,?,?,?,?)";
				ps = conn.prepareStatement(sql1);
				ps.setString(1, oi.getItemid());
				ps.setInt(2, oi.getQuantity());
				ps.setDouble(3, oi.getTotal());
				ps.setString(4, oi.getProduct().getPid());
				ps.setString(5, oi.getOders().getOid());
				rs = ps.executeUpdate();
				if(rs>0)
				System.out.println("插入成功！！");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBHepler.getClose(conn, ps, null);
		}	
	}

	@Override
	public void deleteItem(OrderItem oitem) {
		Connection conn = DBHepler.getConn();
		PreparedStatement ps = null;
		int rs = 0;
		String sql = "delete from orderitem where itemid = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, oitem.getItemid());
			rs = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBHepler.getClose(conn, ps, null);
		}
		OrdersDao od = new OrdersDaoImpl();
		Orders orders = od.findByOid(oitem.getOders().getOid());
		orders.setTotal(orders.getTotal()-oitem.getTotal());
		List<OrderItem> oiList = orders.getOiList();
		for(OrderItem oi : oiList) {
			if(oi.getItemid().equals(oitem.getItemid())) {
				oiList.remove(oi);
				break;
			}
		}
		od.updateOrders(orders);
		
	}

	@Override
	public List<OrderItem> findAll() {
		List<OrderItem> oList = new ArrayList<OrderItem>();
		Connection conn = DBHepler.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from orderitem";
		ProductDao pd = new ProductDaoImpl();
		OrdersDao od = new OrdersDaoImpl();
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				String itemid = rs.getString("itemid");
				int quantity = rs.getInt("quantity");
				double total = rs.getDouble("total");
				
				String pid = rs.getString("pid");
				Product product = pd.getone(pid);
				Orders orders = new Orders();
				orders.setOid(rs.getString("oid"));
				OrderItem oi =new OrderItem(itemid, quantity, total, product, orders);
				oList.add(oi);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBHepler.getClose(conn, ps, rs);
		}
		
		return oList;
	}
	
}
