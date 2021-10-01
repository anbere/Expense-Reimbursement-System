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
			reimbursementDAO.createReimbursementByUser(username, r_type, amount, description, conn);
			return true;
		}
		
	}
	
	public ArrayList<Reimbursement> getPendingReimbursementsByUser(String username) throws SQLException
	{
		ArrayList<Reimbursement> pendingReimbursements = null;
		
		try(Connection conn = ConnectionUtil.getConnection())
		{
			try {
				pendingReimbursements = reimbursementDAO.getPendingReimbursementsByUser(username, conn);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} catch (NoPendingReimbursementsException e) {
				System.out.println(e.getMessage());
			}			
		}
		
		return pendingReimbursements;
		
	}
	
	public ArrayList<Reimbursement> getCompletedReimbursementsByUser(String username) throws SQLException
	{
		ArrayList<Reimbursement> completedReimbursements = null;
		
		try(Connection conn = ConnectionUtil.getConnection())
		{
			try {
				completedReimbursements = reimbursementDAO.getCompletedReimbursementsByUser(username, conn);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} catch (NoPendingReimbursementsException e) {
				System.out.println(e.getMessage());
			}			
		}
		
		return completedReimbursements;
		
	}
	
	public ArrayList<Reimbursement> getAllPendingReimbursements() throws SQLException
	{
		ArrayList<Reimbursement> pendingReimbursements = null;
		
		try(Connection conn = ConnectionUtil.getConnection())
		{
			try {
				pendingReimbursements = reimbursementDAO.getAllPendingReimbursements(conn);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} catch (NoPendingReimbursementsException e) {
				System.out.println(e.getMessage());
			}			
		}
		
		return pendingReimbursements;
	}
	
	public ArrayList<Reimbursement> getAllCompletedReimbursements() throws SQLException
	{
		ArrayList<Reimbursement> pendingReimbursements = null;
		
		try(Connection conn = ConnectionUtil.getConnection())
		{
			try {
				pendingReimbursements = reimbursementDAO.getAllCompletedReimbursements(conn);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} catch (NoPendingReimbursementsException e) {
				System.out.println(e.getMessage());
			}			
		}
		
		return pendingReimbursements;
	}
}
