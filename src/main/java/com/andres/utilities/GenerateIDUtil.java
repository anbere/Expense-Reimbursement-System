package com.andres.utilities;

import java.util.UUID;

public class GenerateIDUtil {
	public static Long generateNewEmployeeID() {

		Long id = 0L;
		UUID temp = UUID.randomUUID();

		id = Math.abs(temp.getMostSignificantBits());

		id = Long.parseLong(id.toString().substring(0, 6));

		return id;
	}
	
}