package com.uib.library.controller;

import com.uib.library.controller.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.uib.library.domain.User;
import com.uib.library.model.DirectorModel;
import com.uib.library.model.FilmModel;
import com.uib.library.model.GridModel;
import com.uib.library.model.RentModel;
import com.uib.library.model.UserModel;
import com.uib.library.service.UserServiceApi;

@Controller
@SessionAttributes(value = {"usr","regList","filmList","directorList","grid"})
public class UserController {

	@Autowired
	private UserServiceApi userServiceApi;
	@Autowired
	private UserValidator userValidator;
	
	@ModelAttribute("userModel")
	public UserModel createUser() {
		return new UserModel();
	}
	
	//------------ LOGIN SECTION ------------ 
	@RequestMapping("/loginPage")
	public ModelAndView goToLoginPage(){
		return new ModelAndView("login");
	}
	
	@RequestMapping("/login")
	public ModelAndView logIn(/*@ModelAttribute("userModel") UserModel userModel,BindingResult result*/){
		ModelAndView modelAndView = new ModelAndView();		
		//Authentication using validator(replaced with spring security)
		/*userValidator.validate(userModel, result);
		if(result.hasErrors()){
			modelAndView.setViewName("login");
			modelAndView.addObject("info", userValidator.getErrMessage());
			return modelAndView;
		}
		modelAndView.addObject("usr", userValidator.getUserModel());*/
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		if(authentication!=null){
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();						
			UserModel userModel = userServiceApi.getUserModelByUsernameAndMD5Password(userDetails.getUsername(), userDetails.getPassword());
			modelAndView.addObject("usr", userModel);
			List<RentModel> listRents = userServiceApi.getRentsbyUser(userModel.getId());
			modelAndView.addObject("listRents", listRents);
			modelAndView.addObject("grid",new GridModel());
			modelAndView.addObject("filmList",new ArrayList<FilmModel>());
			modelAndView.addObject("directorList",new ArrayList<DirectorModel>());
		}		
		modelAndView.setViewName("user");		
		return modelAndView;
	}
	
	//------------ REGISTRATION SECTION ------------ 
	@RequestMapping("/registrationPage")	                 
	public ModelAndView goToRegistrationPage(){
		return new ModelAndView("register");
	}		
	
	@RequestMapping("/register")	                 
	public ModelAndView register(@ModelAttribute("userModel") UserModel userModel,BindingResult result){
		ModelAndView modelAndView = new ModelAndView();
		userValidator.validateRegistration(userModel, result);
		if(result.hasErrors()){
			modelAndView.setViewName("register");
			modelAndView.addObject("regErr", userValidator.getErrMessage());
			return modelAndView;
		}
		modelAndView.setViewName("login");
		userServiceApi.inseretUser(userModel);
		modelAndView.addObject("info", "Registration request sent");
		return modelAndView;
	}
	
	//------------ ACCESS DENINED SECTION ------------ 
	@RequestMapping("/denied")
	public String accessDenied(){
		return "redirect:homePage.htm?deny=Access denied !";
	}
	
	//------------ ERROR PAGE SECTION ------------ 
	@RequestMapping("/error")	                 
	public ModelAndView errorOccured(){
		return new ModelAndView("error");
	}		
	
	//------------ RETURN TO HOMEPAGE SECTION ------------
	@RequestMapping("/homePage")
	public ModelAndView goToHomePage(@ModelAttribute("usr") UserModel usr){
		ModelAndView modelAndView = new ModelAndView("user");
		List<RentModel> listRents = userServiceApi.getRentsbyUser(usr.getId());
		modelAndView.addObject("listRents", listRents);
		return modelAndView;
	}
	
	//------------ REGISTRATIONS REQUEST MANAGMENT SECTION ------------
	@PreAuthorize("hasRole('administrator')")
	@RequestMapping("/regManagmentPage")	                 
	public ModelAndView goToRegManagmentPage(){
		return new ModelAndView("registrationManagment");
	}
	
	@PreAuthorize("hasRole('administrator')")
	@RequestMapping("/regManagment")	                 
	public ModelAndView listRegistrations(@ModelAttribute("userModel") UserModel userModel,@ModelAttribute("grid")GridModel grid){
		ModelAndView modelAndView = new ModelAndView("registrationManagment");
		//+++ Paging and sorting +++
		grid = new GridModel();
		editGrid(userServiceApi.getNumberOfInactiveUsers(), "registrationManagment", grid);
		grid.setCmpString("frstNm");
		modelAndView.addObject("grid", grid);
		List<User> regList = userServiceApi.getInactiveUsersByGrid(grid);
		modelAndView.addObject("regList", regList);
		return modelAndView;
	}
	
	@PreAuthorize("hasRole('administrator')")
	@RequestMapping("/singleRegistrationPage")	                 
	public ModelAndView goToSingleRegistrationPage(@ModelAttribute("userModel") UserModel userModel){
		ModelAndView modelAndView = new ModelAndView("singleRegistration");
		userModel=userServiceApi.getUserModelByUserId(userModel.getId());
		modelAndView.addObject("regReq", userModel);
		return modelAndView;
	}
	
	@PreAuthorize("hasRole('administrator')")
	@RequestMapping("/singleRegistration")	                 
	public ModelAndView changeRegistration(@ModelAttribute("userModel") UserModel userModel,BindingResult result,@ModelAttribute("grid")GridModel grid){
		ModelAndView modelAndView = new ModelAndView();
		userValidator.validateSingleRegistration(userModel, result);
		if(result.hasErrors()){
			modelAndView.addObject("regReq", userModel);
			modelAndView.setViewName("singleRegistration");
			modelAndView.addObject("singleRegErr", userValidator.getErrMessage());	
			return modelAndView;
		}
		modelAndView.setViewName("registrationManagment");
		try{
		userServiceApi.updateUser(userModel);
		}catch (MailException e) {
			modelAndView.addObject("regReq", userModel);
			modelAndView.setViewName("singleRegistration");
			modelAndView.addObject("singleRegErr", "Error sending mail , try again later");	
			return modelAndView;
		}
		editGrid(userServiceApi.getNumberOfInactiveUsers(), "registrationManagment", grid);		
		if(grid.getItems()%5==0  && grid.getPageNumber()>0){
			grid.setPageNumber(grid.getPageNumber()-1);
		}
		List<User> regList = userServiceApi.getInactiveUsersByGrid(grid);
		modelAndView.addObject("regList", regList);
		//+++ Paging and sorting +++			
		modelAndView.addObject("grid", grid);
		return modelAndView;
	} 
	
	//GETTERS AND SETTERS

	public void setUserServiceApi(UserServiceApi userServiceApi) {
		this.userServiceApi = userServiceApi;
	}

	public UserServiceApi getUserServiceApi() {
		return userServiceApi;
	}

	public void setUserValidator(UserValidator userValidator) {
		this.userValidator = userValidator;
	}

	public UserValidator getUserValidator() {
		return userValidator;
	}
	
	//UTILITES METHODS
	
	private void editGrid(int items,String retPage,GridModel grid){		
		grid.setItems(items);	
		grid.setPages(((int)Math.ceil((double)grid.getItems()/(double)grid.getItemsPerPage())-1));
		grid.setRetPage(retPage);
	}

}
