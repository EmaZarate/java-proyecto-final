package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.LinkedList;

import entities.Product;
import entities.Sale;
import entities.SaleDetails;
import entities.User;

public class DataSale {
	
	public void create(Sale sale) throws SQLException {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			stmt= DbConnector.getInstancia().getConn().prepareStatement(
					 "insert into  matisa.sale "
						+ "(date, user_id, state) "
						+ "values (?, ?, ?)",
						PreparedStatement.RETURN_GENERATED_KEYS
					);
			
			stmt.setTimestamp(1, java.sql.Timestamp.valueOf(sale.getDate()));
			stmt.setInt(2, sale.getUserId());
			stmt.setString(3, sale.getState());

			stmt.executeUpdate();
			
			rs=stmt.getGeneratedKeys();
            if(rs!=null && rs.next()){
            	sale.setSaleId(rs.getInt(1));
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
	
	public void createSaleDetails(int saleId, Product[] products) throws SQLException {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			
			String query = "insert into  matisa.sale_details "
					+ "(sale_id, product_id, number) ";
			
			for (int i = 0; i < products.length; i++) {
				if(i == 0) {
					query = query + "values (?, ?, ?)";					
				}
				else {
					query = query + "(?, ?, ?)";
				}
				if(products.length != (i+1)) {
					query = query + ", ";
				}
			}
			
			stmt= DbConnector.getInstancia().getConn().prepareStatement(
					query
					);
			
			
			for (int i = 0; i < products.length; i++) {

					stmt.setInt((i*3)+ 1, saleId);
					stmt.setInt((i*3) + 2, products[i].getProductId());	
					stmt.setInt((i*3) + 3, products[i].getNumberSale());
			}

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
	
	public LinkedList<Sale> getAll() throws SQLException{
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Sale> sales= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery(
					 "SELECT sale.sale_id, sale.user_id, sale.date, sale.state, us.name, us.surname  "
					+ "FROM matisa.sale as sale inner join user as us on sale.user_id = us.user_id"
					);

			if(rs!=null) {
				while(rs.next()) {
					Sale s =new Sale();
					s.setSaleId(rs.getInt("sale_id"));
					s.setUserId(rs.getInt("user_id"));
					s.setDate(rs.getObject("date",LocalDateTime.class));
					s.setState(rs.getString("state"));
					
					User user = new User();
					
					user.setId(rs.getInt("user_id"));
					user.setName(rs.getString("name"));
					user.setSurname(rs.getString("surname"));
					
					s.setUser(user);
					
					sales.add(s);
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
		
		return sales;
	}
	
	public Sale getSaleDetails(Sale sale) throws SQLException{
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			stmt= DbConnector.getInstancia().getConn().prepareStatement(
					 "SELECT sale.sale_id, sale.user_id, sale.date, sale.state, details.number, us.name as user_name, us.surname as user_surname, details.product_id, prod.name as prod_name, prod.sale_price\r\n"
					 + "FROM matisa.sale as sale inner join user as us on sale.user_id = us.user_id\r\n"
					 + "inner join sale_details as details on sale.sale_id = details.sale_id\r\n"
					 + "inner join product as prod on details.product_id = prod.product_id\r\n"
					 + "where sale.sale_id = ?"
					);
			
			stmt.setInt(1, sale.getSaleId());
			
			rs= stmt.executeQuery();
			
			User user = new User();
			
			sale.setUser(user);
			
			LinkedList<SaleDetails> saleDetailsList = new LinkedList<SaleDetails>();
			
			
			if(rs!=null) {
				while(rs.next()) {
					sale.setDate(rs.getObject("date",LocalDateTime.class));
					sale.setUserId(rs.getInt("user_id"));
					sale.setState(rs.getString("state"));
					
					sale.getUser().setId(rs.getInt("user_id"));
					sale.getUser().setName(rs.getString("user_name"));
					sale.getUser().setSurname(rs.getString("user_surname"));
					
					SaleDetails saleDetails = new SaleDetails();
					
					saleDetails.setNumber(rs.getInt("number"));
					
					Product prod = new Product();
					
					prod.setProductId(rs.getInt("product_id"));
					prod.setName(rs.getString("prod_name"));
					prod.setSalePrice(rs.getDouble("sale_price"));
					
					saleDetails.setProduct(prod);
					
					saleDetailsList.add(saleDetails);
				}
			}
			
			sale.setSaleDetails(saleDetailsList);
			
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
		
		return sale;
	}
	
	public Sale update(Sale sale) throws SQLException{
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try {
			stmt= DbConnector.getInstancia().getConn().prepareStatement(
					 "UPDATE  matisa.sale "
						+ "SET state = ? "
						+ "where sale_id = ?"
					);
			
			stmt.setString(1, sale.getState());
			stmt.setInt(2, sale.getSaleId());
			
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
		
		return sale;
	}
	
	

}
