package com.uib.library.controller;

import com.uib.library.controller.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.uib.library.domain.User;
import com.uib.library.model.DirectorModel;
import com.uib.library.model.FilmModel;
import com.uib.library.model.GridModel;
import com.uib.library.model.SearchDirectorServiceModel;
import com.uib.library.model.SearchFilmServiceModel;
import com.uib.library.model.SearchRegRepositoryModel;
import com.uib.library.service.FilmServiceApi;
import com.uib.library.service.UserServiceApi;

@Controller
@SessionAttributes(value = {"usr","regList","filmList","directorList","grid"})
public class GridController {
	
	@Autowired
	private FilmServiceApi filmServiceApi;
	@Autowired
	private UserServiceApi userServiceApi;
	
	//------------ PREVIOUS PAGE SECTION ------------
	@RequestMapping("/prevPage")
	public ModelAndView goToPrevPage(@ModelAttribute("grid") GridModel grid){
		ModelAndView modelAndView = new ModelAndView(grid.getRetPage());
		if(grid.getPageNumber()!=0){
			grid.setPageNumber(grid.getPageNumber()-1);
			modelAndView.addObject("grid",grid);
			setListOnGrid(grid,modelAndView);
		}
		return modelAndView;
	}
	
	//------------ NEXT PAGE SECTION ------------
	@RequestMapping("/nextPage")
	public ModelAndView goToNextPage(@ModelAttribute("grid") GridModel grid){
		ModelAndView modelAndView = new ModelAndView(grid.getRetPage());
		if(grid.getPageNumber()!=grid.getPages()){
			grid.setPageNumber(grid.getPageNumber()+1);			
			modelAndView.addObject("grid",grid);
			setListOnGrid(grid,modelAndView);
		}
		return modelAndView;
	}
	
	//------------ FIRST PAGE SECTION ------------
	@RequestMapping("/firstPage")
	public ModelAndView goToFirstPage(@ModelAttribute("grid") GridModel grid){
		ModelAndView modelAndView = new ModelAndView(grid.getRetPage());
		if(grid.getPageNumber()!=0){
			grid.setPageNumber(0);
			modelAndView.addObject("grid",grid);
			setListOnGrid(grid,modelAndView);
		}
		return modelAndView;
	}
	
	//------------ LAST PAGE SECTION ------------
	@RequestMapping("/lastPage")
	public ModelAndView goToLastPage(@ModelAttribute("grid") GridModel grid){
		ModelAndView modelAndView = new ModelAndView(grid.getRetPage());
		if(grid.getPageNumber()!=grid.getPages()){
			grid.setPageNumber(grid.getPages());
			modelAndView.addObject("grid",grid);
			setListOnGrid(grid,modelAndView);
		}		
		return modelAndView;
	}
	
	//------------ GO TO CUSTOM PAGE SECTION ------------
	@RequestMapping("/customPage")
	public ModelAndView goToCustomPage(@ModelAttribute("grid") GridModel grid){
		ModelAndView modelAndView = new ModelAndView(grid.getRetPage());
		modelAndView.addObject("grid",grid);
		setListOnGrid(grid,modelAndView);
		return modelAndView;
	}
	
	//------------ SET ITEMS PER PAGE SECTION ------------
	@RequestMapping("/itemsPage")
	public ModelAndView setItemsPerPage(@ModelAttribute("grid") GridModel grid){
		ModelAndView modelAndView = new ModelAndView(grid.getRetPage());
		grid.setPages((int)Math.ceil((double)grid.getItems()/(double)grid.getItemsPerPage())-1);
		grid.setPageNumber(0);
		modelAndView.addObject("grid",grid);
		setListOnGrid(grid,modelAndView);
		return modelAndView;
	}	
	
