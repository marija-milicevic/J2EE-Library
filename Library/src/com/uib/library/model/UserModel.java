package com.uib.library.model;

import com.uib.library.model.*;


public class UserModel{
	
	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private int allowedFilms;
	private String status;
	private boolean admin;
	
	//GETTERS AND SETTERS
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		return username;
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
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public void setAllowedFilms(int allowedFilms) {
		this.allowedFilms = allowedFilms;
	}
	public int getAllowedFilms() {
		return allowedFilms;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
    // UTILITIES METHODS
    
    public String toString(){
    	String ret="------------ User ------------\n";
    	ret+=  "usId: " + id  +"\n";
    	ret+=  "Usrn: " + username  +"\n";
    	ret+=  "Pswd: " + password  +"\n";
    	ret+=  "FirN: " + firstName  +"\n";
    	ret+=  "LstN: " + lastName  +"\n";
    	ret+=  "Emal: " + email +  "\n";
    	ret+=  "AlBk: " + allowedFilms  +"\n";
    	ret+=  "Stat: " + status  +"\n";
    	ret+=  "Admn: " + admin  +"\n";
    	ret+=	"------------------------------\n";
    	return ret;
    }

}
