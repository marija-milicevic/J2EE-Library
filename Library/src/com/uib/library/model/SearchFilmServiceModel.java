package com.uib.library.model;

import com.uib.library.model.*;
import java.util.List;

public class SearchFilmServiceModel {
	
	private List<FilmModel> list;
	private int results;
	
	//GETTERS AND SETTERS
	
	public void setList(List<FilmModel> list) {
		this.list = list;
	}
	public List<FilmModel> getList() {
		return list;
	}
	public void setResults(int results) {
		this.results = results;
	}
	public int getResults() {
		return results;
	}
	
	

}
