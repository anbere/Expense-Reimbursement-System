package com.andres.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.andres.models.Employee;

public interface EmployeeDAO {

	public Employee checkCredentials(String username, String password, Connection conn) throws SQLException;
}
