package com.java;

public class RecordsMain {

	public static void main(String[] args) {
		User user = new User("zensar", "zensar123");
		System.out.println("Username: " + user.username());
		System.out.println("Password: " + user.password());
		System.out.println("User object: " + user);
		User user2 = new User("zensar", "zensar123");
		System.out.println("user.equals(user2): " + user.equals(user2));
	}
}

//Data carrier class

class Person {}
record User(String username, String password) { }//parameterized constructor, getter methods, toString(), hashCode(), equals()
/*
class User {
	private String username;
	private String password;
	public User() {}
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
}
*/
