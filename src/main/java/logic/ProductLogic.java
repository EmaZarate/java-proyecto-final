package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.*;
import entities.*;

public class ProductLogic {
private DataProduct dataProd;
	
	public ProductLogic() {
		dataProd=new DataProduct();
	}
	
	public LinkedList<Product> getAll() throws SQLException{
		return dataProd.getAll();
	}
	
	public LinkedList<Product> productSearch(Product prod) throws SQLException{
		return dataProd.productSearch(prod);
	}
	
	public Product getProductById(Product prod) throws SQLException{
		return dataProd.getById(prod);
	}
	
	public Product update(Product prod) throws SQLException{
		return dataProd.update(prod);
	}
	
	public Product create(Product prod) throws SQLException{
		dataProd.create(prod);
		
		return prod;
	}
	
	public void delete(Product prod) throws SQLException{
		dataProd.delete(prod);
	}
}
