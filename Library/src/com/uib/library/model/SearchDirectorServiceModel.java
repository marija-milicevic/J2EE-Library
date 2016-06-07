package com.uib.library.model;

import com.uib.library.model.*;
import java.util.List;

public class SearchDirectorServiceModel {
	
	private List<DirectorModel> list;
	private int results;
	
	//GETTERS AND SETTERS
	public void setList(List<DirectorModel> list) {
		this.list = list;
	}
	public List<DirectorModel> getList() {
		return list;
	}
	public void setResults(int results) {
		this.results = results;
	}
	public int getResults() {
		return results;
	}

}