	//------------ SORT SECTION ------------
	@RequestMapping("/sort")
	public ModelAndView sort(@ModelAttribute("grid") GridModel grid,String sort){
		ModelAndView modelAndView = new ModelAndView(grid.getRetPage());
		List<FilmModel> listF = new ArrayList<FilmModel>();
		//sort films
		if(sort.subSequence(0, 2).equals("bk")){	
			//rented films
			if(sort.contains("Rnt")){
				setFilmImgSort(sort.substring(6),grid);
				grid.setCmpString(sort.substring(6));
				listF = filmServiceApi.getAvailableFilmsFilmsByGrid(grid);
			}
			//film managment
			else{
				setFilmImgSort(sort.substring(3),grid);
				grid.setCmpString(sort.substring(3));
				listF = filmServiceApi.getFilmsByGrid(grid);
			}						
			modelAndView.addObject("filmList",listF);
		}
		//sort authors
		else if(sort.subSequence(0, 2).equals("at")){
			setDirectorImgSort(sort.substring(3),grid);
			grid.setCmpString(sort.substring(3));			
			List<DirectorModel> list = filmServiceApi.getDirectorsByGrid(grid);
			modelAndView.addObject("directorList",list);
		}
		//sort registrations
		else if(sort.subSequence(0, 2).equals("rg")){
			setRegImgSort(sort.substring(3),grid);
			grid.setCmpString(sort.substring(3));
			List<User> list = userServiceApi.getInactiveUsersByGrid(grid);
			modelAndView.addObject("regList",list);
		}
		modelAndView.addObject("grid",grid);
		return modelAndView;
	}
	
	//------------ SEARCH SECTION ------------
	@RequestMapping("/searchFirstName")
	public ModelAndView searchRegByFirstName(String frstNm,@ModelAttribute("grid") GridModel grid){
		SearchRegRepositoryModel mod = userServiceApi.searchRegByFirstName(frstNm, grid.getItemsPerPage());
		grid.setSearchString(!frstNm.equals("")? "frstNm"+frstNm : "");
		ModelAndView mav = new ModelAndView("registrationManagment");
		mav.addObject("regList",mod.getList());
		editGrid(mod.getResults(),grid);
		mav.addObject("grid",grid);
		return mav;
	}
	
	@RequestMapping("/searchLastName")
	public ModelAndView searchRegByLastName(String lstNm,@ModelAttribute("grid") GridModel grid){
		SearchRegRepositoryModel mod = userServiceApi.searchRegByFirstName(lstNm, grid.getItemsPerPage());
		grid.setSearchString(!lstNm.equals("")? "lstNm"+lstNm : "");
		ModelAndView mav = new ModelAndView("registrationManagment");
		mav.addObject("regList",mod.getList());
		editGrid(mod.getResults(),grid);
		mav.addObject("grid",grid);
		return mav;
	}
	
	@RequestMapping("/searchUsername")
	public ModelAndView searchRegByUsername(String usrnm,@ModelAttribute("grid") GridModel grid){
		SearchRegRepositoryModel mod = userServiceApi.searchRegByFirstName(usrnm, grid.getItemsPerPage());
		grid.setSearchString(!usrnm.equals("")? "usrnm"+usrnm : "");
		ModelAndView mav = new ModelAndView("registrationManagment");
		mav.addObject("regList",mod.getList());
		editGrid(mod.getResults(),grid);
		mav.addObject("grid",grid);
		return mav;
	}
	
	@RequestMapping("/searchDirector")
	public ModelAndView searchDirectorByName(String directorName,@ModelAttribute("grid") GridModel grid){
		SearchDirectorServiceModel mod = filmServiceApi.searchDirectorsByName(directorName, grid.getItemsPerPage());
		grid.setSearchString(!directorName.equals("")? "directorName_"+directorName : "");
		ModelAndView mav = new ModelAndView("directorManagment");
		mav.addObject("directorList",mod.getList());
		editGrid(mod.getResults(),grid);
		mav.addObject("grid",grid);
		return mav;
	}
	
	@RequestMapping("/searchFilm")
	public ModelAndView searchFilmByTitle(String filmTitl, String retPage, @ModelAttribute("grid") GridModel grid){
		SearchFilmServiceModel mod =  filmServiceApi.searchFilmByTitle(filmTitl,retPage.equals("filmRent"),grid.getItemsPerPage());
		grid.setSearchString(!filmTitl.equals("")? "titl_"+filmTitl : "");
		return getFilmSearchMAV(mod.getList(),grid,retPage,mod.getResults());
	}
	
	@RequestMapping("/searchCnt")
	public ModelAndView searchFilmByCnt(String filmCnt, String retPage, @ModelAttribute("grid") GridModel grid){
		try{
			Integer.parseInt(filmCnt);			
		}catch (NumberFormatException e) {
			filmCnt="";
		}		
		SearchFilmServiceModel mod =  filmServiceApi.searchFilmCnt(filmCnt,retPage.equals("filmRent"),grid.getItemsPerPage());
		grid.setSearchString(!filmCnt.equals("")? "cnt_"+filmCnt : "");
		return getFilmSearchMAV(mod.getList(),grid,retPage,mod.getResults());
	}
        @RequestMapping("/searchGenre")
	public ModelAndView searchFilmByGenre(String genre, String retPage, @ModelAttribute("grid") GridModel grid){
		SearchFilmServiceModel mod =  filmServiceApi.searchFilmByGenre(genre,retPage.equals("filmRent"),grid.getItemsPerPage());
		grid.setSearchString(!genre.equals("")? "genre_"+genre : "");
		return getFilmSearchMAV(mod.getList(),grid,retPage,mod.getResults());
	}	
	
