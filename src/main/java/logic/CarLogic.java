package logic;

import java.util.LinkedList;

import entities.Product;

public class CarLogic {
	
	public static double showTotal(LinkedList<Product> prods) {
		double total = 0;
		
		for (Product product : prods) {
			total = total + (product.getNumberSale() * product.getSalePrice());
		}
		
		return total;
	}

}
