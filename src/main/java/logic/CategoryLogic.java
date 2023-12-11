package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.DataCategory;
import entities.Category;

public class CategoryLogic {
private DataCategory dataCat;
	
	public CategoryLogic() {
		dataCat = new DataCategory();
	}
	public LinkedList<Category> getAll() throws SQLException{
		return dataCat.getAll();
	}
	
	public Category getById(Category cat) throws SQLException{
		return dataCat.getById(cat);
	}
	
	public Category update(Category cat) throws SQLException{
		return dataCat.update(cat);
	}
	
	public Category create(Category cat) throws SQLException{
		dataCat.create(cat);
		
		return cat;
	}
}