	@RequestMapping("/searchDirectorFilm")
	public ModelAndView searchFilmByDirector(String directorNm, String retPage, @ModelAttribute("grid") GridModel grid){
		SearchFilmServiceModel mod =  filmServiceApi.searchFilmByDirector(directorNm,retPage.equals("filmRent"),grid.getItemsPerPage());
		grid.setSearchString(!directorNm.equals("")? "director_"+directorNm : "");
		return getFilmSearchMAV(mod.getList(),grid,retPage,mod.getResults());
	}
	
	//GETTERS AND SETTERS
	
	public void setFilmServiceApi(FilmServiceApi filmServiceApi) {
		this.filmServiceApi = filmServiceApi;
	}
	public FilmServiceApi getFilmServiceApi() {
		return filmServiceApi;
	}
	public void setUserServiceApi(UserServiceApi userServiceApi) {
		this.userServiceApi = userServiceApi;
	}
	public UserServiceApi getUserServiceApi() {
		return userServiceApi;
	}
	
	//UTILITES METHODS
	
	private void editGrid(int items, GridModel grid){
		grid.setItems(items);	
		grid.setPages(((int)Math.ceil((double)grid.getItems()/(double)grid.getItemsPerPage())-1));
		grid.setPageNumber(0);
	}
	
	private ModelAndView getFilmSearchMAV(List<FilmModel> list, GridModel grid, String retPage,int size){
		ModelAndView mav = new ModelAndView(retPage);
		mav.addObject("filmList",list);
		editGrid(size,grid);
		mav.addObject("grid",grid);
		return mav;
	}
	
	private void setListOnGrid(GridModel grid,ModelAndView mav){
		if(grid.getRetPage().contains("registration")){
			mav.addObject("regList", userServiceApi.getInactiveUsersByGrid(grid));
		}
		else if(grid.getRetPage().contains("director")){
			mav.addObject("directorList",filmServiceApi.getDirectorsByGrid(grid));
		}
		else if(grid.getRetPage().contains("Rent")){
			mav.addObject("filmList",filmServiceApi.getAvailableFilmsFilmsByGrid(grid));
		}
		else{
			mav.addObject("filmList",filmServiceApi.getFilmsByGrid(grid));
		}
	}
	
	private void setRegImgSort(String sort,GridModel grid){
		//first name
		if(sort.equals("frstNm")){
			grid.setSortRegFirstName( (grid.getSortRegFirstName()+1)%2 );
			grid.setSortDesc(grid.getSortRegFirstName()==0 ? true : false);
		}
		else{
			grid.setSortRegFirstName(2);
		}
		//last name
		if(sort.equals("lstNm")){
			grid.setSortRegLastName( (grid.getSortRegLastName()+1)%2 );
			grid.setSortDesc(grid.getSortRegLastName()==0 ? true : false);
		}
		else{
			grid.setSortRegLastName(2);
		}
		//username
		if(sort.equals("usrnm")){
			grid.setSortRegUsername( (grid.getSortRegUsername()+1)%2 );
			grid.setSortDesc(grid.getSortRegUsername()==0 ? true : false);
		}
		else{
			grid.setSortRegUsername(2);
		}
	}
	
