package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Sale;
import logic.SaleLogic;

/**
 * Servlet implementation class UpdateSale
 */
@WebServlet({ "/UpdateSale", "/Updatesale", "/updatesale", "/updateSale" })
public class UpdateSale extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateSale() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			
			int saleId = Integer.parseInt(request.getParameter("saleid"));
			
			SaleLogic saleLog = new SaleLogic();
			Sale sale = new Sale();
			sale.setSaleId(saleId);
			
			saleLog.getSaleDetails(sale);
						
			sale.setState("Completado");
	
			saleLog.update(sale);
			
			request.setAttribute("sale", sale);
			
			request.getRequestDispatcher("WEB-INF/SaleDetails.jsp").forward(request, response);
			
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			request.setAttribute("messageError", "Error actulizando la venta");
			request.getRequestDispatcher("Error.jsp").forward(request, response);
		}
	}

}
