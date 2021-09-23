package com.andres.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserDAO {

	public boolean checkCredentials(String username, String password, Connection conn) throws SQLException;
}
