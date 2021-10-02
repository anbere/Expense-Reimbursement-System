package com.andres.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.andres.models.Employee;

public class EmployeeDAOImpl implements EmployeeDAO {

	public Employee checkCredentials(String username, String password, Connection conn) throws SQLException{

		Employee user = null;

		String sql = "SELECT * FROM ers.employees WHERE username = ?";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, username);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {

			if (password.equals(rs.getString("password"))) {
				String usern = rs.getString("username");
				String pass = rs.getString("password");
				String first = rs.getString("first_name");
				String last = rs.getString("last_name");
				String email = rs.getString("email");
				int id = rs.getInt("role_id");

				user = new Employee(usern, pass, first, last, email, id);

			} else
				user = new Employee();
		}
		
		return user;
	}
	
	@Override
	public ArrayList<Employee> getAllEmployees(Connection conn) throws SQLException
	{
		ArrayList<Employee> allEmps = new ArrayList<>();
		
		String sql = "SELECT * FROM ers.employees WHERE role_id = 0";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next())
		{
			String username = rs.getString("username");
			String password = rs.getString("password");
			String first_name = rs.getString("first_name");
			String last_name = rs.getString("last_name");
			String email = rs.getString("email");

			Employee emp = new Employee(username, password, first_name, last_name, email, 0);
			allEmps.add(emp);
		}
		
		return allEmps;
		
	}
	
}
