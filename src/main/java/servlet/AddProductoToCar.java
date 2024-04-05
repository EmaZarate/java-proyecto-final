package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Product;
import entities.User;
import logic.ProductLogic;

/**
 * Servlet implementation class AddProductoToCar
 */
@WebServlet({ "/AddProductoToCar", "/addproductotocar", "/addProductoToCar" })
public class AddProductoToCar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductoToCar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String productId = request.getParameter("productid");
					
			if(productId != null) {
				int prodId = Integer.parseInt(productId);
				
				ProductLogic prodLog = new ProductLogic();
				Product prod = new Product();
				
				prod.setProductId(prodId);
				prodLog.getProductById(prod);	
				
				request.setAttribute("prod", prod);
			}
				
				request.getRequestDispatcher("WEB-INF/AddProductToCar.jsp").forward(request, response);
			
			
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			request.setAttribute("messageError", "Error recuperando el producto");
			request.getRequestDispatcher("Error.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
				String productId = request.getParameter("productId");
				
				int prodId = Integer.parseInt(productId);
				
				ProductLogic prodLog = new ProductLogic();
				Product prod = new Product();
				
				prod.setProductId(prodId);
				prodLog.getProductById(prod);
				
				if(Integer.parseInt(request.getParameter("numberSale")) > prod.getNumber()) {
					request.setAttribute("messageError", "Ingrese una cantidad valida");
				}
				
				prod.setNumberSale(Integer.parseInt(request.getParameter("numberSale")));
				
				LinkedList<Product> sellProdList = (LinkedList<Product>)request.getSession().getAttribute("sellProdList");
				
				if(sellProdList == null) {
					sellProdList = new LinkedList<Product>();
				}
				
				sellProdList.add(prod);

				
				request.getSession().setAttribute("sellProdList", sellProdList);
				
				LinkedList<Product> prods;
				
				User user = (User)request.getSession().getAttribute("user");
				boolean isAdmin = user.getRole().getName().equals("admin");
				
				prods = prodLog.getAll(isAdmin);
				
				request.setAttribute("prodList", prods);
				
				request.getRequestDispatcher("WEB-INF/ProductList.jsp").forward(request, response);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				request.setAttribute("messageError", "Error agregando el producto");
				request.getRequestDispatcher("Error.jsp").forward(request, response);
			}	
		
	}

}
