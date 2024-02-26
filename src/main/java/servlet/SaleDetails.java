package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Category;
import entities.Sale;
import logic.CategoryLogic;
import logic.SaleLogic;

/**
 * Servlet implementation class sale
 */
@WebServlet({ "/saleDetails", "/SaleDetails" })
public class SaleDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaleDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			int saleId = Integer.parseInt(request.getParameter("saleid"));
				
			SaleLogic saleLog = new SaleLogic();
			Sale sale = new Sale();
			sale.setSaleId(saleId);
			
			saleLog.getSaleDetails(sale);
				
		    request.setAttribute("sale", sale);
			
			request.getRequestDispatcher("WEB-INF/SaleDetails.jsp").forward(request, response);
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			request.setAttribute("messageError", "Error recuperando el producto");
			request.getRequestDispatcher("Error.jsp").forward(request, response);
		}
	}

}
