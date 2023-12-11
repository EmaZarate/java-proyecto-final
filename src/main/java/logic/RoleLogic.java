package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.DataRole;
import entities.Role;

public class RoleLogic {
	
	private DataRole dataRole;
	
	public RoleLogic() {
		dataRole = new DataRole();
	}
	
	public LinkedList<Role> getAll() throws SQLException {
		return dataRole.getAll();
	}

}
