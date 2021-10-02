package com.andres.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.andres.dao.EmployeeDAO;
import com.andres.dao.EmployeeDAOImpl;
import com.andres.exceptions.InvalidPasswordException;
import com.andres.exceptions.UserNotFoundException;
import com.andres.models.Employee;
import com.andres.utilities.ConnectionUtil;

public class UserServices {

	EmployeeDAO employeeDAO;

	public UserServices() {
		employeeDAO = new EmployeeDAOImpl();
	}

	public Employee checkCredentials(String username, String password) throws SQLException, InvalidPasswordException, UserNotFoundException {

		Employee user = null;

		try (Connection conn = ConnectionUtil.getConnection()) {
			user = employeeDAO.checkCredentials(username, password, conn);
		}

		if (user == null) {
//			System.out.println("No user found");
			throw new UserNotFoundException("User not found.");
		}else if (user.getUsername().equals(""))
		{
//			System.out.println("Wrong password");
			throw new InvalidPasswordException("Wrong password.");
		}
		
		return user;
	}
	
	public ArrayList<Employee> getAllEmployees() throws SQLException
	{
		ArrayList<Employee> allEmps = new ArrayList<>();
		try(Connection conn = ConnectionUtil.getConnection())
		{
			allEmps = employeeDAO.getAllEmployees(conn);
		}catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return allEmps;
	}

}
