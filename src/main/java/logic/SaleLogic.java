package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.DataSale;
import entities.Product;
import entities.SaleDetails;
import entities.Sale;

public class SaleLogic {
	private DataSale dataSale;
	
	public SaleLogic() {
		dataSale = new DataSale();
	}
	
	public Sale createSale(Sale sale) throws SQLException {
		this.dataSale.create(sale);
		
		return sale;
	}
	
	public void createSaleDetails(int saleId, Product[] productList) throws SQLException {
		this.dataSale.createSaleDetails(saleId, productList);
	}
	
	public LinkedList<Sale> getAll() throws SQLException {
		return this.dataSale.getAll();
	}
	
	public Sale getSaleDetails(Sale sale) throws SQLException {
		return this.dataSale.getSaleDetails(sale);
	}
	
	public Sale update(Sale sale) throws SQLException{
		return dataSale.update(sale);
	}
	
	public static double showTotal(LinkedList<SaleDetails> details) {
		double total = 0;
		
		for (SaleDetails detail : details) {
			total = total + (detail.getNumber() * detail.getProduct().getSalePrice());
		}
		
		return total;
	}
}
