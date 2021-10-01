package com.andres.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.andres.exceptions.NoPendingReimbursementsException;
import com.andres.models.Reimbursement;
import com.andres.utilities.GenerateIDUtil;

public class ReimbursementDAOImpl implements ReimbursementDAO {

	@Override
	public Reimbursement createReimbursementByUser(String username, int r_type, int amount, String description, Connection conn) throws SQLException {

		int id = Integer.parseInt(GenerateIDUtil.generateNewEmployeeID());
		
		Reimbursement reimb = new Reimbursement(id, 0, username, r_type, amount, description);
		
		String sql = "INSERT INTO ers.reimbursements (reimb_id, reimb_author, reimb_type_id, reimb_amount, reimb_description) values(?,?,?,?,?);";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setInt(1, id);
		ps.setString(2, username);
		ps.setInt(3, r_type);
		ps.setInt(4, amount);
		ps.setString(5,  description);

		int i = ps.executeUpdate();
		
		if(i != 1)
		{
			throw new SQLException("Failed to insert reimbursement");
		}
		else
		{
			sql = "SELECT * FROM ers.reimbursements WHERE reimb_id = ?";
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				reimb = new Reimbursement(rs.getInt("reimb_id"), rs.getInt("reimb_status_id"),
						rs.getString("reimb_author"), rs.getInt("reimb_type_id"), rs.getInt("reimb_amount"), rs.getString("reimb_description") );
				return reimb;
			}
			return null;
		}
	}

	@Override
	public ArrayList<Reimbursement> getPendingReimbursementsByUser(String username, Connection conn) throws SQLException, NoPendingReimbursementsException 
	{

		ArrayList<Reimbursement> reimbs = new ArrayList<>();
		
		String sql = "SELECT * FROM ers.reimbursements WHERE reimb_author = ? AND reimb_status_id = 0";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		
		ResultSet rs = ps.executeQuery();
		
		
		while(rs.next())
		{
			reimbs.add(new Reimbursement(rs.getInt("reimb_id"), rs.getInt("reimb_status_id"),
					rs.getString("reimb_author"), rs.getInt("reimb_type_id"), rs.getInt("reimb_amount"), rs.getString("reimb_description")));
		}
		
		return reimbs;
	}
	
	@Override
	public ArrayList<Reimbursement> getCompletedReimbursementsByUser(String username, Connection conn) throws SQLException, NoPendingReimbursementsException 
	{

		ArrayList<Reimbursement> reimbs = new ArrayList<>();
		
		String sql = "SELECT * FROM ers.reimbursements WHERE reimb_author = ? AND reimb_status_id = 1";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		
		ResultSet rs = ps.executeQuery();
		
		
		while(rs.next())
		{
			reimbs.add(new Reimbursement(rs.getInt("reimb_id"), rs.getInt("reimb_status_id"),
					rs.getString("reimb_author"), rs.getInt("reimb_type_id"), rs.getInt("reimb_amount"), rs.getString("reimb_description")));
		}

		return reimbs;
	}
	
	@Override
	public ArrayList<Reimbursement> getAllPendingReimbursements(Connection conn) throws SQLException, NoPendingReimbursementsException 
	{

		ArrayList<Reimbursement> reimbs = new ArrayList<>();
		
		String sql = "SELECT * FROM ers.reimbursements WHERE reimb_status_id = 0";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		
		while(rs.next())
		{
			reimbs.add(new Reimbursement(rs.getInt("reimb_id"), rs.getInt("reimb_status_id"),
					rs.getString("reimb_author"), rs.getInt("reimb_type_id"), rs.getInt("reimb_amount"), rs.getString("reimb_description")));
		}

		return reimbs;
	}
	
	@Override
	public ArrayList<Reimbursement> getAllCompletedReimbursements(Connection conn) throws SQLException, NoPendingReimbursementsException 
	{

		ArrayList<Reimbursement> reimbs = new ArrayList<>();
		
		String sql = "SELECT * FROM ers.reimbursements WHERE reimb_status_id = 1";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		
		while(rs.next())
		{
			reimbs.add(new Reimbursement(rs.getInt("reimb_id"), rs.getInt("reimb_status_id"),
					rs.getString("reimb_author"), rs.getInt("reimb_type_id"), rs.getInt("reimb_amount"), rs.getString("reimb_description")));
		}

		return reimbs;
	}

}
