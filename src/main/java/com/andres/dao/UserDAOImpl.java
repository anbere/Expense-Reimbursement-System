package com.andres.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {

	public boolean checkCredentials(String username, String password, Connection conn) throws SQLException {

			String sql = "SELECT username, password FROM ers.users WHERE username = ? and password = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			}else {
				return false;
			}
			
	}
}
