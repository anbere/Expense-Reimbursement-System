package com.andres.app;

import java.sql.SQLException;
import java.util.ArrayList;

import com.andres.models.Employee;
import com.andres.service.UserServices;

public class Application {

	public static void main(String[] args) {

		ArrayList<Employee> emps = new ArrayList<>();

		UserServices us = new UserServices();
		
		try {
			emps = us.getAllEmployees();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		System.out.println(emps.toString());
		
		for(int x = 0; x < emps.size(); x++)
		{
			System.out.println(emps.get(x).toString());
		}

	}

}
