package com.andres.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.andres.models.Employee;
import com.andres.models.Reimbursement;
import com.andres.service.ReimbursementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

/**
 * Servlet implementation class DisplayReimbServlet
 */
public class DisplayPendingReimb extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReimbursementService reimbursementService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayPendingReimb() {
        super();
        reimbursementService = new ReimbursementService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Employee currentEmp = (Employee)session.getAttribute("currentUser");
		
		Integer offset = Integer.parseInt(request.getReader().readLine().replaceAll("[^\\d.]", ""));
		
		try {
			ArrayList<Reimbursement> reimbursements = reimbursementService.getPendingReimbursementsByUser(currentEmp.getUsername(), offset);
			System.out.println(reimbursements);
			Gson gson = new Gson();
			String jsonArray = gson.toJson(reimbursements);
			out.append(jsonArray);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}



}
