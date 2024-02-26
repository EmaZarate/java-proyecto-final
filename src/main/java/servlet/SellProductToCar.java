package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Product;
import entities.Sale;
import entities.User;
import logic.ProductLogic;
import logic.SaleLogic;

/**
 * Servlet implementation class SellProductToCar
 */
@WebServlet({ "/SellProductToCar", "/sellProductToCar", "/sellproducttocar" })
public class SellProductToCar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellProductToCar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LinkedList<Product> sellProdList = (LinkedList<Product>)request.getSession().getAttribute("sellProdList");
		
		User user = (User)request.getSession().getAttribute("user");
		
		SaleLogic saleLog = new SaleLogic();
		ProductLogic prodLogic = new ProductLogic();
		
		Sale sale = new Sale();
		
		sale.setDate(LocalDateTime.now());
		sale.setState("Completado");
		sale.setUserId(user.getId());
		
		try {
			saleLog.createSale(sale);
			
			Object[] array = sellProdList.toArray();
			
			int length = array.length;
			
			Product[] prodArray = new Product[length];
			
			for (int i = 0; i < length; i++) {
				prodArray[i] = (Product)array[i];
			}
			
			saleLog.createSaleDetails(sale.getSaleId(), prodArray);
			
			for (Iterator iterator = sellProdList.iterator(); iterator.hasNext();) {
				Product product = (Product) iterator.next();
				int num = product.getNumber() - product.getNumberSale();
				product.setNumber(num);
				prodLogic.update(product);
			}
			
			LinkedList<Product> prods = prodLogic.getAll();
			request.setAttribute("prodList", prods);
			
			request.getSession().setAttribute("sellProdList", null);
			
			request.getRequestDispatcher("WEB-INF/ProductList.jsp").forward(request, response);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
