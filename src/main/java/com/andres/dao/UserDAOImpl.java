package com.andres.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.andres.utilities.ConnectionUtil;

public class UserDAOImpl implements UserDAO {

	public boolean checkLogin(String username, String password) {

		try (Connection con = ConnectionUtil.getConnection()) {

			String sql = "SELECT username FROM ers.users WHERE username = ?";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();
			String returnedUser = null;

			if (rs.next()) {
				returnedUser = rs.getString("username");
			}
			if (returnedUser != null) {
				return true;
			}

			return false;

		} catch (SQLException e) {
			return false;
		}
	}
}
