package com.uib.library.model;

import com.uib.library.model.*;
import java.util.List;

import com.uib.library.domain.Film;

public class SearchFilmRepositoryModel {
	
	private List<Film> list;
	private int results;
	
	//GETTERS AND SETTERS
	
	public void setResults(int results) {
		this.results = results;
	}
	public int getResults() {
		return results;
	}
	public void setList(List<Film> list) {
		this.list = list;
	}
	public List<Film> getList() {
		return list;
	}
	
	

}
