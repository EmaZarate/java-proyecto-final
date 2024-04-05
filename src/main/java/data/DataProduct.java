package data;

import java.util.LinkedList;

import java.sql.*;

import entities.*;

public class DataProduct {
	
	public LinkedList<Product> getAll(boolean isAdmin) throws SQLException{
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Product> prods= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			
			String query = "SELECT prod.product_id, prod.name as name, prod.order_point, prod.sale_price, prod.purchase_price, prod.number , cat.category_id, cat.name as cat_name, prod.is_active, prod.number - prod.order_point AS difference "
					+ "FROM matisa.product prod inner join category cat on cat.category_id = prod.category_id ";
					
			
			if(isAdmin) {
				query +=  "ORDER BY difference";
			}
			else {
				query += "where prod.is_active and prod.number > 0";
			}
			
			rs= stmt.executeQuery(query);

			if(rs!=null) {
				while(rs.next()) {
					Product p =new Product();
					p.setProductId(rs.getInt("product_id"));
					p.setName(rs.getString("name"));
					p.setOrderPoint(rs.getInt("order_point"));
					p.setSalePrice(rs.getDouble("sale_price"));
					p.setPurchasePrice(rs.getDouble("purchase_price"));
					p.setNumber(rs.getInt("number"));
					p.setActive(rs.getBoolean("is_active"));
					
					Category category = new Category();
					
					category.setCategoryId(rs.getInt("category_id"));
					category.setName(rs.getString("cat_name"));
					
					p.setCategory(category);
					
					prods.add(p);
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
		
		return prods;
	}
	
	public LinkedList<Product> getProductsToCheck(Product[] productList) throws SQLException{
		Statement stmt=null;
		ResultSet rs=null;
		
		LinkedList<Product> prods= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			
			String query = "SELECT prod.product_id, prod.name as name, prod.order_point, prod.sale_price, prod.purchase_price, prod.number, prod.is_active, prod.category_id "
					+ "FROM matisa.product prod "
					+ "where product_id in (";
					
			
			for (int i = 0; i < productList.length; i++) {
				query = query + productList[i].getProductId();
				if(productList.length == (i+1)) {
					query = query + ") order by product_id";
				}
				else {
					query = query + ", ";
				}
			}
			
			
			rs= stmt.executeQuery(query);

			if(rs!=null) {
				while(rs.next()) {
					Product p =new Product();
					p.setProductId(rs.getInt("product_id"));
					p.setName(rs.getString("name"));
					p.setOrderPoint(rs.getInt("order_point"));
					p.setSalePrice(rs.getDouble("sale_price"));
					p.setPurchasePrice(rs.getDouble("purchase_price"));
					p.setNumber(rs.getInt("number"));
					p.setActive(rs.getBoolean("is_active"));
					p.setCategoryId(rs.getInt("category_id"));
					
					prods.add(p);
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
		
		return prods;
	}
	
	public LinkedList<Product> productSearch(Product prod) throws SQLException{
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Product> prods= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().prepareStatement(
					 "SELECT prod.product_id, prod.name as name, prod.order_point, prod.sale_price, prod.purchase_price,prod.number , cat.category_id, cat.name as cat_name, prod.is_active "
								+ "FROM matisa.product prod inner join category cat on cat.category_id = prod.category_id "
								+ "where prod.name like ?"
					);
			
			stmt.setString(1, "%"+prod.getName()+"%");
			
			rs= stmt.executeQuery();
			
			
			if(rs!=null) {
				while(rs.next()) {
					Product p =new Product();
					p.setProductId(rs.getInt("product_id"));
					p.setName(rs.getString("name"));
					p.setOrderPoint(rs.getInt("order_point"));
					p.setSalePrice(rs.getDouble("sale_price"));
					p.setPurchasePrice(rs.getDouble("purchase_price"));
					p.setNumber(rs.getInt("number"));
					p.setActive(rs.getBoolean("is_active"));
					
					Category category = new Category();
					
					category.setCategoryId(rs.getInt("category_id"));
					category.setName(rs.getString("cat_name"));
					
					p.setCategory(category);
					
					p.setCategoryId(category.getCategoryId());
					
					prods.add(p);
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
		
		return prods;
	}
	
	public Product getById(Product prod) throws SQLException{
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			stmt= DbConnector.getInstancia().getConn().prepareStatement(
					 "SELECT prod.product_id, prod.name as name, prod.order_point, prod.sale_price, prod.purchase_price,prod.number , cat.category_id, cat.name as cat_name, prod.is_active "
								+ "FROM matisa.product prod inner join category cat on cat.category_id = prod.category_id "
								+ "where prod.product_id = ?"
					);
			
			stmt.setInt(1, prod.getProductId());
			
			rs= stmt.executeQuery();
			
			
			if(rs!=null) {
				while(rs.next()) {
					prod.setProductId(rs.getInt("product_id"));
					prod.setName(rs.getString("name"));
					prod.setOrderPoint(rs.getInt("order_point"));
					prod.setSalePrice(rs.getDouble("sale_price"));
					prod.setPurchasePrice(rs.getDouble("purchase_price"));
					prod.setNumber(rs.getInt("number"));
					prod.setActive(rs.getBoolean("is_active"));
					
					Category category = new Category();
					
					category.setCategoryId(rs.getInt("category_id"));
					category.setName(rs.getString("cat_name"));
					
					prod.setCategory(category);
					
					prod.setCategoryId(category.getCategoryId());
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
		
		return prod;
	}
	
	public Product update(Product prod) throws SQLException{
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			stmt= DbConnector.getInstancia().getConn().prepareStatement(
					 "UPDATE  matisa.product "
						+ "SET name = ?, order_point = ?, sale_price = ?, purchase_price = ?, number = ?, category_id = ?, is_active = ? "
						+ "where product_id = ?"
					);
			
			stmt.setString(1, prod.getName());
			stmt.setInt(2, prod.getOrderPoint());
			stmt.setDouble(3, prod.getSalePrice());
			stmt.setDouble(4, prod.getPurchasePrice());
			stmt.setInt(5, prod.getNumber());
			stmt.setInt(6, prod.getCategoryId());
			stmt.setBoolean(7, prod.isActive());
			stmt.setInt(8, prod.getProductId());
			
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
		
		return prod;
	}
	
	public void create(Product prod) throws SQLException{
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			stmt= DbConnector.getInstancia().getConn().prepareStatement(
					 "insert into  matisa.product "
						+ "(name, order_point, sale_price, purchase_price, number, category_id, is_active) "
						+ "values (?, ?, ?, ?, ?, ?, ?)",
						PreparedStatement.RETURN_GENERATED_KEYS
					);
			
			stmt.setString(1, prod.getName());
			stmt.setInt(2, prod.getOrderPoint());
			stmt.setDouble(3, prod.getSalePrice());
			stmt.setDouble(4, prod.getPurchasePrice());
			stmt.setInt(5, prod.getNumber());
			stmt.setInt(6, prod.getCategoryId());
			stmt.setBoolean(7, prod.isActive());
			
			stmt.executeUpdate();
			
			rs=stmt.getGeneratedKeys();
            if(rs!=null && rs.next()){
            	prod.setProductId(rs.getInt(1));
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
	
	public void delete(Product prod) throws SQLException{
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			stmt= DbConnector.getInstancia().getConn().prepareStatement(
					 "DELETE from matisa.product "
						+ "WHERE product_id = ?;"
					);
			
			stmt.setInt(1, prod.getProductId());
			
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
	}
}
