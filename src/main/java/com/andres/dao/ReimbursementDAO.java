package com.andres.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.andres.exceptions.NoPendingReimbursementsException;
import com.andres.models.Reimbursement;

public interface ReimbursementDAO {

	public Reimbursement createReimbursementByUser(String username, int r_type, int amount, String description, Connection conn) throws SQLException;
	
	public ArrayList<Reimbursement> getPendingReimbursementsByUser(String username, int offset, Connection conn) throws SQLException, NoPendingReimbursementsException;

	public ArrayList<Reimbursement> getCompletedReimbursementsByUser(String username, int offset, Connection conn) throws SQLException, NoPendingReimbursementsException;

	public ArrayList<Reimbursement> getAllPendingReimbursements(int offset, Connection conn) throws SQLException, NoPendingReimbursementsException;

	public ArrayList<Reimbursement> getAllCompletedReimbursements(int offset, Connection conn) throws SQLException, NoPendingReimbursementsException;

	public Reimbursement updateReimbursementStatus(int id, int status, Connection conn) throws SQLException;
	
}
