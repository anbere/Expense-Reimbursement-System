package com.andres.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.andres.models.User;
import com.andres.service.UserLoginService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		String jsonString = request.getReader().readLine();
		
		ObjectMapper om = new ObjectMapper();
		
		User u = om.readValue(jsonString, User.class);
		
		try {

			UserLoginService uls = new UserLoginService();

			if (uls.checkCredentials(u.getUsername(), u.getPassword())) {
				System.out.println("Good boi");
				response.sendRedirect(request.getContextPath() + "/EmployeeLanding.html");
			}
			else {
				System.out.println("Bad boi");
				pw.println("Bad boi");
			}
				
		} catch (SQLException e) {
			System.out.println("Bad bad boi");
			pw.println("Bad bad boi");
		}
		
		pw.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
