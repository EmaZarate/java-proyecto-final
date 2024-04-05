package logic;

import java.sql.SQLException;

import data.*;
import entities.*;

public class Login {
	private DataUser du;
	
	public Login() {
		du=new DataUser();
	}
	
	public User validate(User u) throws SQLException{
		return du.getByUser(u);
	}
}
