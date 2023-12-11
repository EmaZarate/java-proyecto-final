package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entities.Supplier;

public class DataSupplier {
	
	public LinkedList<Supplier> getAll() throws SQLException{
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Supplier> suppliers= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery(
					 "SELECT * "
					+ "FROM matisa.supplier"
					);

			if(rs!=null) {
				while(rs.next()) {
					Supplier s =new Supplier();
					s.setSupplierId(rs.getInt("supplier_id"));
					s.setName(rs.getString("name"));
					s.setSurname(rs.getString("surname"));
					s.setEmail(rs.getString("email"));
					s.setPhone(rs.getString("phone"));
					s.setActive(rs.getBoolean("is_active"));
					
					suppliers.add(s);
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
		
		return suppliers;
	}
	
	public void create(Supplier supplier) throws SQLException{
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			stmt= DbConnector.getInstancia().getConn().prepareStatement(
					 "insert into  matisa.supplier "
						+ "(name, surname, email, phone, is_active) "
						+ "values (?, ?, ?, ?, ?)",
						PreparedStatement.RETURN_GENERATED_KEYS
					);
			
			stmt.setString(1, supplier.getName());
			stmt.setString(2, supplier.getSurname());
			stmt.setString(3, supplier.getEmail());
			stmt.setString(4, supplier.getPhone());
			stmt.setBoolean(5, supplier.isActive());

			stmt.executeUpdate();
			
			rs=stmt.getGeneratedKeys();
            if(rs!=null && rs.next()){
            	supplier.setSupplierId(rs.getInt(1));
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
	
	public Supplier update(Supplier supplier) throws SQLException{
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			stmt= DbConnector.getInstancia().getConn().prepareStatement(
					 "UPDATE  matisa.supplier "
						+ "SET name = ?, surname = ?, email = ?, phone = ?, is_active= ? "
						+ "where supplier_id = ?"
					);
			
			stmt.setString(1, supplier.getName());
			stmt.setString(2, supplier.getSurname());
			stmt.setString(3, supplier.getEmail());
			stmt.setString(4, supplier.getPhone());
			stmt.setBoolean(5, supplier.isActive());
			stmt.setInt(6, supplier.getSupplierId());
			
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
		
		return supplier;
	}
	
	public Supplier getById(Supplier supplier) throws SQLException{
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			stmt= DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT * "
							+ "FROM matisa.supplier sup "
								+ "where sup.supplier_id = ?"
					);
			
			stmt.setInt(1, supplier.getSupplierId());
			
			rs= stmt.executeQuery();
			
			
			if(rs!=null) {
				while(rs.next()) {
					supplier.setName(rs.getString("name"));
					supplier.setSurname(rs.getString("surname"));
					supplier.setEmail(rs.getString("email"));
					supplier.setPhone(rs.getString("phone"));
					supplier.setActive(rs.getBoolean("is_active"));
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
		
		return supplier;
	}
}
