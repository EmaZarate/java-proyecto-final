package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
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
		try {
			
			LinkedList<String> ErrorNumberProdsMessage = new LinkedList<String>();
			LinkedList<Product> sellProdList = (LinkedList<Product>)request.getSession().getAttribute("sellProdList");
			
			Collections.sort(sellProdList, new Comparator<Product>() {
			    @Override
			    public int compare(Product o1, Product o2) {
			        return o1.getProductId() - o2.getProductId();
			    }
			});
			
			Object[] array = sellProdList.toArray();
			
			int length = array.length;
			
			Product[] prodArray = new Product[length];
			
			for (int i = 0; i < length; i++) {
				prodArray[i] = (Product)array[i];
			}
			
			SaleLogic saleLog = new SaleLogic();
			ProductLogic prodLogic = new ProductLogic();
			
			LinkedList<Product> prodsToCheck = prodLogic.getProductsToCheck(prodArray);
			
			for (int i = 0; i < prodsToCheck.size(); i++) {
				if(prodArray[i].getNumberSale() > prodsToCheck.get(i).getNumber()) {
					ErrorNumberProdsMessage.add(prodsToCheck.get(i).getName() + " cantidad disponible: " + prodsToCheck.get(i).getNumber());
				}
			}
			
			if(ErrorNumberProdsMessage.size() > 0) {
				request.setAttribute("errorNumberProdsMessage", ErrorNumberProdsMessage);
				request.getRequestDispatcher("WEB-INF/Car.jsp").forward(request, response);
				return;
			}
			
			User user = (User)request.getSession().getAttribute("user");
			
			Sale sale = new Sale();
			
			sale.setDate(LocalDateTime.now());
			
			boolean isAdmin = user.getRole().getName().equals("admin");
			
			if(isAdmin) { 
				sale.setState("Completado");			
			}
			else {
				sale.setState("Incompleto");
			}
			
			sale.setUserId(user.getId());
		
		
			saleLog.createSale(sale);
			
			saleLog.createSaleDetails(sale.getSaleId(), prodArray);
			
			for (int i = 0; i < prodsToCheck.size(); i++) {
				Product product = prodsToCheck.get(i);
				int num = product.getNumber() - prodArray[i].getNumberSale();
				product.setNumber(num);
				prodLogic.update(product);
			}
			
			LinkedList<Product> prods = prodLogic.getAll(isAdmin);
			request.setAttribute("prodList", prods);
			
			request.getSession().setAttribute("sellProdList", null);
			
			request.getRequestDispatcher("WEB-INF/ProductList.jsp").forward(request, response);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
