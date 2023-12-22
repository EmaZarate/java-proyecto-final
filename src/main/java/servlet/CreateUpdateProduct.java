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
import logic.ProductLogic;
import logic.SupplierLogic;
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
			SupplierLogic supLog = new SupplierLogic();
			
			LinkedList<Supplier> sups= supLog.getAll();
			
			if(productId != null) {
				int prodId = Integer.parseInt(productId);
				
				ProductLogic prodLog = new ProductLogic();
				Product prod = new Product();
				
				prod.setProductId(prodId);
				prodLog.getProductById(prod);
				LinkedList<Integer> supplierIdsByProd = supLog.getAllSuppierByProduct(prod);	
				
				for(Supplier sup: sups) {
					if(supplierIdsByProd.contains(sup.getSupplierId())) {
						sup.setSelected(true);
					}
				}	
				request.setAttribute("prod", prod);
			}
			
			
			CategoryLogic catLog = new CategoryLogic();
			
				
			LinkedList<Category> cats = catLog.getAll();
				
				request.setAttribute("cats", cats);
				request.setAttribute("sups", sups);
				
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
			
			String[] stringSuppliersIds =  request.getParameterValues("suppliersIds");
			
			int[] suppliersIds = new int[stringSuppliersIds.length];
			
			for (int i = 0; i < stringSuppliersIds.length; i++) {
				suppliersIds[i] = Integer.parseInt(stringSuppliersIds[i]);
			}
			
			
			
			if(request.getParameter("isactive") == null) {
				prod.setActive(false);
			}
			else {
				prod.setActive(true);
			}
			
			ProductLogic prodLog = new ProductLogic();
			SupplierLogic supLog = new SupplierLogic();
			
			String productId = request.getParameter("productId");
			
			if(!productId.equals("0")) {
				prod.setProductId(Integer.parseInt(productId));				
				prodLog.update(prod);
				supLog.deleteSupplierProducts(prod);
			}
			else {
				prodLog.create(prod);
			}
			
			supLog.addSupplierToProduct(prod, suppliersIds);
			
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
