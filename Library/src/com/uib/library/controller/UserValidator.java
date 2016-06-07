package com.uib.library.controller;

import com.uib.library.controller.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uib.library.domain.User;
import com.uib.library.model.UserModel;
import com.uib.library.service.UserServiceApi;

public class UserValidator implements Validator {
	
	@Autowired
	private UserServiceApi userServiceApi;
	private UserModel userModel;
	private String errMessage;

	@Override
	public boolean supports(Class<? extends Object> clazz) {
		return UserModel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "field.required", "Username required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required", "Password required");
		if(errors.hasErrors()){
			errMessage="";
			return;
		}
		userModel = (UserModel) target;
		userModel=userServiceApi.getUserModelByUsernameAndPassword(userModel.getUsername(), userModel.getPassword());
		if(userModel==null){
			errMessage="Wrong username/password or still inactive";		
			errors.reject("field.required");
			return;
		}
	}
	
	public void validateRegistration(Object target, Errors errors){
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "field.required", "First name required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "field.required", "Last name required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "field.required", "Username required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required", "Password required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "field.required", "Email required");
		if(errors.hasErrors()){
			errMessage="";
			return;
		}
		userModel = (UserModel) target;
		//validate email
		if(!checkEmail(userModel.getEmail())){
			errMessage="Invalid email";
			errors.reject("field.required");
			return;
		}
		List<User> list = userServiceApi.getUsersByUsername(userModel.getUsername());
		for(User libUsr:list){
			if(libUsr.getSts()==1){
				errMessage="User with that username already exists";
				errors.reject("field.required");
				return;
			}
			if(libUsr.getEmail()!=null){
				if(libUsr.getEmail().equals(userModel.getEmail())){
					errMessage="User with that email already exists";
					errors.reject("field.required");
					return;
				}
			}
		}				
	}
	
	
	public void validateSingleRegistration(Object target, Errors errors){
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "field.required", "First name required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "field.required", "Last name required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "field.required", "Username required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required", "Password required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required", "Password required");
		if(errors.hasErrors()){
			errMessage="Enter all text fields";
			return;
		}
		userModel = (UserModel) target;
		if(userServiceApi.isUsernameActive(userModel.getUsername(),userModel.getId())){
			errMessage="User with that username already exists";
			errors.reject("field.required");
			return;
		}	
	}
	
	//GETTERS AND SETTERS

	public void setUserServiceApi(UserServiceApi userServiceApi) {
		this.userServiceApi = userServiceApi;
	}

	public UserServiceApi getUserServiceApi() {
		return userServiceApi;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

	public String getErrMessage() {
		return errMessage;
	}
	
	//UTILITIES METHODS
	
	private boolean checkEmail(String email){
		String EMAIL_PATTERN = "^[_A-Za-z0-9-]+@[A-Za-z0-9]+(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}	

}
