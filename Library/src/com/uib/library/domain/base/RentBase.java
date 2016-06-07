package com.uib.library.domain.base;
// Generated Jun 26, 2013 10:09:32 AM by Hibernate Tools 3.3.0.GA


import com.uib.library.domain.base.*;
import com.uib.library.domain.Film;
import com.uib.library.domain.User;
import java.util.Date;

/**
 * RentBase generated by hbm2java
 */
public class RentBase  implements java.io.Serializable {


     /**
	 * 
	 */
	private static final long serialVersionUID = 6946433129741671945L;
	private RentId id;
     private User libUsr;
     private Film film;
     private Date dtOfRnt;
     private Date dtOfRet;

    public RentBase() {
    }

	
    public RentBase(RentId id, User libUsr, Film film, Date dtOfRnt) {
        this.id = id;
        this.libUsr = libUsr;
        this.film = film;
        this.dtOfRnt = dtOfRnt;
    }
    public RentBase(RentId id, User libUsr, Film film, Date dtOfRnt, Date dtOfRet) {
       this.id = id;
       this.libUsr = libUsr;
       this.film = film;
       this.dtOfRnt = dtOfRnt;
       this.dtOfRet = dtOfRet;
    }
   
    public RentId getId() {
        return this.id;
    }
    
    public void setId(RentId id) {
        this.id = id;
    }
    public User getLibUsr() {
        return this.libUsr;
    }
    
    public void setLibUsr(User libUsr) {
        this.libUsr = libUsr;
    }
    public Film getFilm() {
        return this.film;
    }
    
    public void setFilm(Film film) {
        this.film = film;
    }
    public Date getDtOfRnt() {
        return this.dtOfRnt;
    }
    
    public void setDtOfRnt(Date dtOfRnt) {
        this.dtOfRnt = dtOfRnt;
    }
    public Date getDtOfRet() {
        return this.dtOfRet;
    }
    
    public void setDtOfRet(Date dtOfRet) {
        this.dtOfRet = dtOfRet;
    }




}

