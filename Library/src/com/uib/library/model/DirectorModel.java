package com.uib.library.model;

import com.uib.library.model.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DirectorModel {
	
    private int directorID;
    private String name;
    private Date dtOfBth;
    private Date creatDt;
    private Date updtDt;
    private int creatUsrId;
    private int updtUsrId;
    private String creatUsr;
    private String updtUsr;
    
    private String creatDtString;
    private String dtOfBthString;
    
    //GETTERS AND SETTERS
    
	public String getCreatUsr() {
		return creatUsr;
	}
	public void setCreatUsr(String creatUsr) {
		this.creatUsr = creatUsr;
	}
	public String getUpdtUsr() {
		return updtUsr;
	}
	public void setUpdtUsr(String updtUsr) {
		this.updtUsr = updtUsr;
	}
	public int getDirectorID() {
		return directorID;
	}
	public void setDirectorID(int directorID) {
		this.directorID = directorID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDtOfBth() {
		return dtOfBth;
	}
	public void setDtOfBth(Date dtOfBth) {
		this.dtOfBth = dtOfBth;
	}
	public Date getCreatDt() {
		return creatDt;
	}
	public void setCreatDt(Date creatDt) {
		this.creatDt = creatDt;
	}
	public Date getUpdtDt() {
		return updtDt;
	}
	public void setUpdtDt(Date updtDt) {
		this.updtDt = updtDt;
	}
	public int getCreatUsrId() {
		return creatUsrId;
	}
	public void setCreatUsrId(int creatUsrId) {
		this.creatUsrId = creatUsrId;
	}
	public int getUpdtUsrId() {
		return updtUsrId;
	}
	public void setUpdtUsrId(int updtUsrId) {
		this.updtUsrId = updtUsrId;
	}
	public void setCreatDtString(String creatDtString) {
		this.creatDtString = creatDtString;
		if(creatDtString.equals("") || creatDtString==null){
			this.creatDt=null;
			return;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.creatDt = sdf.parse(creatDtString);
		} catch (Exception e) {/*e.printStackTrace();*/}
	}
	public String getCreatDtString() {
		return creatDtString;
	}
	public void setDtOfBthString(String dtOfBthString) {
		this.dtOfBthString = dtOfBthString;
		if(dtOfBthString.equals("") || dtOfBthString==null){
			this.dtOfBth=null;
			return;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.dtOfBth = sdf.parse(dtOfBthString);
		} catch (Exception e) {/*e.printStackTrace();*/}
	}
	public String getDtOfBthString() {
		return dtOfBthString;
	}    
	
	// UTILITIES METHODS
    
    public String toString(){       
    	String ret="------------ Author ------------\n";
    	ret+=  "atId: " + directorID  +"\n";
    	ret+=  "Auth: " + name  +"\n";
    	ret+=  "Birt: " + dtOfBth  +"\n";
    	ret+=  "CrDt: " + creatDt  +"\n";
    	ret+=  "UpDt: " + updtDt  +"\n";
    	ret+=  "CrUs: " + creatUsr  +"\n";
    	ret+=  "UpUs: " + updtUsr  +"\n";
    	ret+=  "CrId: " + creatUsrId  +"\n";
    	ret+=  "UpId: " + updtUsrId  +"\n";
    	ret+=	"--------------------------------\n";
    	return ret;
    }

}
