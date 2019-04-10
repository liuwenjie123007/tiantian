package com.msr.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.msr.bean.User;
import com.msr.dao.UserDao;
import com.msr.utils.DBHepler;

public class UserDaoImpl implements UserDao {

	@Override
	public boolean login(String username, String pwd) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		conn = DBHepler.getConn();
		try {
			ps = conn.prepareStatement("select * from user where username=? and password=?");
			ps.setString(1, username);
			ps.setString(2, pwd);
			rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBHepler.getClose(conn, ps, rs);
		}
		return false;
	}

	@Override
	public void addUser(User newUser) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		conn = DBHepler.getConn();

		try {
			ps = conn.prepareStatement("insert into user values(?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, newUser.getUid());
			ps.setString(2, newUser.getUsername());
			ps.setString(3, newUser.getPassword());
			ps.setString(4, newUser.getName());
			ps.setString(5, newUser.getEmail());
			ps.setString(6, newUser.getTelephone());
			ps.setString(7, newUser.getBirthday());
			if (Integer.parseInt(newUser.getSex()) == 1) {
				ps.setString(8, "男");
			} else {
				ps.setString(8, "女");
			}
			ps.setInt(9, 1);
			ps.setString(10, newUser.getCode());

			int rs1 = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBHepler.getClose(conn, ps, rs);
		}
	}

	@Override
	public String[] getUserInfo(String username) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = DBHepler.getConn();
		String sql = "select u.username,u.telephone,o.address  from  user u,orders o where u.username = ? and u.uid = o.uid;";
		String[] strRs = new String[3];
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			for (int i = 0; i < 3; i++) {
				rs.next();
				strRs[i] = rs.getString((i + 1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBHepler.getClose(conn, ps, rs);
		}
		return strRs;
	}

	@Override
	public User getUserByUserName(String username) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = DBHepler.getConn();
		String sql = "select * from user where username = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				String uid = rs.getString(1);
				String password = rs.getString(3);
				String name = rs.getString(4);
				String email = rs.getString(5);
				String telephone = rs.getString(6);
				String birthday = rs.getString(7);
				String sex = rs.getString(8);
				int state = rs.getInt(9);
				String code = rs.getString(10);
				User user = new User(uid, username, password, name, email, telephone, birthday, sex, state, code);
				return user;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBHepler.getClose(conn, ps, rs);
		}
		
		
		
		return null;
	}

	@Override
	public List<User> findAll() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> uList = new ArrayList<User>();
		
		conn = DBHepler.getConn();
		String sql = "select * from User";
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				String uid = rs.getString(1); // varchar(32)
				String username = rs.getString(2); // varchar(20)
				String password = rs.getString(3); // varchar(20)
				String name = rs.getString(4); // varchar(20)
				String email = rs.getString(5); // varchar(30)
				String telephone = rs.getString(6); // varchar(20)
				String birthday = rs.getString(7); // date
				String sex = rs.getString(8); // varchar(10)
				int state = rs.getInt(9); // int(11)
				String code = rs.getString(10);// varchar(64)
				
				User user = new User(uid, username, password, name, email, telephone, birthday, sex, state, code);
				uList.add(user);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBHepler.getClose(conn, ps, rs);
		}
				
		
		
		return uList;
	}

	@Override
	public void modifyUser(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		conn = DBHepler.getConn();

		try {
			ps = conn.prepareStatement("update user set username=? , password=? , name = ? , email=? , telephone= ? , birthday= ? , sex= ? , state = ? , code= ? where uid = ?");
			ps.setString(10, user.getUid());
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getTelephone());
			ps.setString(6, user.getBirthday());
			if ("男".equals(user.getSex())) {
				ps.setString(7, "男");
			} else {
				ps.setString(7, "女");
			}
			ps.setInt(8, 1);
			ps.setString(9, user.getCode());

			int rs1 = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBHepler.getClose(conn, ps, rs);
		}
		
	}

	@Override
	public void deleteByUid(String uid) {
		Connection conn = DBHepler.getConn();
		PreparedStatement ps = null;
		int rs = 0;
		String sql = "delete from user where uid = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			rs = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBHepler.getClose(conn, ps, null);
		}
		
	}

	@Override
	public List<User> findByBirthday(String start, String end) {
		Connection conn = DBHepler.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> uList = new ArrayList<User>();
		String sql = "select * from user where birthday>=? and birthday<=?";
		try {
			ps = conn.prepareStatement(sql);
			if(start==null && end!=null) {
				ps.setString(1, "1800-01-01");
				ps.setString(2, end);
				rs = ps.executeQuery();
				while(rs.next()) {
					String uid = rs.getString(1); // varchar(32)
					String username = rs.getString(2); // varchar(20)
					String password = rs.getString(3); // varchar(20)
					String name = rs.getString(4); // varchar(20)
					String email = rs.getString(5); // varchar(30)
					String telephone = rs.getString(6); // varchar(20)
					String birthday = rs.getString(7); // date
					String sex = rs.getString(8); // varchar(10)
					int state = rs.getInt(9); // int(11)
					String code = rs.getString(10);// varchar(64)
					
					User user = new User(uid, username, password, name, email, telephone, birthday, sex, state, code);
					uList.add(user);
				}
				return uList;
				
			}else if(start!=null && end==null) {
				ps.setString(1, start);
				ps.setString(2, "2100-12-31");
				rs = ps.executeQuery();
				while(rs.next()) {
					String uid = rs.getString(1); // varchar(32)
					String username = rs.getString(2); // varchar(20)
					String password = rs.getString(3); // varchar(20)
					String name = rs.getString(4); // varchar(20)
					String email = rs.getString(5); // varchar(30)
					String telephone = rs.getString(6); // varchar(20)
					String birthday = rs.getString(7); // date
					String sex = rs.getString(8); // varchar(10)
					int state = rs.getInt(9); // int(11)
					String code = rs.getString(10);// varchar(64)
					
					User user = new User(uid, username, password, name, email, telephone, birthday, sex, state, code);
					uList.add(user);
				}
				return uList;
			}else {
				ps.setString(1, start);
				ps.setString(2, end);
				rs = ps.executeQuery();
				while(rs.next()) {
					String uid = rs.getString(1); // varchar(32)
					String username = rs.getString(2); // varchar(20)
					String password = rs.getString(3); // varchar(20)
					String name = rs.getString(4); // varchar(20)
					String email = rs.getString(5); // varchar(30)
					String telephone = rs.getString(6); // varchar(20)
					String birthday = rs.getString(7); // date
					String sex = rs.getString(8); // varchar(10)
					int state = rs.getInt(9); // int(11)
					String code = rs.getString(10);// varchar(64)
					
					User user = new User(uid, username, password, name, email, telephone, birthday, sex, state, code);
					uList.add(user);
				}
				return uList;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBHepler.getClose(conn, ps, rs);
		}
		
		return null;
	}
}
