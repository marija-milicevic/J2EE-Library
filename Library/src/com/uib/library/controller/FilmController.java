package com.uib.library.controller;

import com.uib.library.controller.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.uib.library.model.DirectorModel;
import com.uib.library.model.FilmModel;
import com.uib.library.model.FileModel;
import com.uib.library.model.GridModel;
import com.uib.library.service.FilmServiceApi;
import com.uib.library.service.UserServiceApi;
import org.apache.commons.io.IOUtils;

@Controller
@SessionAttributes(value = {"usr","regList","filmList","directorList","grid"})
public class FilmController {
	
	@Autowired
	private FilmServiceApi filmServiceApi;
	@Autowired
	private UserServiceApi userServiceApi;
	@Autowired
	private FilmValidator filmValidator;
	
	@ModelAttribute("filmModel")
	public FilmModel createFilm() {
		return new FilmModel();
	}
	@ModelAttribute("directorModel")
	public DirectorModel createDirector() {
		return new DirectorModel();
	}
	
	//------------ AUTHORS MANAGMENT SECTION ------------
	@PreAuthorize("hasRole('administrator')")
	@RequestMapping("/directorManagmentPage")
	public ModelAndView goToAuthorManagmentPage(){
		return new ModelAndView("directorManagment");
	}
	
	@PreAuthorize("hasRole('administrator')")
	@RequestMapping("/directorManagment")
	public ModelAndView listAllDirectors(@ModelAttribute("grid")GridModel grid){
		ModelAndView modelAndView = new ModelAndView("directorManagment");				
		//+++ Paging and sorting +++
		grid = new GridModel();
		editGrid(filmServiceApi.getNumOfDirectors(), "directorManagment", grid);
		grid.setCmpString("directorName");
		modelAndView.addObject("grid", grid);
		List<DirectorModel> directorList = filmServiceApi.getDirectorsByGrid(grid);
		modelAndView.addObject("directorList", directorList);
		return modelAndView;				
	}
	
	@PreAuthorize("hasRole('administrator')")
	@RequestMapping("/singleDirectorPage")	                 
	public ModelAndView goToSingleDirectorPage(@ModelAttribute("directorModel") DirectorModel directorModel,boolean isNew){
		ModelAndView modelAndView = new ModelAndView("singleDirector");
		modelAndView.addObject("isNew",isNew);
		if(isNew){			
			return modelAndView;
		}
		directorModel=filmServiceApi.getDirectorModelByDirectorId(directorModel.getDirectorID());	
		if(directorModel==null){
			modelAndView.addObject("singleDirectorErr" , "Director doesn't exist , redirected to create page !");
			modelAndView.addObject("isNew", true);
			return modelAndView;
		}
		modelAndView.addObject("snglDirector", directorModel);
		return modelAndView;
	}
	
	@PreAuthorize("hasRole('administrator')")
	@RequestMapping("/singleDirector")	                 
	public ModelAndView changeDirector(@ModelAttribute("directorModel") DirectorModel directorModel,BindingResult result,boolean isNew,@ModelAttribute("grid") GridModel grid){
		ModelAndView modelAndView = new ModelAndView();
		filmValidator.validateDirector(directorModel, result, isNew);
		if(result.hasErrors()){
			modelAndView.addObject("snglDirector", directorModel);
			modelAndView.addObject("isNew", isNew);
			modelAndView.setViewName("singleDirector");
			modelAndView.addObject("singleDirectorErr" , filmValidator.getErrMessage());
			return modelAndView;
		}
		modelAndView.setViewName("directorManagment");
		if(isNew){
			filmServiceApi.insertDirector(directorModel);
			editGrid(grid.getItems()+1, "directorManagment", grid);
		}		
		else{
			filmServiceApi.updateDirector(directorModel);
		}		
		List<DirectorModel> directorList = filmServiceApi.getDirectorsByGrid(grid);
		modelAndView.addObject("directorList", directorList);
		//+++ Paging and sorting +++	
		modelAndView.addObject("grid", grid);
		return modelAndView;
	} 	
	
	@PreAuthorize("hasRole('administrator')")	
	@RequestMapping("/deleteDirector")	
	public ModelAndView deleteDirector(@ModelAttribute("directorModel") DirectorModel directorModel,@ModelAttribute("grid") GridModel grid){
		ModelAndView modelAndView = new ModelAndView("directorManagment");	
		try{
		filmServiceApi.delteDirectorById(directorModel.getDirectorID());
		editGrid(grid.getItems()-1, "directorManagment", grid);
		}catch (IllegalArgumentException e) {
			modelAndView.addObject("deleteDirectorErr" , "Can't delete director , director already deleted !");
		}catch (DataIntegrityViolationException e) {
			modelAndView.addObject("deleteDirectorErr" , "Can't delete director , delete all of his films first !");
		}		
		if(grid.getItems()%5==0  && grid.getPageNumber()>0){
			grid.setPageNumber(grid.getPageNumber()-1);
		}
		List<DirectorModel> directorList = filmServiceApi.getDirectorsByGrid(grid);
		modelAndView.addObject("directorList", directorList);
		//+++ Paging and sorting +++			
		modelAndView.addObject("grid", grid);
		return modelAndView;
	}
	
