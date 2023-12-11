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
import logic.ProductLogic;

/**
 * Servlet implementation class ProductList
 */
@WebServlet({ "/ProductList", "/productList", "/productlist" })
public class ProductList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductLogic prodLog = new ProductLogic();
		
		LinkedList<Product> prods;
		
		try {
			
			prods = prodLog.getAll();
			
			request.setAttribute("prodList", prods);
			
			request.getRequestDispatcher("WEB-INF/ProductList.jsp").forward(request, response);
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			request.setAttribute("messageError", "Error recuperando los productos");
			request.getRequestDispatcher("Error.jsp").forward(request, response);
		}
	}

}
