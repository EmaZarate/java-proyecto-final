package data;

import entities.*;

import java.sql.*;
import java.util.LinkedList;

public class DataUser {
	
	public User getByUser(User user) {
		User u=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select user_id,u.name,surname,email,r.role_id, r.name as role_name from user u "
					+ "inner join role r on r.role_id = u.role_id where email=? and password=? and u.is_active"
					);
			stmt.setString(1, user.getEmail());
			stmt.setString(2, user.getPassword());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				u=new User();
				u.setId(rs.getInt("user_id"));
				u.setName(rs.getString("name"));
				u.setSurname(rs.getString("surname"));
				u.setEmail(rs.getString("email"));
				Role role = new Role(); 
				role.setId(rs.getInt("role_id"));
				role.setName(rs.getString("role_name"));
				u.setRole(role);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return u;
	}
	
	public LinkedList<User> getAll() throws SQLException{
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<User> users= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery(
					 "SELECT user_id, us.name, surname, email, role.role_id, role.name as roleName, is_active "
					+ "FROM matisa.user us inner join role on us.role_id = role.role_id"
					);

			if(rs!=null) {
				while(rs.next()) {
					User u =new User();
					u.setId(rs.getInt("user_id"));
					u.setName(rs.getString("name"));
					u.setSurname(rs.getString("surname"));
					u.setEmail(rs.getString("email"));
					u.setActive(rs.getBoolean("is_active"));
					
					Role role = new Role();
					
					role.setId(rs.getInt("role_id"));
					role.setName(rs.getString("roleName"));
					
					u.setRole(role);
					
					users.add(u);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
		}
		
		return users;
	}
	
	public void create(User user) throws SQLException{
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			stmt= DbConnector.getInstancia().getConn().prepareStatement(
					 "insert into  matisa.user "
						+ "(name, surname, email, password, role_id, is_active) "
						+ "values (?, ?, ?, ?, ?, ?)",
						PreparedStatement.RETURN_GENERATED_KEYS
					);
			
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getSurname());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getPassword());
			stmt.setInt(5, user.getRoleId());
			stmt.setBoolean(6, user.isActive());

			stmt.executeUpdate();
			
			rs=stmt.getGeneratedKeys();
            if(rs!=null && rs.next()){
            	user.setId(rs.getInt(1));
            }
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
		}
	}
	
	public User update(User user) throws SQLException{
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			stmt= DbConnector.getInstancia().getConn().prepareStatement(
					 "UPDATE  matisa.user "
						+ "SET name = ?, surname = ?, email = ?, role_id = ?, password = ?, is_active= ? "
						+ "where user_id = ?"
					);
			
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getSurname());
			stmt.setString(3, user.getEmail());
			stmt.setInt(4, user.getRoleId());
			stmt.setString(5, user.getPassword());
			stmt.setBoolean(6, user.isActive());
			stmt.setInt(7, user.getId());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
		}
		
		return user;
	}
	
	public User getById(User user) throws SQLException{
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			stmt= DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT user_id, us.name, surname, email, role.role_id, role.name as roleName, password, is_active "
							+ "FROM matisa.user us inner join role on us.role_id = role.role_id "
								+ "where us.user_id = ?"
					);
			
			stmt.setInt(1, user.getId());
			
			rs= stmt.executeQuery();
			
			
			if(rs!=null) {
				while(rs.next()) {
					user.setName(rs.getString("name"));
					user.setSurname(rs.getString("surname"));
					user.setEmail(rs.getString("email"));
					user.setPassword(rs.getString("password"));
					user.setActive(rs.getBoolean("is_active"));
					
					Role role = new Role();
					
					role.setId(rs.getInt("role_id"));
					role.setName(rs.getString("roleName"));
					
					user.setRole(role);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
		}
		
		return user;
	}
}
