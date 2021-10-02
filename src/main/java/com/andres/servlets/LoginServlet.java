package com.andres.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.andres.exceptions.InvalidPasswordException;
import com.andres.exceptions.UserNotFoundException;
import com.andres.models.Employee;
import com.andres.service.UserServices;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserServices userLoginService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		userLoginService = new UserServices();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Do I need this?
		//response.setContentType("text/html");

		ObjectMapper om = new ObjectMapper();
		
		Employee user = om.readValue(request.getReader(), Employee.class);

		try {
			
			Employee activeUser = userLoginService.checkCredentials(user.getUsername(), user.getPassword());
			System.out.println(activeUser.toString());
			
			HttpSession session = request.getSession();
			session.setAttribute("currentUser", activeUser);
			
			if(activeUser.getRole_id() == 0)
			{
				response.sendRedirect("Dashboard.html");
			}
			else
			{
				response.sendRedirect("ManagerDashboard.html");
			}
			

		} catch (SQLException e) {
			System.out.println("Bad bad boi.");
			
		} catch (InvalidPasswordException e) {
			System.out.println("Invalid password.");

		} catch (UserNotFoundException e) {
			System.out.println("User not found.");

		}

	}

}
