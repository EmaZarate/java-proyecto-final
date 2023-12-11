package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Supplier;
import logic.SupplierLogic;

/**
 * Servlet implementation class SupplierList
 */
@WebServlet({ "/SupplierList", "/supplierList", "/supplierlist" })
public class SupplierList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupplierList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SupplierLogic supplierLog = new SupplierLogic();
		
		LinkedList<Supplier> suppliers;
		
		try {
			
			suppliers = supplierLog.getAll();
			
			request.setAttribute("supplierList", suppliers);
			
			request.getRequestDispatcher("WEB-INF/SupplierList.jsp").forward(request, response);
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			request.setAttribute("messageError", "Error recuperando los proveedores");
			request.getRequestDispatcher("Error.jsp").forward(request, response);
		}
	}

}
