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
 * Servlet implementation class CreateUpdateSupplier
 */
@WebServlet({ "/CreateUpdateSupplier", "/createUpdateSupplier", "/createupdatesupplier" })
public class CreateUpdateSupplier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUpdateSupplier() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String supplierid = request.getParameter("supplierid");
			if(supplierid != null) {
				int id = Integer.parseInt(supplierid);
				
				SupplierLogic supplierLog = new SupplierLogic();
				
				Supplier supplier = new Supplier();
				
				supplier.setSupplierId(id);
				supplierLog.getById(supplier);
				
				request.setAttribute("supplier", supplier);
			}
			
			request.getRequestDispatcher("WEB-INF/CreateUpdateSupplier.jsp").forward(request, response);
		} catch (SQLException e) {
			
			request.setAttribute("messageError", "Error recuperando el proveedor");
			request.getRequestDispatcher("Error.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Supplier supplier = new Supplier();
			
			supplier.setName(request.getParameter("name"));
			supplier.setSurname(request.getParameter("surname"));
			supplier.setEmail(request.getParameter("email"));
			supplier.setPhone(request.getParameter("phone"));
			
			if(request.getParameter("isactive") == null) {
				supplier.setActive(false);
			}
			else {
				supplier.setActive(true);
			}
			
			SupplierLogic supplierLog = new SupplierLogic();
			
			String supplierId = request.getParameter("supplierId");
			
			if(!supplierId.equals("0")) {
				supplier.setSupplierId(Integer.parseInt(supplierId));				
				supplierLog.update(supplier);
			}
			else {
				supplierLog.create(supplier);
			}
			
			LinkedList<Supplier> suppliers;
			
			suppliers = supplierLog.getAll();
			
			request.setAttribute("supplierList", suppliers);
			
			request.getRequestDispatcher("WEB-INF/SupplierList.jsp").forward(request, response);
			
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			request.setAttribute("messageError", "Error actulizando el proveedor");
			request.getRequestDispatcher("Error.jsp").forward(request, response);
		}
	}

}