	//------------ BOOKS MANAGMENT SECTION ------------ 
	@PreAuthorize("hasRole('administrator')")
	@RequestMapping("/filmManagmentPage")
	public ModelAndView goToFilmManagmentPage(){
		return new ModelAndView("filmManagment");
	}
	
	@PreAuthorize("hasRole('administrator')")
	@RequestMapping("/filmManagment")
	public ModelAndView listAllFilms(@ModelAttribute("grid")GridModel grid){
		ModelAndView modelAndView = new ModelAndView("filmManagment");					
		//+++ Paging and sorting +++
		grid = new GridModel();
		editGrid(filmServiceApi.getNumOfFilms(), "filmManagment", grid);
		grid.setCmpString("titl");
		modelAndView.addObject("grid", grid);
		List<FilmModel> filmList = filmServiceApi.getFilmsByGrid(grid);
		modelAndView.addObject("filmList", filmList);
		return modelAndView;
	}
	
	@PreAuthorize("hasRole('administrator')")
	@RequestMapping("/singleFilmPage")	                 
	public ModelAndView goToSingleFilmPage(@ModelAttribute("filmModel") FilmModel filmModel,boolean isNew){
		ModelAndView modelAndView = new ModelAndView("singleFilm");	
		modelAndView.addObject("isNew",isNew);
		List<DirectorModel> list = filmServiceApi.getAllDirectors();
		modelAndView.addObject("directorList", list);
		if(isNew){			
			return modelAndView;
		}
		filmModel=filmServiceApi.getFilmModelByFilmId(filmModel.getFilmID());	
		if(filmModel==null){
			modelAndView.addObject("singleFilmErr" , "Film doesn't exist , redirected to create page !");
			modelAndView.addObject("isNew", true);
			return modelAndView;
		}
		modelAndView.addObject("snglFilm", filmModel);		
		return modelAndView;
	}
	
	@PreAuthorize("hasRole('administrator')")
	@RequestMapping("/singleFilm")	                 
	public ModelAndView changeFilm(String directorName,@ModelAttribute("filmModel") FilmModel filmModel,BindingResult result,boolean isNew,@ModelAttribute("grid") GridModel grid){
		ModelAndView modelAndView = new ModelAndView();
		filmValidator.validateFilm(filmModel, result, isNew);
		if(result.hasErrors()){				
			List<DirectorModel> list = filmServiceApi.getAllDirectors();
			modelAndView.addObject("directorList", list);
			modelAndView.addObject("snglFilm", filmModel);
			modelAndView.addObject("isNew", isNew);
			modelAndView.setViewName("singleFilm");
			modelAndView.addObject("singleFilmErr", filmValidator.getErrMessage());
			return modelAndView;
		}
		modelAndView.setViewName("filmManagment");
                filmModel.setDirecotrName(directorName);
		if(isNew){                       
			filmServiceApi.insertFilm(filmModel);
			editGrid(grid.getItems()+1, "filmManagment", grid);
		}		
		else{
			filmServiceApi.updateFilm(filmModel);
		}			
		List<FilmModel> filmList = filmServiceApi.getFilmsByGrid(grid);		
		modelAndView.addObject("filmList", filmList);
		//+++ Paging and sorting +++	
		modelAndView.addObject("grid", grid);
		return modelAndView;
	} 	
	
	@PreAuthorize("hasRole('administrator')")
	@RequestMapping("/deleteFilm")	
	public ModelAndView deleteFilm(@ModelAttribute("filmModel") FilmModel filmModel,boolean isNew,@ModelAttribute("grid") GridModel grid){
		ModelAndView modelAndView = new ModelAndView("filmManagment");	
		try{
		filmServiceApi.deleteFilmById(filmModel.getFilmID());
		editGrid(grid.getItems()-1, "filmManagment", grid);
		}catch (IllegalArgumentException e) {
		modelAndView.addObject("deleteFilmErr" , "Can't delete film , film already deleted !");
		}catch (DataIntegrityViolationException e) {
		modelAndView.addObject("deleteFilmErr" , "Can't delete film , someone rented it !");
		}	
		if(grid.getItems()%5==0  && grid.getPageNumber()>0){
			grid.setPageNumber(grid.getPageNumber()-1);
		}
		List<FilmModel> filmList = filmServiceApi.getFilmsByGrid(grid);
		modelAndView.addObject("filmList", filmList);
		//+++ Paging and sorting +++			
		modelAndView.addObject("grid", grid);
		return modelAndView;
	}
	
	//------------ BOOKS RENT SECTION ------------ 
	@RequestMapping("/filmRentPage")
	public ModelAndView listAvailableFilms(@ModelAttribute("grid")GridModel grid){
		ModelAndView modelAndView = new ModelAndView("filmRent");				
		//+++ Paging and sorting +++
		grid = new GridModel();
		editGrid(filmServiceApi.getNumOfAvailableFilms(),"filmRent", grid);
		grid.setCmpString("titl");
		modelAndView.addObject("grid", grid);
		List<FilmModel> filmList = filmServiceApi.getAvailableFilmsFilmsByGrid(grid);
		modelAndView.addObject("filmList", filmList);
		return modelAndView;
	}
	
