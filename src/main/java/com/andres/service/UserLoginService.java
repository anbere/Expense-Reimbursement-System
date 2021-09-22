package com.andres.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.andres.dao.UserDAO;
import com.andres.dao.UserDAOImpl;
import com.andres.utilities.ConnectionUtil;

public class UserLoginService {
	
	UserDAO userDAO;

	public UserLoginService() {
		userDAO = new UserDAOImpl();
	}
	
	public boolean checkLogin(String username, String password) throws SQLException {
		
		try(Connection conn = ConnectionUtil.getConnection())
		{
			return userDAO.checkLogin(username, password);
		}
		
		
	}
	
}
