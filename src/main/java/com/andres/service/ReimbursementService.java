package com.andres.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.andres.dao.ReimbursementDAO;
import com.andres.dao.ReimbursementDAOImpl;
import com.andres.exceptions.NoPendingReimbursementsException;
import com.andres.models.Reimbursement;
import com.andres.utilities.ConnectionUtil;

public class ReimbursementService {

	ReimbursementDAO reimbursementDAO;

	public ReimbursementService() {
		reimbursementDAO = new ReimbursementDAOImpl();
	}
	
	public boolean createReimbursementByUser(String username, int r_type, int amount, String description) throws SQLException
	{
		
		try(Connection conn = ConnectionUtil.getConnection())
		{
			Reimbursement rm = reimbursementDAO.createReimbursementByUser(username, r_type, amount, description, conn);
			System.out.println("From RMB Service, after pulling from db: " + rm);
			return true;
		}
		
	}
	
	public ArrayList<Reimbursement> getPendingReimbursementsByUser(String username, int offset) throws SQLException
	{
		ArrayList<Reimbursement> pendingReimbursements = null;
		
		try(Connection conn = ConnectionUtil.getConnection())
		{
			try {
				pendingReimbursements = reimbursementDAO.getPendingReimbursementsByUser(username, 12 * offset, conn);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} catch (NoPendingReimbursementsException e) {
				System.out.println(e.getMessage());
			}			
		}
		
		return pendingReimbursements;
		
	}
	
	public ArrayList<Reimbursement> getCompletedReimbursementsByUser(String username, int offset) throws SQLException
	{
		ArrayList<Reimbursement> completedReimbursements = null;
		
		try(Connection conn = ConnectionUtil.getConnection())
		{
			try {
				completedReimbursements = reimbursementDAO.getCompletedReimbursementsByUser(username, 12 * offset, conn);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} catch (NoPendingReimbursementsException e) {
				System.out.println(e.getMessage());
			}			
		}
		
		return completedReimbursements;
		
	}
	
	public ArrayList<Reimbursement> getAllPendingReimbursements(int offset) throws SQLException
	{
		ArrayList<Reimbursement> pendingReimbursements = null;
		
		try(Connection conn = ConnectionUtil.getConnection())
		{
			try {
				pendingReimbursements = reimbursementDAO.getAllPendingReimbursements(12 * offset, conn);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} catch (NoPendingReimbursementsException e) {
				System.out.println(e.getMessage());
			}			
		}
		
		return pendingReimbursements;
	}
	
	public ArrayList<Reimbursement> getAllCompletedReimbursements(int offset) throws SQLException
	{
		ArrayList<Reimbursement> pendingReimbursements = null;
		
		try(Connection conn = ConnectionUtil.getConnection())
		{
			try {
				pendingReimbursements = reimbursementDAO.getAllCompletedReimbursements(12 * offset, conn);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} catch (NoPendingReimbursementsException e) {
				System.out.println(e.getMessage());
			}			
		}
		
		return pendingReimbursements;
	}
	
	public void updateReimbursementStatus(int id, int status) throws SQLException
	{
		Reimbursement reimbursement = null;
		try(Connection conn = ConnectionUtil.getConnection())
		{
			reimbursement = reimbursementDAO.updateReimbursementStatus(id, status, conn);
			System.out.println("Updated Reimbursement from RBService: " + reimbursement.toString());
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
}
