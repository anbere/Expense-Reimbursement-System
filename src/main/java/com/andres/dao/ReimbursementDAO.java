package com.andres.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.andres.exceptions.NoPendingReimbursementsException;
import com.andres.models.Reimbursement;

public interface ReimbursementDAO {

	public Reimbursement createReimbursementByUser(String username, int r_type, int amount, String description, Connection conn) throws SQLException;
	
	public ArrayList<Reimbursement> getPendingReimbursementsByUser(String username, Connection conn) throws SQLException, NoPendingReimbursementsException;

	public ArrayList<Reimbursement> getCompletedReimbursementsByUser(String username, Connection conn) throws SQLException, NoPendingReimbursementsException;

	public ArrayList<Reimbursement> getAllPendingReimbursements(Connection conn) throws SQLException, NoPendingReimbursementsException;

	public ArrayList<Reimbursement> getAllCompletedReimbursements(Connection conn) throws SQLException, NoPendingReimbursementsException;
	
}
