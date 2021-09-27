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
import com.andres.service.UserLoginService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserLoginService userLoginService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		userLoginService = new UserLoginService();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Do I need this?
		//response.setContentType("text/html");
		
		
		String jsonString = request.getReader().readLine();

		ObjectMapper om = new ObjectMapper();
		
		Employee user = om.readValue(jsonString, Employee.class);

		try {

			HttpSession session = request.getSession();
			
			Employee activeUser = userLoginService.checkCredentials(user.getUsername(), user.getPassword());
			
			session.setAttribute("username", activeUser.getUsername());
//			session.setAttribute("password", activeUser.getPassword());
			
			response.sendRedirect("DashboardServlet");

		} catch (SQLException e) {
			System.out.println("Bad bad boi");
			
		} catch (InvalidPasswordException e) {
			

		} catch (UserNotFoundException e) {
			

		}

		//pw.close();
	}

}
