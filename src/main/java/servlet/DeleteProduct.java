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
 * Servlet implementation class DeleteProduct
 */
@WebServlet({ "/DeleteProduct", "/deleteProduct", "/deleteproduct" })
public class DeleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Product prod = new Product();
		
		prod.setProductId(Integer.parseInt(request.getParameter("productid")));
		
		ProductLogic prodLog = new ProductLogic();
		
		LinkedList<Product> prods;
		
		try {
			
			prodLog.delete(prod);
			
			User user = (User)request.getSession().getAttribute("user");
			boolean isAdmin = user.getRole().getName().equals("admin");
			
			prods = prodLog.getAll(isAdmin);
			
			request.setAttribute("prodList", prods);
			
			request.getRequestDispatcher("WEB-INF/ProductList.jsp").forward(request, response);
		} catch (SQLException e) {
			
			request.setAttribute("messageError", "Error borrando el producto");
			request.getRequestDispatcher("Error.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