	@RequestMapping("/filmRent")
	public ModelAndView rentFilm(int usrId,int filmId,int allFilms,String filmImg,@ModelAttribute("grid")GridModel grid){
		ModelAndView modelAndView = new ModelAndView("filmRent");		
		if(allFilms>0){
			if(filmServiceApi.rentFilm(usrId, filmId)==0){
				modelAndView.addObject("rentInfo", "Film rented");
				modelAndView.addObject("rentImg" , filmImg);
				if(filmServiceApi.getFilmModelByFilmId(filmId).getCnt()==0){
					editGrid(grid.getItems()-1, "filmRent", grid);
				}
			}
			else if(filmServiceApi.rentFilm(usrId, filmId)==1){
				modelAndView.addObject("rentInfo", "Can't rent same film twice !");
			}			
			else if(filmServiceApi.rentFilm(usrId, filmId)==2){
				modelAndView.addObject("rentInfo", "All copies gone , refresh page !");
			}
		}
		else{
			modelAndView.addObject("rentInfo", "Can't rent any more films !");
		}
		if(grid.getItems()%5==0  && grid.getPageNumber()>0){
			grid.setPageNumber(grid.getPageNumber()-1);
		}
		List<FilmModel> filmList = filmServiceApi.getAvailableFilmsFilmsByGrid(grid);
		modelAndView.addObject("filmList", filmList);
		modelAndView.addObject("usr", userServiceApi.getUserModelByUserId(usrId));
		return modelAndView;
	}
	
	//------------ BOOKS RETURN SECTION ------------
	@RequestMapping("/filmReturn")
	public ModelAndView returnFilm(int usrId){
		ModelAndView modelAndView = new ModelAndView("user");
		filmServiceApi.deleteRentsByUserId(usrId);
		modelAndView.addObject("usr",userServiceApi.getUserModelByUserId(usrId));
		return modelAndView;
	}			
	
	//------------ UPLOAD FILE SECTION ------------
	@RequestMapping("/uploadFile")
	public ModelAndView uploadFile(FileModel file,HttpServletRequest req){
		ModelAndView modelAndView = new ModelAndView("singleFilm");	
		FilmModel filmModel = new FilmModel();
		try{					
			modelAndView.addObject("directorList",filmServiceApi.getAllDirectors());
			modelAndView.addObject("isNew", true);
			//If the film is updated
			if(!req.getParameter("filmID").equals("")){
				if(Integer.parseInt(req.getParameter("filmID"))!=0){
					filmModel = filmServiceApi.getFilmModelByFilmId(Integer.parseInt(req.getParameter("filmID")));					
					modelAndView.addObject("isNew", false);
				}
			}
			//Check if the file is image type	
			String fileName = file.getFile().getOriginalFilename();			
			if(fileName.length()!=0){				
				String ext = fileName.substring(fileName.lastIndexOf("."));
				if(ext.equals(".gif") || ext.equals(".jpg") || ext.equals(".jpeg") || ext.equals(".png")){
					filmModel.setImg("imgFilms/"+fileName);
					String parent = req.getSession().getServletContext().getRealPath("")+ "\\" + "imgFilms";									
					//File f = new File(parent,fileName);
					//file.getFile().transferTo(f);
					//*** Added to save file to project , not in tmp folder ***
                                                parent=parent.substring(0,parent.indexOf("Library"))+"Library\\docroot\\imgFilms";
                                               
						File f1 = new File(parent,fileName);
                                                file.getFile().transferTo(f1);
                                                
                                                //wait for image to save on disk - to be able to show on the page
                                                Thread.sleep(2000);
                                                while(!f1.exists()){
                                                    Thread.sleep(200);
                                                    if(f1.exists())
                                                        break;
                                                    else
                                                        continue;
                                                }
						/*InputStream in = new FileInputStream(f); 
						OutputStream out = new FileOutputStream(f1); 

						byte[] buf = new byte[4096]; 
						int len = in.read(buf); 
						while ((len = in.read(buf)) > 0) { 
							out.write(buf, 0, len); 
						}
                                               
						in.close(); 
						out.close();*/
					//**********************************************************
					modelAndView.addObject("uploadInfo","File uploaded");
				}
				else{
					modelAndView.addObject("uploadInfo","Type not supported(.gif .jpg .jpeg .png allowed)");
					filmModel.setImg(filmModel.getImg());
				}
			}
			else{
				modelAndView.addObject("uploadInfo","File not uploaded(no file selected)");
				filmModel.setImg(filmModel.getImg());
			}
			modelAndView.addObject("snglFilm",filmModel);							
		}catch (Exception e) {
			e.printStackTrace();
			modelAndView.setViewName("error");
		}
		return modelAndView;
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
	
	private void editGrid(int items,String retPage,GridModel grid){		
		grid.setItems(items);	
		grid.setPages(((int)Math.ceil((double)grid.getItems()/(double)grid.getItemsPerPage())-1));
		grid.setRetPage(retPage);
	}

}
