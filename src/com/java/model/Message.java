package com.java.model;

public class Message {
	private String msg = "";

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void ErrorHandling(String key, String value) {
		if (value == null) {
			this.msg = key + " must be specified!";
		} else if (value.isEmpty()) {
			this.msg = key + " cannot be empty!";
		} else if (key.equals("u_id")&&!value.matches("[0-9]+")) {
			this.msg="ID must contain only numbers!";
		}else if (key.equals("username") && !value.matches("([a-zA-Z0-9_]+){3,}")) {
			this.msg = "Not a valid user name!";
		} else if (key.equals("firstname") && !value.matches("[a-zA-Z]+\\.?")) {
			this.msg = "First name must contain only English letters!";
		} else if (key.equals("lastname") && !value.matches("[a-zA-Z]+\\.?")) {
			this.msg = "Last name must contain English letters!";
		} else if (key.equals("email")
				&& !value.matches("^([a-zA-Z0-9._-]){3,}@([a-zA-Z0-9.-]){3,}\\.[a-zA-Z]{2,4}$")) {
			this.msg = "Not a valid e-mail address!";
		} else if (key.equals("pass") && !value.matches("^(?=.*[0-9]).{5,}$|(?=.*[A-Z]).{5,}$")) {
			this.msg = "Password must be at least 5 chars containing at least one digit!";
		}

	}

}
