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
	public Reimbursement createReimbursementByUser(String username, int r_type, int amount, String description,
			Connection conn) throws SQLException {

		int r_id = Integer.parseInt(GenerateIDUtil.generateNewEmployeeID());

		Reimbursement reimb = new Reimbursement(r_id, username, r_type, amount, description);

		String sql = "INSERT INTO ers.reimbursements (reimb_id, reimb_author, reimb_type_id, reimb_amount, reimb_description) values(?,?,?,?,?);";
		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setInt(1, r_id);
		ps.setString(2, username);
		ps.setInt(3, r_type);
		ps.setInt(4, amount);
		ps.setString(5, description);

		int i = ps.executeUpdate();

		if (i != 1) {
			throw new SQLException("Failed to insert reimbursement");
		}

		sql = "SELECT r.reimb_id, rs.reimb_status, r.reimb_author, rt.reimb_type, r.reimb_amount, r.reimb_description \r\n"
				+ "FROM ers.reimbursements r \r\n" + "join ers.reimbursement_type rt \r\n"
				+ "on r.reimb_type_id  = rt.reimb_type_id \r\n" + "join ers.reimbursement_status rs \r\n"
				+ "on r.reimb_status_id = rs.reimb_status_id \r\n" + "where reimb_id = ?\r\n";
		ps = conn.prepareStatement(sql);

		ps.setInt(1, r_id);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			reimb = new Reimbursement(rs.getInt("reimb_id"), rs.getString("reimb_status"), rs.getString("reimb_author"),
					rs.getString("reimb_type"), rs.getInt("reimb_amount"), rs.getString("reimb_description"));
			return reimb;
		}
		return null;

	}

	@Override
	public ArrayList<Reimbursement> getPendingReimbursementsByUser(String username, int offset, Connection conn)
			throws SQLException, NoPendingReimbursementsException {

		ArrayList<Reimbursement> reimbs = new ArrayList<>();

		String sql = "SELECT r.reimb_id, rs.reimb_status, r.reimb_author, rt.reimb_type, r.reimb_amount, r.reimb_description \r\n"
				+ "FROM ers.reimbursements r \r\n" + "join ers.reimbursement_type rt \r\n"
				+ "on r.reimb_type_id  = rt.reimb_type_id \r\n" + "join ers.reimbursement_status rs \r\n"
				+ "on r.reimb_status_id = rs.reimb_status_id \r\n" + "where r.reimb_author = ? and rs.reimb_status = 'PENDING'\r\n"
				+ "order by r.reimb_author, r.reimb_id\r\n"
				+ "LIMIT 13 OFFSET ?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ps.setInt(2, offset);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			reimbs.add(
					new Reimbursement(rs.getInt("reimb_id"), rs.getString("reimb_status"), rs.getString("reimb_author"),
							rs.getString("reimb_type"), rs.getInt("reimb_amount"), rs.getString("reimb_description")));
		}

		return reimbs;
	}

	@Override
	public ArrayList<Reimbursement> getCompletedReimbursementsByUser(String username, int offset, Connection conn)
			throws SQLException, NoPendingReimbursementsException {

		ArrayList<Reimbursement> reimbs = new ArrayList<>();

		String sql = "SELECT r.reimb_id, rs.reimb_status, r.reimb_author, rt.reimb_type, r.reimb_amount, r.reimb_description \r\n"
				+ "FROM ers.reimbursements r \r\n" + "join ers.reimbursement_type rt \r\n"
				+ "on r.reimb_type_id  = rt.reimb_type_id \r\n" + "join ers.reimbursement_status rs \r\n"
				+ "on r.reimb_status_id = rs.reimb_status_id \r\n" + "where r.reimb_author = ? and rs.reimb_status != 'PENDING'\r\n"
				+ "order by r.reimb_author, r.reimb_id\r\n"
				+ "LIMIT 13 OFFSET ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ps.setInt(2, offset);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			reimbs.add(
					new Reimbursement(rs.getInt("reimb_id"), rs.getString("reimb_status"), rs.getString("reimb_author"),
							rs.getString("reimb_type"), rs.getInt("reimb_amount"), rs.getString("reimb_description")));
		}

		return reimbs;
	}

	@Override
	public ArrayList<Reimbursement> getAllPendingReimbursements(int offset,Connection conn)
			throws SQLException, NoPendingReimbursementsException {

		ArrayList<Reimbursement> reimbs = new ArrayList<>();

		String sql = "SELECT r.reimb_id, rs.reimb_status, r.reimb_author, rt.reimb_type, r.reimb_amount, r.reimb_description \r\n"
				+ "FROM ers.reimbursements r \r\n" + "join ers.reimbursement_type rt \r\n"
				+ "on r.reimb_type_id  = rt.reimb_type_id \r\n" + "join ers.reimbursement_status rs \r\n"
				+ "on r.reimb_status_id = rs.reimb_status_id \r\n" + "where rs.reimb_status = 'PENDING'\r\n"
				+ "order by r.reimb_author, r.reimb_id\r\n"
				+ "LIMIT 13 OFFSET ?";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, offset);
		System.out.println("ps before: " + ps.toString());
		ResultSet rs = ps.executeQuery();
		System.out.println("ps after: " + ps.toString());
		while (rs.next()) {
			reimbs.add(
					new Reimbursement(rs.getInt("reimb_id"), rs.getString("reimb_status"), rs.getString("reimb_author"),
							rs.getString("reimb_type"), rs.getInt("reimb_amount"), rs.getString("reimb_description")));
		}

		return reimbs;
	}

	@Override
	public ArrayList<Reimbursement> getAllCompletedReimbursements(int offset, Connection conn)
			throws SQLException, NoPendingReimbursementsException {

		ArrayList<Reimbursement> reimbs = new ArrayList<>();

		String sql = "SELECT r.reimb_id, rs.reimb_status, r.reimb_author, rt.reimb_type, r.reimb_amount, r.reimb_description \r\n"
				+ "FROM ers.reimbursements r \r\n" + "join ers.reimbursement_type rt \r\n"
				+ "on r.reimb_type_id  = rt.reimb_type_id \r\n" + "join ers.reimbursement_status rs \r\n"
				+ "on r.reimb_status_id = rs.reimb_status_id \r\n" + "where rs.reimb_status != 'PENDING'\r\n"
				+ "order by r.reimb_author, r.reimb_id\r\n"
				+ "LIMIT 13 OFFSET ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, offset);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			reimbs.add(
					new Reimbursement(rs.getInt("reimb_id"), rs.getString("reimb_status"), rs.getString("reimb_author"),
							rs.getString("reimb_type"), rs.getInt("reimb_amount"), rs.getString("reimb_description")));
		}

		return reimbs;
	}

	@Override
	public Reimbursement updateReimbursementStatus(int id, int status, Connection conn) throws SQLException {
		Reimbursement reimb = null;
		String sql = "update ers.reimbursements set reimb_status_id = ? where reimb_id = ?";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setInt(1, status);
		ps.setInt(2, id);

		int i = ps.executeUpdate();

		if (i != 1) {
			throw new SQLException("Failed to update reimb status, with reimb id: " + id);
		}
		
		sql = "SELECT r.reimb_id, rs.reimb_status, r.reimb_author, rt.reimb_type, r.reimb_amount, r.reimb_description \r\n"
				+ "FROM ers.reimbursements r \r\n"
				+ "join ers.reimbursement_type rt \r\n"
				+ "on r.reimb_type_id  = rt.reimb_type_id \r\n"
				+ "join ers.reimbursement_status rs \r\n"
				+ "on r.reimb_status_id = rs.reimb_status_id\r\n"
				+ "where r.reimb_id = ?";
		
		ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next())
		{
			reimb = new Reimbursement(rs.getInt("reimb_id"), rs.getString("reimb_status"), rs.getString("reimb_author"), rs.getString("reimb_type"), rs.getInt("reimb_amount"), rs.getString("reimb_description"));
			return reimb;
		}
		else
		{
			throw new SQLException("Updated Reimbursement not found");
		}

	}

}
