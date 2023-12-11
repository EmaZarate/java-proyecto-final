package data;

import java.util.LinkedList;

import java.sql.*;

import entities.*;

public class DataCategory {
	
	public LinkedList<Category> getAll() throws SQLException{
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Category> cats= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("SELECT * FROM matisa.category;");

			if(rs!=null) {
				while(rs.next()) {
					Category c =new Category();
					c.setCategoryId(rs.getInt("category_id"));
					c.setName(rs.getString("name"));
					c.setActive(rs.getBoolean("is_active"));
					cats.add(c);
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
		
		return cats;
	}
	
	public void create(Category cat) throws SQLException{
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			stmt= DbConnector.getInstancia().getConn().prepareStatement(
					 "insert into  matisa.category "
						+ "(name, is_active) "
						+ "values (?, ?)",
						PreparedStatement.RETURN_GENERATED_KEYS
					);
			
			stmt.setString(1, cat.getName());
			stmt.setBoolean(2, cat.isActive());

			stmt.executeUpdate();
			
			rs=stmt.getGeneratedKeys();
            if(rs!=null && rs.next()){
            	cat.setCategoryId(rs.getInt(1));
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
	
	public Category update(Category cat) throws SQLException{
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			stmt= DbConnector.getInstancia().getConn().prepareStatement(
					 "UPDATE  matisa.category "
						+ "SET name = ?, is_active= ? "
						+ "where category_id = ?"
					);
			
			stmt.setString(1, cat.getName());
			stmt.setBoolean(2, cat.isActive());
			stmt.setInt(3, cat.getCategoryId());
			
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
		
		return cat;
	}
	
	public Category getById(Category cat) throws SQLException{
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			stmt= DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT category_id, name, is_active "
							+ "FROM matisa.category cat "
								+ "where cat.category_id = ?"
					);
			
			stmt.setInt(1, cat.getCategoryId());
			
			rs= stmt.executeQuery();
			
			
			if(rs!=null) {
				while(rs.next()) {
					cat.setName(rs.getString("name"));
					cat.setActive(rs.getBoolean("is_active"));
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
		
		return cat;
	}
}
