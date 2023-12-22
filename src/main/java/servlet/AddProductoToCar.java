package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Category;
import entities.Product;
import entities.Supplier;
import logic.CategoryLogic;
import logic.ProductLogic;
import logic.SupplierLogic;

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
				
				if(Integer.parseInt(request.getParameter("number")) > prod.getNumber()) {
					request.setAttribute("messageError", "Ingrese una cantidad valida");
				}
				
				prod.setNumber(Integer.parseInt(request.getParameter("number")));
				
				LinkedList<Product> sellProdList = (LinkedList<Product>)request.getSession().getAttribute("sellProdList");
				
				if(sellProdList == null) {
					sellProdList = new LinkedList<Product>();
				}
				
				sellProdList.add(prod);

				
				request.getSession().setAttribute("sellProdList", sellProdList);
				
				LinkedList<Product> prods;
				
				prods = prodLog.getAll();
				
				request.setAttribute("prodList", prods);
				
				request.getRequestDispatcher("WEB-INF/ProductList.jsp").forward(request, response);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			

		
		
	}

}
