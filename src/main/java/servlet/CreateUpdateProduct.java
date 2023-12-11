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
import logic.ProductLogic;
import logic.CategoryLogic;

/**
 * Servlet implementation class CreateUpdateProduct
 */
@WebServlet({ "/CreateUpdateProduct", "/createupdateproduct", "/createUpdateProduct" })
public class CreateUpdateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUpdateProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
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
			
			CategoryLogic catLog = new CategoryLogic();
			LinkedList<Category> cats = catLog.getAll();
			request.setAttribute("cats", cats);
			
			request.getRequestDispatcher("WEB-INF/CreateUpdateProduct.jsp").forward(request, response);
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
		// TODO Auto-generated method stub
		
		try {
			Product prod = new Product();
			
			prod.setPurchasePrice(Double.parseDouble(request.getParameter("purchasePrice")));
			prod.setSalePrice(Double.parseDouble(request.getParameter("salePrice")));
			prod.setName(request.getParameter("name"));
			prod.setOrderPoint(Integer.parseInt(request.getParameter("orderPoint")));
			prod.setNumber(Integer.parseInt(request.getParameter("number")));
			prod.setCategoryId(Integer.parseInt(request.getParameter("category")));
			
			if(request.getParameter("isactive") == null) {
				prod.setActive(false);
			}
			else {
				prod.setActive(true);
			}
			
			ProductLogic prodLog = new ProductLogic();
			
			String productId = request.getParameter("productId");
			
			if(!productId.equals("0")) {
				prod.setProductId(Integer.parseInt(productId));				
				prodLog.update(prod);
			}
			else {
				prodLog.create(prod);
			}
			
			LinkedList<Product> prods;
			
			prods = prodLog.getAll();
			
			request.setAttribute("prodList", prods);
			
			request.getRequestDispatcher("WEB-INF/ProductList.jsp").forward(request, response);
			
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			request.setAttribute("messageError", "Error actulizando el producto");
			request.getRequestDispatcher("Error.jsp").forward(request, response);
		}
		
	}

}
