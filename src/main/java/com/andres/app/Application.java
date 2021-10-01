package com.andres.app;

import java.sql.Connection;
import java.sql.SQLException;

import com.andres.dao.ReimbursementDAOImpl;
import com.andres.exceptions.NoPendingReimbursementsException;
import com.andres.utilities.ConnectionUtil;

public class Application {

	public static void main(String[] args) {



		ReimbursementDAOImpl rdi = new ReimbursementDAOImpl();
		
		try(Connection conn = ConnectionUtil.getConnection())
		{
			System.out.println(rdi.getPendingReimbursementsByUser("sdf", conn).toString());
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoPendingReimbursementsException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		

	}

}
