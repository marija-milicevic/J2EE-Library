package com.uib.library.model;

import com.uib.library.model.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.uib.library.domain.Director;

public class FilmModel {
	
    private int filmID;
    private Director director;
    private String directorName;
    private String genre;
    private int star;
    private String titl;
    private String img;
    private boolean rntInd;
    private int cnt;
    private Date creatDt;
    private Date updtDt;
    private String creatUsr;
    private String updtUsr;
    private int creatUsrId;
    private int updtUsrId;
    
    private String creatDtString;    
    
    //GETTERS AND SETTERS
    
	public int getFilmID() {
		return filmID;
	}
	public void setFilmID(int filmID) {
		this.filmID = filmID;
	}
	public Director getDirector() {
		return director;
	}
	public void setDirector(Director director) {
		this.director = director;
	}
	public String getDirectorName() {
		return directorName;
	}
	public void setDirecotrName(String directorName) {
		this.directorName = directorName;
	}
	public String getTitl() {
		return titl;
	}
	public void setTitl(String titl) {
		this.titl = titl;
	}
	public boolean isRntInd() {
		return rntInd;
	}
	public void setRntInd(boolean rntInd) {
		this.rntInd = rntInd;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.creatDt = sdf.parse(creatDtString);
		} catch (Exception e) {/*e.printStackTrace();*/}
	}
	public String getCreatDtString() {
		return creatDtString;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getImg() {
		return img;
	}

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
        
	
    // UTILITIES METHODS
    
    public String toString(){
    	String ret="------------ Book ------------\n";
    	ret+=  "bkId: " + filmID  +"\n";
    	ret+=  "Auth: " + directorName  +"\n";
    	ret+=  "Titl: " + titl  +"\n";
    	ret+=  "Imag: " + img  +"\n";
    	ret+=  "rntI: " + rntInd  +"\n";
    	ret+=  "Coun: " + cnt  +"\n";
    	ret+=  "CrDt: " + creatDt  +"\n";
    	ret+=  "UpDt: " + updtDt  +"\n";
    	ret+=  "CrUs: " + creatUsr  +"\n";
    	ret+=  "UpUs: " + updtUsr  +"\n";
    	ret+=  "CrId: " + creatUsrId  +"\n";
    	ret+=  "UpId: " + updtUsrId  +"\n";
    	ret+=	"------------------------------\n";
    	return ret;
    }

}
