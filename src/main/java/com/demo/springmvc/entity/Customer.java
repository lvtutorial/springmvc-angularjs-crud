package com.demo.springmvc.entity;

public class Customer implements Cloneable  {
	
	Integer id;
	String username;
	String fullname;
	String email;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "{id:" + id + ", username:" + username + ", fullname:" + fullname + ", email:" + email + "}";
		//String json = writeValueAsString(this);
		//return json;
	}	
	
	@Override
	public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }	
}
