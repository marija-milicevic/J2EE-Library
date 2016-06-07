package com.uib.library.model;

import com.uib.library.model.*;
import java.util.List;

import com.uib.library.domain.User;

public class SearchRegRepositoryModel {
	
	private List<User> list;
	private int results;
	
	// GETTERS AND SETTERS
	
	public void setList(List<User> list) {
		this.list = list;
	}
	public List<User> getList() {
		return list;
	}
	public void setResults(int results) {
		this.results = results;
	}
	public int getResults() {
		return results;
	}

}
