package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.DataUser;
import entities.User;

public class UserLogic {
	
	DataUser dataUser;
	
	public UserLogic() {
		dataUser = new DataUser();
	}
	
	public LinkedList<User> getAll() throws SQLException {
		return dataUser.getAll();
	}
	
	public User create(User user) throws SQLException {
		dataUser.create(user);
		
		return user;
	}
	
	public User update(User user) throws SQLException {
		return dataUser.update(user);
	}
	
	public User getById(User user) throws SQLException {
		return dataUser.getById(user);
	}
}
