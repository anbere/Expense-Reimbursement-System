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
import com.google.gson.Gson;

/**
 * Servlet implementation class DisplayAllPendingReimb
 */
public class DisplayAllPendingReimb extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ReimbursementService reimbursementService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayAllPendingReimb() {
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
		
		try {
			ArrayList<Reimbursement> reimbursements = reimbursementService.getAllPendingReimbursements();
			System.out.println(reimbursements);
			Gson gson = new Gson();
			String jsonArray = gson.toJson(reimbursements);
			out.append(jsonArray);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
