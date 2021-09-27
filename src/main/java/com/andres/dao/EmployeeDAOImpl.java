package com.andres.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.andres.models.Employee;

public class EmployeeDAOImpl implements EmployeeDAO {

	public Employee checkCredentials(String username, String password, Connection conn) throws SQLException{

		Employee user = null;

		String sql = "SELECT * FROM ers.users WHERE username = ?";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, username);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {

			if (password == rs.getString("password")) {
				String id = rs.getString("username");
				String pass = rs.getString("password");

				user = new Employee(id, pass);

			} else
				user = new Employee();
		}
		
		return user;
	}

}
