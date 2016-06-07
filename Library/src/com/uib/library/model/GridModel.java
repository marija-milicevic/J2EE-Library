package com.uib.library.model;

import com.uib.library.model.*;

public class GridModel {		
		
	private int pageNumber=0;	
	private int itemsPerPage=5;
	private int pages;
	private int items;
	private String retPage;
	private String cmpString;
	private String searchString="";
	
	private boolean sortDesc;
	
	private int sortRegFirstName=2;
	private int sortRegLastName=2;
	private int sortRegUsername=2;
	
	private int sortDirectorName=2;
	private int sortDirectorDateOfBirth=2;
	private int sortDirectorCreateDate=2;
	private int sortDirectorUpdateDate=2;
	private int sortDirectorCreateUser=2;
	private int sortDirectorUpdateUser=2;
	
	private int sortFilmTitle=2;
	private int sortFilmCount=2;
	private int sortFilmAthrName=2;
        private int sortFilmGenre=2;
        private int sortFilmStar=2;
	private int sortFilmCreateDate=2;
	private int sortFilmUpdateDate=2;
	private int sortFilmCreateUser=2;
	private int sortFilmUpdateUser=2;
	private int sortFilmImage=2;
	
	//GETTERS AND SETTERS
	
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public int getPages() {
		return pages;
	}
	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}
	public int getItemsPerPage() {
		return itemsPerPage;
	}
	public void setItems(int items) {
		this.items = items;
	}
	public int getItems() {
		return items;
	}
	public void setRetPage(String retPage) {
		this.retPage = retPage;
	}
	public String getRetPage() {
		return retPage;
	}	
	
	public void setCmpString(String cmpString) {
		this.cmpString = cmpString;
	}
	public String getCmpString() {
		return cmpString;
	}
	
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	public String getSearchString() {
		return searchString;
	}
	
	public boolean isSortDesc() {
		return sortDesc;
	}
	public void setSortDesc(boolean sortDesc) {
		this.sortDesc = sortDesc;
	}
	public int getSortRegFirstName() {
		return sortRegFirstName;
	}
	public void setSortRegLastName(int sortRegLastName) {
		this.sortRegLastName = sortRegLastName;
	}
	public int getSortRegLastName() {
		return sortRegLastName;
	}
	public void setSortRegUsername(int sortRegUsername) {
		this.sortRegUsername = sortRegUsername;
	}
	public int getSortRegUsername() {
		return sortRegUsername;
	}
	public int getSortDirectorName() {
		return sortDirectorName;
	}
	public void setSortDirectorName(int sortDirectorName) {
		this.sortDirectorName = sortDirectorName;
	}
	public int getSortDirectorDateOfBirth() {
		return sortDirectorDateOfBirth;
	}
	public void setSortDirectorDateOfBirth(int sortDirectorDateOfBirth) {
		this.sortDirectorDateOfBirth = sortDirectorDateOfBirth;
	}
	public int getSortDirectorCreateDate() {
		return sortDirectorCreateDate;
	}
	public void setSortDirectorCreateDate(int sortDirectorCreateDate) {
		this.sortDirectorCreateDate = sortDirectorCreateDate;
	}
	public int getSortDirectorUpdateDate() {
		return sortDirectorUpdateDate;
	}
	public void setSortDirectorUpdateDate(int sortDirectorUpdateDate) {
		this.sortDirectorUpdateDate = sortDirectorUpdateDate;
	}
	public void setSortDirectorCreateUser(int sortDirectorCreateUser) {
		this.sortDirectorCreateUser = sortDirectorCreateUser;
	}
	public int getSortDirectorCreateUser() {
		return sortDirectorCreateUser;
	}
	public void setSortDirectorUpdateUser(int sortDirectorUpdateUser) {
		this.sortDirectorUpdateUser = sortDirectorUpdateUser;
	}
	public int getSortDirectorUpdateUser() {
		return sortDirectorUpdateUser;
	}
	public int getSortFilmTitle() {
		return sortFilmTitle;
	}
	public void setSortFilmTitle(int sortBookTitle) {
		this.sortFilmTitle = sortBookTitle;
	}
	public int getSortFilmCount() {
		return sortFilmCount;
	}
	public void setSortFilmCount(int sortBookCount) {
		this.sortFilmCount = sortBookCount;
	}
	public int getSortFilmAthrName() {
		return sortFilmAthrName;
	}
	public void setSortFilmAthrName(int sortBookAthrName) {
		this.sortFilmAthrName = sortBookAthrName;
	}
	public int getSortFilmCreateDate() {
		return sortFilmCreateDate;
	}
	public void setSortFilmCreateDate(int sortBookCreateDate) {
		this.sortFilmCreateDate = sortBookCreateDate;
	}
	public void setSortFilmUpdateDate(int sortBookUpdateDate) {
		this.sortFilmUpdateDate = sortBookUpdateDate;
	}
	public int getSortFilmUpdateDate() {
		return sortFilmUpdateDate;
	}
	public void setSortFilmCreateUser(int sortBookCreateUser) {
		this.sortFilmCreateUser = sortBookCreateUser;
	}
	public int getSortFilmCreateUser() {
		return sortFilmCreateUser;
	}
	public void setSortFilmUpdateUser(int sortBookUpdateUser) {
		this.sortFilmUpdateUser = sortBookUpdateUser;
	}
	public int getSortFilmUpdateUser() {
		return sortFilmUpdateUser;
	}
	public int getSortFilmImage() {
		return sortFilmImage;
	}
	public void setSortFilmImage(int sortBookImage) {
		this.sortFilmImage = sortBookImage;
	}

    public int getSortFilmGenre() {
        return sortFilmGenre;
    }

    public void setSortFilmGenre(int sortFilmGenre) {
        this.sortFilmGenre = sortFilmGenre;
    }

    public int getSortFilmStar() {
        return sortFilmStar;
    }

    public void setSortFilmStar(int sortFilmStar) {
        this.sortFilmStar = sortFilmStar;
    }
        
	
	// UTILITIES METHODS    
    
	public String toString(){       
    	String ret="------------ Grid ------------\n";
    	ret+=  "PgNm: " + pageNumber  +"\n";
    	ret+=  "ItPp: " + itemsPerPage  +"\n";
    	ret+=  "Pags: " + pages  +"\n";
    	ret+=  "Itms: " + items  +"\n";
    	ret+=  "rtPg: " + retPage  +"\n";
    	ret+=	"------------------------------\n";
    	return ret;
    }
	public void setSortRegFirstName(int sortRegFirstName) {
		this.sortRegFirstName = sortRegFirstName;
	}

}
