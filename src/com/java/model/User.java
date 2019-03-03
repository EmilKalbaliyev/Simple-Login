package com.java.model;

/** Simple representation of an employee. Used to seed database
 *  initially. Immutable.
 */

public class User {
  private int u_id;
  private String username,password,email,firstName, lastName;
/**
 * @param u_id
 * @param username
 * @param password
 * @param email
 * @param firstName
 * @param lastName
 */
public User(int u_id, String username, String password, String email, String firstName, String lastName) {
	this.u_id = u_id;
	this.username = username;
	this.password = password;
	this.email = email;
	this.firstName = firstName;
	this.lastName = lastName;
}
public int getU_id() {
	return u_id;
}
public String getUsername() {
	return username;
}
public String getPassword() {
	return password;
}
public String getEmail() {
	return email;
}
public String getFirstName() {
	return firstName;
}
public String getLastName() {
	return lastName;
}
/**
 * 
 */
public User() {
}


}