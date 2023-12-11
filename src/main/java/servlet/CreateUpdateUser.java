package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Role;
import entities.User;
import logic.RoleLogic;
import logic.UserLogic;

/**
 * Servlet implementation class createUpdateUser
 */
@WebServlet({ "/createUpdateUser", "/createupdateuser", "/CreateUpdateUser" })
public class CreateUpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUpdateUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String userId = request.getParameter("userid");
			if(userId != null) {
				int id = Integer.parseInt(userId);
				
				UserLogic userLog = new UserLogic();
				
				User user = new User();
				
				user.setId(id);
				userLog.getById(user);
				
				request.setAttribute("user", user);
			}
			
			RoleLogic rolelog = new RoleLogic();
			LinkedList<Role> roles = rolelog.getAll();
			request.setAttribute("roles", roles);
			
			request.getRequestDispatcher("WEB-INF/CreateUpdateUser.jsp").forward(request, response);
		} catch (SQLException e) {
			
			request.setAttribute("messageError", "Error recuperando el usuario");
			request.getRequestDispatcher("Error.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			User user = new User();
			
			user.setName(request.getParameter("name"));
			user.setSurname(request.getParameter("surname"));
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("password"));
			user.setRoleId(Integer.parseInt(request.getParameter("role")));
			
			if(request.getParameter("isactive") == null) {
				user.setActive(false);
			}
			else {
				user.setActive(true);
			}
			
			UserLogic userLog = new UserLogic();
			
			String userId = request.getParameter("userId");
			
			if(!userId.equals("0")) {
				user.setId(Integer.parseInt(userId));				
				userLog.update(user);
			}
			else {
				userLog.create(user);
			}
			
			LinkedList<User> users;
			
			users = userLog.getAll();
			
			request.setAttribute("userList", users);
			
			request.getRequestDispatcher("WEB-INF/UserList.jsp").forward(request, response);
			
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			request.setAttribute("messageError", "Error actulizando el usuario");
			request.getRequestDispatcher("Error.jsp").forward(request, response);
		}
	}

}