	private void setDirectorImgSort(String sort,GridModel grid){
		//director name
		if(sort.equals("directorName")){
			grid.setSortDirectorName((grid.getSortDirectorName()+1)%2 );
			grid.setSortDesc(grid.getSortDirectorName()==0 ? true : false);
		}
		else{
			grid.setSortDirectorName(2);
		}
		//director date of birth
		if(sort.equals("dtOfBth")){
			grid.setSortDirectorDateOfBirth( (grid.getSortDirectorDateOfBirth()+1)%2 );	
			grid.setSortDesc(grid.getSortDirectorDateOfBirth()==0 ? true : false);
		}
		else{
			grid.setSortDirectorDateOfBirth(2);			
		}
		
		//director create date
		if(sort.equals("creatDt")){
			grid.setSortDirectorCreateDate( (grid.getSortDirectorCreateDate()+1)%2 );	
			grid.setSortDesc(grid.getSortDirectorCreateDate()==0 ? true : false);
		}
		else{
			grid.setSortDirectorCreateDate(2);			
		}
		
		//director update date
		if(sort.equals("updtDt")){
			grid.setSortDirectorUpdateDate( (grid.getSortDirectorUpdateDate()+1)%2 );	
			grid.setSortDesc(grid.getSortDirectorUpdateDate()==0 ? true : false);
		}
		else{
			grid.setSortDirectorUpdateDate(2);			
		}
		
		//director create user
		if(sort.equals("creatUsrId")){
			grid.setSortDirectorCreateUser( (grid.getSortDirectorCreateUser()+1)%2 );
			grid.setSortDesc(grid.getSortDirectorCreateUser()==0 ? true : false);
		}
		else{
			grid.setSortDirectorCreateUser(2);
		}
		
		//director update user
		if(sort.equals("updtUsrId")){
			grid.setSortDirectorUpdateUser( (grid.getSortDirectorUpdateUser()+1)%2 );
			grid.setSortDesc(grid.getSortDirectorUpdateUser()==0 ? true : false);
		}
		else{
			grid.setSortDirectorUpdateUser(2);
		}
		
	}
	
	private void setFilmImgSort(String sort,GridModel grid){
		//film title
		if(sort.equals("titl")){
			grid.setSortFilmTitle( (grid.getSortFilmTitle()+1)%2 );
			grid.setSortDesc(grid.getSortFilmTitle()==0 ? true : false);
		}
		else{
			grid.setSortFilmTitle(2);
		}
		
		//film number
		if(sort.equals("cnt")){
			grid.setSortFilmCount( (grid.getSortFilmCount()+1)%2 );
			grid.setSortDesc(grid.getSortFilmCount()==0 ? true : false);
		}
		else{
			grid.setSortFilmCount(2);
		}
		
		//film author name
		if(sort.equals("director")){
			grid.setSortFilmAthrName( (grid.getSortFilmAthrName()+1)%2 );
			grid.setSortDesc(grid.getSortFilmAthrName()==0 ? true : false);
		}
		else{
			grid.setSortFilmAthrName(2);
		}
                
                //film genre
		if(sort.equals("genre")){
			grid.setSortFilmGenre((grid.getSortFilmGenre()+1)%2 );
			grid.setSortDesc(grid.getSortFilmGenre()==0 ? true : false);
		}
		else{
			grid.setSortFilmGenre(2);
		}
                
                //film star
		if(sort.equals("star")){
			grid.setSortFilmStar((grid.getSortFilmStar()+1)%2 );
			grid.setSortDesc(grid.getSortFilmStar()==0 ? true : false);
		}
		else{
			grid.setSortFilmStar(2);
		}
		
		//film create date
		if(sort.equals("creatDt")){
			grid.setSortFilmCreateDate( (grid.getSortFilmCreateDate()+1)%2 );
			grid.setSortDesc(grid.getSortFilmCreateDate()==0 ? true : false);
		}
		else{
			grid.setSortFilmCreateDate(2);
		}
		
		//film create date
		if(sort.equals("updtDt")){
			grid.setSortFilmUpdateDate( (grid.getSortFilmUpdateDate()+1)%2 );
			grid.setSortDesc(grid.getSortFilmUpdateDate()==0 ? true : false);
		}
		else{
			grid.setSortFilmUpdateDate(2);
		}
		
		//film create user
		if(sort.equals("creatUsrId")){
			grid.setSortFilmCreateUser( (grid.getSortFilmCreateUser()+1)%2 );
			grid.setSortDesc(grid.getSortFilmCreateUser()==0 ? true : false);
		}
		else{
			grid.setSortFilmCreateUser(2);
		}
		
		//film update user
		if(sort.equals("updtUsrId")){
			grid.setSortFilmUpdateUser( (grid.getSortFilmUpdateUser()+1)%2 );
			grid.setSortDesc(grid.getSortFilmUpdateUser()==0 ? true : false);
		}
		else{
			grid.setSortFilmUpdateUser(2);
		}
		
		//film image
		if(sort.equals("img")){
			grid.setSortFilmImage( (grid.getSortFilmImage()+1)%2 );
			grid.setSortDesc(grid.getSortFilmImage()==0 ? true : false);
		}
		else{
			grid.setSortFilmImage(2);
		}
	}

}
