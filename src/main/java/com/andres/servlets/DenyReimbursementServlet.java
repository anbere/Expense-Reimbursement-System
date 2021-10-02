package com.andres.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.andres.service.ReimbursementService;

/**
 * Servlet implementation class DenyReimbursementServlet
 */
public class DenyReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReimbursementService reimbursementService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DenyReimbursementServlet() {
        super();
        reimbursementService = new ReimbursementService();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getReader().readLine().replaceAll("[^\\d.]", ""));

//		System.out.println("changereimbstatus request param values: " + request.getReader().readLine().toString());
		
		try {
			reimbursementService.updateReimbursementStatus(id, 2);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
