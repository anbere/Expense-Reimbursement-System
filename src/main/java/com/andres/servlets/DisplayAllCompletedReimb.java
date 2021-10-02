package com.andres.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.andres.models.Reimbursement;
import com.andres.service.ReimbursementService;
import com.google.gson.Gson;

/**
 * Servlet implementation class DisplayAllCompletedReimb
 */
public class DisplayAllCompletedReimb extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReimbursementService reimbursementService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayAllCompletedReimb() {
        super();
        reimbursementService = new ReimbursementService();
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
//		HttpSession session = request.getSession();
//		Employee currentEmp = (Employee)session.getAttribute("currentUser");
		
		Integer offset = Integer.parseInt(request.getReader().readLine().replaceAll("[^\\d.]", ""));
		System.out.println("completed offset: " + offset);
		try {
			ArrayList<Reimbursement> reimbursements = reimbursementService.getAllCompletedReimbursements(offset);
			System.out.println(reimbursements);
			Gson gson = new Gson();
			String jsonArray = gson.toJson(reimbursements);
			out.append(jsonArray);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
