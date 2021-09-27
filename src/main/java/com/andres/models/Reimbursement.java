package com.andres.models;

import java.util.Arrays;

public class Reimbursement {

	private int r_id;
	private int r_status;
	private String r_author;
	private String r_type;
	private String r_amount;
	private byte[] receipt;

	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reimbursement(int r_id, int r_status, String r_author, String r_type, String r_amount) {
		super();
		this.r_id = r_id;
		this.r_status = r_status;
		this.r_author = r_author;
		this.r_type = r_type;
		this.r_amount = r_amount;
	}

	public Reimbursement(int r_id, int r_status, String r_author, String r_type, String r_amount, byte[] receipt) {
		super();
		this.r_id = r_id;
		this.r_status = r_status;
		this.r_author = r_author;
		this.r_type = r_type;
		this.r_amount = r_amount;
		this.receipt = receipt;
	}

	public int getR_id() {
		return r_id;
	}

	public void setR_id(int r_id) {
		this.r_id = r_id;
	}

	public int getR_status() {
		return r_status;
	}

	public void setR_status(int r_status) {
		this.r_status = r_status;
	}

	public String getR_author() {
		return r_author;
	}

	public void setR_author(String r_author) {
		this.r_author = r_author;
	}

	public String getR_type() {
		return r_type;
	}

	public void setR_type(String r_type) {
		this.r_type = r_type;
	}

	public String getR_amount() {
		return r_amount;
	}

	public void setR_amount(String r_amount) {
		this.r_amount = r_amount;
	}

	public byte[] getReceipt() {
		return receipt;
	}

	public void setReceipt(byte[] receipt) {
		this.receipt = receipt;
	}

	@Override
	public String toString() {
		return "Reimbursement [r_id=" + r_id + ", r_status=" + r_status + ", r_author=" + r_author + ", r_type="
				+ r_type + ", r_amount=" + r_amount + ", receipt=" + Arrays.toString(receipt) + "]";
	}

}
