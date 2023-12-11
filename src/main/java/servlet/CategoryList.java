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
 * Servlet implementation class CategoryList
 */
@WebServlet({ "/CategoryList", "/categoryList", "/categorylist" })
public class CategoryList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryLogic catLog = new CategoryLogic();
		
		LinkedList<Category> cats;
		
		try {
			
			cats = catLog.getAll();
			
			request.setAttribute("categoryList", cats);
			
			request.getRequestDispatcher("WEB-INF/CategoryList.jsp").forward(request, response);
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			request.setAttribute("messageError", "Error recuperando las categorias");
			request.getRequestDispatcher("Error.jsp").forward(request, response);
		}
	}

}
