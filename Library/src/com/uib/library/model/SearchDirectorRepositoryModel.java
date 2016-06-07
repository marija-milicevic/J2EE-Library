package com.uib.library.model;

import com.uib.library.model.*;
import java.util.List;

import com.uib.library.domain.Director;

public class SearchDirectorRepositoryModel {
	
	private List<Director> list;
	private int results;
	
	//GETTERS AND SETTERS
	public void setList(List<Director> list) {
		this.list = list;
	}
	public List<Director> getList() {
		return list;
	}
	public void setResults(int results) {
		this.results = results;
	}
	public int getResults() {
		return results;
	}

}
