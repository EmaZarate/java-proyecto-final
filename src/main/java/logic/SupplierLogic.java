package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.DataSupplier;
import entities.Supplier;
import entities.Product;

public class SupplierLogic {
	DataSupplier dataSupplier;
	
	public SupplierLogic() {
		dataSupplier = new DataSupplier();
	}
	
	public LinkedList<Supplier> getAll() throws SQLException {
		return dataSupplier.getAll();
	}
	
	public Supplier create(Supplier supplier) throws SQLException {
		dataSupplier.create(supplier);
		
		return supplier;
	}
	
	public Supplier update(Supplier supplier) throws SQLException {
		return dataSupplier.update(supplier);
	}
	
	public Supplier getById(Supplier supplier) throws SQLException {
		return dataSupplier.getById(supplier);
	}
	
	public LinkedList<Integer> getAllSuppierByProduct(Product prod) throws SQLException{
		return dataSupplier.getAllSuppierByProduct(prod);
	}
	
	public void deleteSupplierProducts(Product prod) throws SQLException{
		dataSupplier.deleteSupplierProducts(prod);
	}
	
	public void addSupplierToProduct(Product prod, int[] supplierIds) throws SQLException {
		dataSupplier.createSupplierProduct(prod, supplierIds);
	}
}
