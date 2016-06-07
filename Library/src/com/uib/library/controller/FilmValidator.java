package com.uib.library.controller;

import com.uib.library.controller.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uib.library.model.DirectorModel;
import com.uib.library.model.FilmModel;
import com.uib.library.service.FilmServiceApi;

public class FilmValidator implements Validator {
	
	@Autowired
	private FilmServiceApi filmServiceApi;
	private FilmModel filmModel;
	private DirectorModel directorModel;
	private String errMessage;

	@Override
	public boolean supports(Class<? extends Object> clazz) {
		return FilmModel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
	}
	
	public void validateFilm(Object target, Errors errors, boolean isNew) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titl", "field.required", "Titl required");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "directorName", "field.required", "Director required");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "genre", "field.required", "Genre required");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "star", "field.required", "Stars required");
		if(errors.hasErrors()){
			errMessage="Enter all text fields";
			return;
		}
		filmModel = (FilmModel) target;
		if(filmModel.getCnt()<0){
			errMessage="Remaining films can't be negative number";
			errors.reject("field.required");
			return;
		}
                if(filmModel.getStar()<5 || filmModel.getStar()>10){
                        errMessage="Film raiting can be from 5 to 10 stars";
			errors.reject("field.required");
			return;
                }
		if(filmServiceApi.isFilmActive(filmModel.getTitl(), filmModel.getFilmID(), isNew)){
			errMessage="Film with that title already exists";
			errors.reject("field.required");
			return;
		}
		if(filmServiceApi.isExistingImg(filmModel.getImg(), filmModel.getFilmID(), isNew)){
			errMessage="Film with that image already exists";
			errors.reject("field.required");
			return;
		}
		
	}
	
	public void validateDirector(Object target, Errors errors, boolean isNew){
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required", "Director name required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dtOfBthString", "field.required", "Date of birth required");
		if(errors.hasErrors()){
			errMessage="Enter all text fields";
			return;
		}
		directorModel = (DirectorModel) target;
		if(!isValidDate(directorModel.getDtOfBthString())){
			errMessage="Date is not valid(needed yyyy-mm-dd)";
			errors.reject("field.required");
			return;
		}
		if(filmServiceApi.isDirectorActive(directorModel.getName(), directorModel.getDirectorID(), isNew)){
			errMessage="Director with that name already exists";
			errors.reject("field.required");
			return;
		}
	}

	//GETTERS AND SETTERS
	
	public void setFilmServiceApi(FilmServiceApi filmServiceApi) {
		this.filmServiceApi = filmServiceApi;
	}
	
	public FilmServiceApi getFilmServiceApi() {
		return filmServiceApi;
	}
	public FilmModel getFilmModel() {
		return filmModel;
	}

	public void setFilmModel(FilmModel filmModel) {
		this.filmModel = filmModel;
	}

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}
	
	//UTILITIES METHOD
	
	public boolean isValidDate(String inDate) {

	    if (inDate == null)
	      return false;

	    //set the format to use as a constructor argument
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    
	    if (inDate.trim().length() != dateFormat.toPattern().length())
	      return false;

	    dateFormat.setLenient(false);
	    
	    try {
	      //parse the inDate parameter
	      dateFormat.parse(inDate.trim());
	    }
	    catch (ParseException pe) {
	      return false;
	    }
	    return true;
	  }

}
