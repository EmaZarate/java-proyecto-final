package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Product;
import entities.Sale;
import entities.SaleDetails;
import logic.ProductLogic;
import logic.SaleLogic;

/**
 * Servlet implementation class cancelSale
 */
@WebServlet({ "/cancelSale", "/cancelsale", "/CancelSale" })
public class CancelSale extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelSale() {
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
						
			sale.setState("Cancelado");
	
			saleLog.update(sale);
			
			ProductLogic prodLogic = new ProductLogic();
			
			Collections.sort(sale.getSaleDetails(), new Comparator<SaleDetails>() {
			    @Override
			    public int compare(SaleDetails o1, SaleDetails o2) {
			        return o1.getProductId() - o2.getProductId();
			    }
			});
			
			Product[] arrayProd = new Product[sale.getSaleDetails().size()];
			
			for (int i = 0; i < sale.getSaleDetails().size(); i++) {
				arrayProd[i] = sale.getSaleDetails().get(i).getProduct();
			}
			
			LinkedList<Product> prodsToCheck = prodLogic.getProductsToCheck(arrayProd);
			
			for (int i = 0; i < prodsToCheck.size(); i++) {
				Product product = prodsToCheck.get(i);
				int num = product.getNumber() + sale.getSaleDetails().get(i).getNumber();
				product.setNumber(num);
				prodLogic.update(product);
			}
			
			request.setAttribute("sale", sale);
			
			request.getRequestDispatcher("WEB-INF/SaleDetails.jsp").forward(request, response);
			
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			request.setAttribute("messageError", "Error actulizando la venta");
			request.getRequestDispatcher("Error.jsp").forward(request, response);
		}
	}

}
