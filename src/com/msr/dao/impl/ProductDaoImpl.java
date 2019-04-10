package com.msr.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.msr.bean.Product;
import com.msr.dao.ProductDao;
import com.msr.utils.DBHepler;
import com.msr.utils.PageUtils;

public class ProductDaoImpl implements ProductDao {

	@Override
	public Map<String, List> getFI(String cid) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<String> images = new ArrayList<String>();
		List<Double> prices = new ArrayList<Double>();
		List<String> pnames = new ArrayList<String>();
		List<String> pid = new ArrayList<String>();

		conn = DBHepler.getConn();
		String sql = "select * from product where cid = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cid);
			rs = ps.executeQuery();

			while (rs.next()) {
				pid.add(rs.getString("pid"));
				images.add(rs.getString("pimage"));
				prices.add(rs.getDouble("shop_price"));
				pnames.add(rs.getString("pname"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBHepler.getClose(conn, ps, rs);
		}

		Random r = new Random();
		List<Integer> index = new ArrayList<>();

		int c = r.nextInt(images.size());
		index.add(c);

		for (int i = 0; i < 3; i++) {
			int a = -1;
			while (a == -1) {
				int count = 0;
				int newr = r.nextInt(images.size());
				for (Integer b : index) {
					if (b != newr) {
						count++;
					}
				}
				if (count == index.size()) {
					a = newr;
					index.add(a);
				}
			}
		}

		List<String> images1 = new ArrayList<String>();
		List<Double> prices1 = new ArrayList<Double>();
		List<String> pnames1 = new ArrayList<String>();
		List<String> pid1 = new ArrayList<String>();

		for (int i = 0; i < index.size(); i++) {
			images1.add(images.get(index.get(i)));
			prices1.add(prices.get(index.get(i)));
			pnames1.add(pnames.get(index.get(i)));
			pid1.add(pid.get(index.get(i)));
		}

		Map<String, List> map = new HashMap<String, List>();
		map.put("pimages", images1);
		map.put("prices", prices1);
		map.put("pnames", pnames1);
		map.put("pid", pid1);

		return map;
	}

	@Override
	public Product getone(String pid) {
		String sql = "select * from product where pid= ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = DBHepler.getConn();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, pid);
			rs = ps.executeQuery();
			while (rs.next()) {
				/**
				 * private String pid; private String pname; private double shop_price; private
				 * String pimage; private String pdate; private String pdesc; private String
				 * pflag; private String cid;
				 */
				String pname = rs.getString("pname");
				double shop_price = rs.getDouble("shop_price");
				String pimage = rs.getString("pimage");
				String pdate = rs.getString("pdate");
				String pdesc = rs.getString("pdesc");
				String pflag = rs.getString("pflag");
				String cid = rs.getString("cid");
				Product product = new Product(pid, pname, shop_price, pimage, pdate, pdesc, pflag, cid);
				return product;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBHepler.getClose(conn, ps, rs);
		}

		return null;
	}

	public List<Product> findByCid(String cid) {
		String sql = "select * from product where cid = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Product> pl = new ArrayList<Product>();

		conn = DBHepler.getConn();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cid);
			rs = ps.executeQuery();

			while (rs.next()) {
				String pid = rs.getString("pid");
				String pname = rs.getString("pname");
				double shop_price = rs.getDouble("shop_price");
				String pimage = rs.getString("pimage");
				String pdate = rs.getString("pdate");
				String pdesc = rs.getString("pdesc");
				String pflag = rs.getString("pflag");
				Product product = new Product(pid, pname, shop_price, pimage, pdate, pdesc, pflag, cid);
				pl.add(product);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBHepler.getClose(conn, ps, rs);
		}

		return pl;
	}

	@Override
	public List<Product> OrderbyDate(String cid) {
		String sql = "select * from product where cid =?  order by pdate desc";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Product> pl = new ArrayList<Product>();

		conn = DBHepler.getConn();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cid);
			rs = ps.executeQuery();

			while (rs.next()) {
				String pid = rs.getString("pid");
				String pname = rs.getString("pname");
				double shop_price = rs.getDouble("shop_price");
				String pimage = rs.getString("pimage");
				String pdate = rs.getString("pdate");
				String pdesc = rs.getString("pdesc");
				String pflag = rs.getString("pflag");
				Product product = new Product(pid, pname, shop_price, pimage, pdate, pdesc, pflag, cid);
				pl.add(product);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBHepler.getClose(conn, ps, rs);
		}

