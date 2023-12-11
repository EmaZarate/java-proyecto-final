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
import logic.CategoryLogic;

/**
 * Servlet implementation class CreateUpdateCategory
 */
@WebServlet({ "/CreateUpdateCategory", "/createUpdateCategory", "/createupdatecategory" })
public class CreateUpdateCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUpdateCategory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String categoryId = request.getParameter("categoryid");
			if(categoryId != null) {
				int catId = Integer.parseInt(categoryId);
				
				CategoryLogic catLog = new CategoryLogic();
				Category cat = new Category();
				
				cat.setCategoryId(catId);
				catLog.getById(cat);
				
				request.setAttribute("cat", cat);
			}
			
			request.getRequestDispatcher("WEB-INF/CreateUpdateCategory.jsp").forward(request, response);
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
			Category cat = new Category();
			
			cat.setName(request.getParameter("name"));
			
			if(request.getParameter("isactive") == null) {
				cat.setActive(false);
			}
			else {
				cat.setActive(true);
			}
			
			CategoryLogic catLog = new CategoryLogic();
			
			String categoryId = request.getParameter("categoryId");
			
			if(!categoryId.equals("0")) {
				cat.setCategoryId(Integer.parseInt(categoryId));				
				catLog.update(cat);
			}
			else {
				catLog.create(cat);
			}
			
			LinkedList<Category> cats;
			
			cats = catLog.getAll();
			
			request.setAttribute("categoryList", cats);
			
			request.getRequestDispatcher("WEB-INF/CategoryList.jsp").forward(request, response);
			
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			request.setAttribute("messageError", "Error actulizando el producto");
			request.getRequestDispatcher("Error.jsp").forward(request, response);
		}
	}

}
