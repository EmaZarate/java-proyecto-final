package data;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.*;


public class DataRole {
	
	public void setRole(User user) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					  "select role.* "
					+ "from role "
					+ "where user_id=?"
					);
			stmt.setInt(1, user.getId());
			rs= stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Role r= new Role();
					r.setId(rs.getInt("id"));
					r.setName(rs.getString("name"));
					user.setRole(r);
				}
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
	}
	
	public LinkedList<Role> getAll() {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Role> roles= new LinkedList<>();
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("SELECT * FROM matisa.role");

			rs= stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Role r= new Role();
					r.setId(rs.getInt("role_id"));
					r.setName(rs.getString("name"));
					
					roles.add(r);
				}
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
		
		return roles;
	}

}
