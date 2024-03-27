package com.Anurdip.online_Food_Store.entity;

public class User {
private int id;
private String firstName;
private String lastName;
private String role;
private String email;
private String password;
private String address;

public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public User(int id, String firstName, String lastName, String role, String email, String password) {
	super();
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.role = role;
	this.email = email;
	this.password = password;
}
public User(String role, String email, String password) {
	super();
	this.role = role;
	this.email = email;
	this.password = password;
}

public User(String firstName, String lastName, String role, String email, String password) {
	super();
	this.firstName = firstName;
	this.lastName = lastName;
	this.role = role;
	this.email = email;
	this.password = password;
}
	
public User()
{
	
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

public String getRole() {
	return role;
}

public void setRole(String role) {
	this.role = role;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

@Override
public String toString() {
	return "firstName=" + firstName + "\nlastName=" + lastName +  "\nemail="
			+ email + "\naddress=" + address;
}


}
