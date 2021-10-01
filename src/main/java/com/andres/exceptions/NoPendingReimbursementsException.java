package com.andres.exceptions;

@SuppressWarnings("serial")
public class NoPendingReimbursementsException extends Exception {

	public NoPendingReimbursementsException() {
		super();

	}

	public NoPendingReimbursementsException(String message) {
		super(message);

	}

}
