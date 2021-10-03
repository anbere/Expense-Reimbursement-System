package com.andres.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.andres.models.Employee;
import com.andres.models.Reimbursement;
import com.andres.service.ReimbursementService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class ReimbursementServlet
 */
public class ReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ReimbursementService reimbursementService;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReimbursementServlet() {
        super();
        reimbursementService = new ReimbursementService();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ObjectMapper om = new ObjectMapper();
		
		Reimbursement reimbursement = om.readValue(request.getReader(), Reimbursement.class);
		
		HttpSession session = request.getSession();
		Employee loggedInEmp = (Employee)session.getAttribute("currentUser");
		
		try {
	
			if(reimbursementService.createReimbursementByUser(loggedInEmp.getUsername(), reimbursement.getR_type_id(), reimbursement.getR_amount(), reimbursement.getR_description()))
			{
				PrintWriter out = response.getWriter();
				out.append("{\"success\":\"true\"}");
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
