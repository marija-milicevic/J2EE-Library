package com.uib.library.model;

import com.uib.library.model.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileModel {
	
	private CommonsMultipartFile file;
	
	//GETTERS AND SETTERS

	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}

	public CommonsMultipartFile getFile() {
		return file;
	}
	
	// UTILITIES METHODS
    
    public String toString(){
    	String ret="------------ File ------------\n";
    	ret+=  "File: " + file.getOriginalFilename()  +"\n";
    	ret+=	"------------------------------\n";
    	return ret;
    }
	
}