package com.uib.library.model;

import com.uib.library.model.*;
import java.util.Date;

public class RentModel {
	
	private String titl;
	private String director;
	private Date dateOfRent;
	
	//GETTERS AND SETTERS
	
	public void setTitl(String titl) {
		this.titl = titl;
	}
	public String getTitl() {
		return titl;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getDirector() {
		return director;
	}
	public void setDateOfRent(Date dateOfRent) {
		this.dateOfRent = dateOfRent;
	}
	public Date getDateOfRent() {
		return dateOfRent;
	}
	
	// UTILITIES METHODS
    
    public String toString(){       
    	String ret="------------ Rent ------------\n";
    	ret+=  "Titl: " + titl  +"\n";
    	ret+=  "Athr: " + director  +"\n";
    	ret+=  "DtRt: " + dateOfRent  +"\n";
    	ret+=	"------------------------------\n";
    	return ret;
    }
	
	

}
