package com.andres.models;

public class Reimbursement {

	private int r_id;
	private String r_status; // 0: Pending, 1: Approved, 2: Denied
	private String r_author;
	private String r_type; // 0: Lodging, 2: Travel, 3: Food, 4: Other
	private int r_type_id;
	private int r_amount;
	private String r_description;

	public Reimbursement() {
		super();
	}

	public Reimbursement(String r_type, int r_amount, String r_description) {
		this.r_type = r_type;
		this.r_amount = r_amount;
		this.r_description = r_description;
	}

	public Reimbursement(int r_id, String r_status, String r_author, String r_type, int r_amount, String description) {
		this.r_id = r_id;
		this.r_status = r_status;
		this.r_author = r_author;
		this.r_type = r_type;
		this.r_amount = r_amount;
		this.r_description = description;
	}
	
	public Reimbursement(int r_id, String r_author, int r_type_id, int r_amount, String description) {
		this.r_id = r_id;
		this.r_author = r_author;
		this.r_type_id = r_type_id;
		this.r_amount = r_amount;
		this.r_description = description;
	}

	public int getR_id() {
		return r_id;
	}

	public void setR_id(int r_id) {
		this.r_id = r_id;
	}

	public String getR_status() {
		return r_status;
	}

	public void setR_status(String r_status) {
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

	public int getR_type_id() {
		return r_type_id;
	}

	public void setR_type_id(int r_type_id) {
		this.r_type_id = r_type_id;
	}

	public int getR_amount() {
		return r_amount;
	}

	public void setR_amount(int r_amount) {
		this.r_amount = r_amount;
	}

	public String getR_description() {
		return r_description;
	}

	public void setR_description(String r_description) {
		this.r_description = r_description;
	}

	@Override
	public String toString() {
		return "Reimbursement [r_id=" + r_id + ", r_status=" + r_status + ", r_author=" + r_author + ", r_type="
				+ r_type + ", r_amount=" + r_amount + ", r_description=" + r_description + "]";
	}

	

}