		return pl;
	}

	@Override
	public List<Product> findByCidPage(String cid, int pageIndex, int everySize) {
		
		int biginIndex = everySize * (pageIndex-1);
		
		
		String sql = "select * from product where cid=?  limit ?,?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Product> pl = new ArrayList<Product>();

		conn = DBHepler.getConn();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cid);
			ps.setInt(2, biginIndex);
			ps.setInt(3, everySize);
			rs = ps.executeQuery();

			while (rs.next()) {
				String pid = rs.getString("pid");
				String pname = rs.getString("pname");
				double shop_price = rs.getDouble("shop_price");
				String pimage = rs.getString("pimage");
				String pdate = rs.getString("pdate");
				String pdesc = rs.getString("pdesc");
				String pflag = rs.getString("pflag");
				Product product = new Product(pid, pname, shop_price, pimage, pdate, pdesc, pflag, cid);
				pl.add(product);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBHepler.getClose(conn, ps, rs);
		}

		return pl;
	}

	@Override
	public List<Product> findAll() {
		List<Product> pList = new ArrayList<Product>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		conn = DBHepler.getConn();
		String sql = "select * from product";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				String cid = rs.getString("cid");
				String pid = rs.getString("pid");
				String pname = rs.getString("pname");
				double shop_price = rs.getDouble("shop_price");
				String pimage = rs.getString("pimage");
				String pdate = rs.getString("pdate");
				String pdesc = rs.getString("pdesc");
				String pflag = rs.getString("pflag");
				Product product = new Product(pid, pname, shop_price, pimage, pdate, pdesc, pflag, cid);
				pList.add(product);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBHepler.getClose(conn, ps, rs);
		}
		return pList;
	}

	@Override
	public void ModifyProduct(Product product) {
		Connection conn = DBHepler.getConn();
		PreparedStatement ps = null;
		int rs = 0;
		String sql = "update product set pname=? , shop_price=? , pimage=? , pdate=? , pdesc=? , pflag=? , cid=? where pid = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, product.getPname());
			ps.setDouble(2, product.getShop_price());
			ps.setString(3, product.getPimage());
			ps.setString(4, product.getPdate());
			ps.setString(5, product.getPdesc());
			ps.setString(6, product.getPflag());
			ps.setString(7, product.getCid());
			ps.setString(8, product.getPid());
			ps.executeLargeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBHepler.getClose(conn, ps, null);
		}
		
		
	}

	@Override
	public void addProduct(Product product) {
		Connection conn = DBHepler.getConn();
		PreparedStatement ps = null;
		int rs = 0;
		String sql = "insert into product values(?,?,?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, product.getPid());
			ps.setString(2, product.getPname());
			ps.setDouble(3, product.getShop_price());
			ps.setString(4, product.getPimage());
			ps.setString(5, product.getPdate());
			ps.setString(6, product.getPdesc());
			ps.setString(7, product.getPflag());
			ps.setString(8, product.getCid());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBHepler.getClose(conn, ps, null);
		}
		
		
	}

	@Override
	public void deleteProduct(String pid) {
		Connection conn = DBHepler.getConn();
		PreparedStatement ps = null;
		int rs = 0;
		String sql = "delete from product where pid = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, pid);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBHepler.getClose(conn, ps, null);
		}
		
		
	}

	@Override
	public Product findByPname(String pname) {
		String sql = "select * from product where pname= ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = DBHepler.getConn();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, pname);
			rs = ps.executeQuery();
			while (rs.next()) {
				/**
				 * private String pid; private String pname; private double shop_price; private
				 * String pimage; private String pdate; private String pdesc; private String
				 * pflag; private String cid;
				 */
				String pid = rs.getString("pid");
				double shop_price = rs.getDouble("shop_price");
				String pimage = rs.getString("pimage");
				String pdate = rs.getString("pdate");
				String pdesc = rs.getString("pdesc");
				String pflag = rs.getString("pflag");
				String cid = rs.getString("cid");
				Product product = new Product(pid, pname, shop_price, pimage, pdate, pdesc, pflag, cid);
				return product;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBHepler.getClose(conn, ps, rs);
		}

		return null;
	}

}
