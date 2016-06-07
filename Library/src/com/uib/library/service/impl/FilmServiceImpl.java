package com.uib.library.service.impl;

import com.uib.library.service.impl.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.uib.library.domain.Director;
import com.uib.library.domain.Film;
import com.uib.library.domain.Rent;
import com.uib.library.domain.User;
import com.uib.library.domain.base.RentId;
import com.uib.library.model.DirectorModel;
import com.uib.library.model.FilmModel;
import com.uib.library.model.GridModel;
import com.uib.library.model.SearchDirectorRepositoryModel;
import com.uib.library.model.SearchDirectorServiceModel;
import com.uib.library.model.SearchFilmRepositoryModel;
import com.uib.library.model.SearchFilmServiceModel;
import com.uib.library.repository.FilmRepositoryApi;
import com.uib.library.repository.UserRepositoryApi;
import com.uib.library.service.FilmServiceApi;

@Transactional
@TransactionConfiguration(transactionManager="transactionManager")
public class FilmServiceImpl implements FilmServiceApi {
	
	private FilmRepositoryApi filmRepositoryApi;
	private UserRepositoryApi userRepositoryApi;

	@SuppressWarnings("unchecked")
	public List<FilmModel> getAllFilms() {
		List<Film> list = (List<Film>)filmRepositoryApi.listAll(Film.class);			
		ArrayList<FilmModel> retlist = new ArrayList<FilmModel>();
		for(Film film:list){
			retlist.add(createFilmModel(film));
		}
		return retlist;
	}
	
	public List<FilmModel> getFilmsByGrid(GridModel grid){
		List<Film> list = (List<Film>)filmRepositoryApi.getFilmsByGrid(grid);			
		ArrayList<FilmModel> retlist = new ArrayList<FilmModel>();
		for(Film film:list){
			retlist.add(createFilmModel(film));
		}
		return retlist;
	}
	
	public FilmModel getFilmModelByFilmId(int filmId) {
		Film film = filmRepositoryApi.getFilmById(filmId);
		if(film==null){return null;}
		return createFilmModel(film);
	}

	public List<FilmModel> getAvailableFilms() {
		List<Film> list = filmRepositoryApi.getAvailableFilms();
		ArrayList<FilmModel> retlist = new ArrayList<FilmModel>();
		for(Film film:list){
			retlist.add(createFilmModel(film));
		}
		return retlist;
	}
	
	public List<FilmModel> getAvailableFilmsFilmsByGrid(GridModel grid){
		List<Film> list = (List<Film>)filmRepositoryApi.getAvailableFilmsFilmsByGrid(grid);			
		ArrayList<FilmModel> retlist = new ArrayList<FilmModel>();
		for(Film film:list){
			retlist.add(createFilmModel(film));
		}
		return retlist;
	}
	
	public int getNumOfFilms(){
		return filmRepositoryApi.getNumOfFilms();
	}
	
	public int getNumOfAvailableFilms(){
		return filmRepositoryApi.getNumOfAvailableFilms();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DirectorModel> getAllDirectors() {
		List<Director> list = (List<Director>) filmRepositoryApi.listAll(Director.class);
		ArrayList<DirectorModel> retlist = new ArrayList<DirectorModel>();
		for(Director director:list){
			retlist.add(createDirectorModel(director));
		}
		return retlist;
	}
	
	public List<DirectorModel> getDirectorsByGrid(GridModel grid){
		List<Director> list = (List<Director>)filmRepositoryApi.getDirectorsByGrid(grid);			
		ArrayList<DirectorModel> retlist = new ArrayList<DirectorModel>();
		for(Director director:list){
			retlist.add(createDirectorModel(director));
		}
		return retlist;
	}
	
	public int getNumOfDirectors(){
		return filmRepositoryApi.getNumOfDirectors();
	}
	
	@Override
	public DirectorModel getDirectorModelByDirectorId(int directorId) {
		Director director = filmRepositoryApi.getDirectorById(directorId);	
		if(director==null){return null;}
		return createDirectorModel(director);
	}
	

	public void insertFilm(FilmModel filmModel) {
		Film film = new Film();
		film.setTitl(filmModel.getTitl());
                film.setGenre(filmModel.getGenre());
                film.setStar(filmModel.getStar());
		film.setImg(filmModel.getImg());
		film.setCnt(filmModel.getCnt());
		film.setDirector(filmRepositoryApi.getDirectorByName(filmModel.getDirectorName()));
		film.setCreatUsrId(filmModel.getUpdtUsrId());
		film.setCreatDt(Calendar.getInstance().getTime());
		filmRepositoryApi.save(film);
	}

	public void updateFilm(FilmModel filmModel) {
		Film film = new Film();
		film.setFilmID(filmModel.getFilmID());
		film.setTitl(filmModel.getTitl());
                film.setGenre(filmModel.getGenre());
                film.setStar(filmModel.getStar());
		film.setImg(filmModel.getImg());
		film.setCnt(filmModel.getCnt());
		film.setDirector(filmRepositoryApi.getDirectorByName(filmModel.getDirectorName()));
		film.setCreatUsrId(filmModel.getCreatUsrId());
		film.setCreatDt(filmModel.getCreatDt());
		film.setUpdtUsrId(filmModel.getUpdtUsrId());
		film.setUpdtDt(Calendar.getInstance().getTime());	
		filmRepositoryApi.saveOrUpdate(film);
	}
	
	@Override
	public boolean isFilmActive(String filmName, int filmId, boolean isNew) {
		List<Film> list = filmRepositoryApi.getFilmsByTitleExceptId(filmName, filmId, isNew);
		if(list==null || list.isEmpty()){
			return false;
		}
		else{
			return true;
		}
	}
	
	@Override
	public boolean isExistingImg(String img, int filmId, boolean isNew) {
		List<Film> list = filmRepositoryApi.getFilmsByImgExceptId(img, filmId, isNew);
		if(list==null || list.isEmpty()){
			return false;
		}
		else{
			return true;
		}
	}
	
	@Override
	public void deleteFilmById(int filmId) {
		filmRepositoryApi.delete(filmRepositoryApi.getFilmById(filmId));
	}
	
	@Override
	public void insertDirector(DirectorModel directorModel) {
		Director director = new Director();
		director.setDirectorName(directorModel.getName());
		director.setDtOfBth(directorModel.getDtOfBth());
		director.setCreatDt(Calendar.getInstance().getTime());
		director.setCreatUsrId(directorModel.getUpdtUsrId());
		filmRepositoryApi.save(director);
	}

	@Override
	public void updateDirector(DirectorModel directorModel) {
		Director director = new Director();
		director.setDirectorID(directorModel.getDirectorID());
		director.setDirectorName(directorModel.getName());
		director.setDtOfBth(directorModel.getDtOfBth());
		director.setCreatDt(directorModel.getCreatDt());
		director.setCreatUsrId(directorModel.getCreatUsrId());
		director.setUpdtDt(Calendar.getInstance().getTime());
		director.setUpdtUsrId(directorModel.getUpdtUsrId());
		filmRepositoryApi.saveOrUpdate(director);
	}
	
	@Override
	public boolean isDirectorActive(String directorName, int directorId,boolean isNew) {
		List<Director> list = filmRepositoryApi.getDirectorsByNameExceptId(directorName, directorId, isNew);
		if(list==null || list.isEmpty()){
			return false;
		}
		else{
			return true;
		}
	}
	
	@Override
	public void delteDirectorById(int directorId) {
		filmRepositoryApi.delete(filmRepositoryApi.getDirectorById(directorId));		
	}
	
	@Override
	public int rentFilm(int usrId, int filmId) {
		Film film = filmRepositoryApi.getFilmById(filmId);
		User libUsr = userRepositoryApi.getUserById(usrId);		
		RentId rentId = new RentId(filmId,usrId);
		if(film.getCnt()==0){
			return 2;
		}
		if(filmRepositoryApi.getRentByRntId(rentId).isEmpty()){
			Rent libRnt = new Rent();
			libRnt.setFilm(film);
			libRnt.setLibUsr(libUsr);
			libRnt.setDtOfRnt(Calendar.getInstance().getTime());
			libRnt.setId(rentId);
			libUsr.setNbrOfAllowFilms(libUsr.getNbrOfAllowFilms()-1);
			film.setCnt(film.getCnt()-1);
			filmRepositoryApi.save(libRnt);
			filmRepositoryApi.saveOrUpdate(film);
			userRepositoryApi.saveOrUpdate(libUsr);
			return 0;
		}		
		return 1;
	}
	
	@Override
	public void deleteRentsByUserId(int usrId) {	
		List<Rent> list = userRepositoryApi.deleteRentsbyUsrId(usrId);
		//update users allowed films
		User libUsr = userRepositoryApi.getUserById(usrId);
		libUsr.setNbrOfAllowFilms(libUsr.getNbrOfAllowFilms()+list.size());
		userRepositoryApi.saveOrUpdate(libUsr);		
		//for each film update count
		for(Rent libRnt:list){
			Film film = filmRepositoryApi.getFilmById(libRnt.getId().getFilmId());
			film.setCnt(film.getCnt()+1);
			filmRepositoryApi.saveOrUpdate(film);
		}		
	}
	

	@Override
	public List<DirectorModel> getSortedDirectors(String sort) {
		List<Director> list = filmRepositoryApi.getSortedDirectors(sort);
		ArrayList<DirectorModel> retlist = new ArrayList<DirectorModel>();
		for(Director director:list){
			retlist.add(createDirectorModel(director));
		}
		return retlist;
	}

	@Override
	public List<FilmModel> getSortedFilms(String sort) {
		List<Film> list = filmRepositoryApi.getSortedFilms(sort);			
		ArrayList<FilmModel> retlist = new ArrayList<FilmModel>();
		for(Film libBk:list){
			retlist.add(createFilmModel(libBk));
		}
		return retlist;
	}
	
	@Override
	public SearchDirectorServiceModel searchDirectorsByName(String directorNm,int res) {
		SearchDirectorServiceModel ret = new SearchDirectorServiceModel();
		SearchDirectorRepositoryModel rep = filmRepositoryApi.searchDirectorByName(directorNm,res);
		ArrayList<DirectorModel> retlist = new ArrayList<DirectorModel>();
		for(Director director:rep.getList()){
			retlist.add(createDirectorModel(director));
		}
		ret.setList(retlist);
		ret.setResults(rep.getResults());
		return ret;
	}
	
	@Override
	public SearchFilmServiceModel searchFilmByTitle(String filmTitl,boolean rent,int res) {
		SearchFilmServiceModel ret = new SearchFilmServiceModel();
		SearchFilmRepositoryModel rep = filmRepositoryApi.searchFilmByTitle(filmTitl,rent,res);
		ArrayList<FilmModel> retlist = new ArrayList<FilmModel>();
		for(Film film:rep.getList()){
			retlist.add(createFilmModel(film));
		}
		ret.setList(retlist);
		ret.setResults(rep.getResults());
		return ret;
	}
	
	@Override
	public SearchFilmServiceModel searchFilmCnt(String cnt,boolean rent,int res) {
		SearchFilmServiceModel ret = new SearchFilmServiceModel();
		SearchFilmRepositoryModel rep = filmRepositoryApi.searchFilmCnt(cnt,rent,res);
		ArrayList<FilmModel> retlist = new ArrayList<FilmModel>();
		for(Film film:rep.getList()){
			retlist.add(createFilmModel(film));
		}
		ret.setList(retlist);
		ret.setResults(rep.getResults());
		return ret;
	}
        
        @Override
	public SearchFilmServiceModel searchFilmByGenre(String genre,boolean rent,int res) {
		SearchFilmServiceModel ret = new SearchFilmServiceModel();
		SearchFilmRepositoryModel rep = filmRepositoryApi.searchFilmByGenre(genre,rent,res);
		ArrayList<FilmModel> retlist = new ArrayList<FilmModel>();
		for(Film film:rep.getList()){
			retlist.add(createFilmModel(film));
		}
		ret.setList(retlist);
		ret.setResults(rep.getResults());
		return ret;
	}	
	
	@Override
	public SearchFilmServiceModel searchFilmByDirector(String directorNm,boolean rent,int res) {
		SearchFilmServiceModel ret = new SearchFilmServiceModel();
		SearchFilmRepositoryModel rep = filmRepositoryApi.searchFilmByDirector(directorNm,rent,res);
		ArrayList<FilmModel> retlist = new ArrayList<FilmModel>();
		for(Film film:rep.getList()){
			retlist.add(createFilmModel(film));
		}
		ret.setList(retlist);
		ret.setResults(rep.getResults());
		return ret;
	}	
	
	@Override
	public void insertImgToFilm(String img,int filmId) {
		filmRepositoryApi.insertImgToFilm(img,filmId);
		
	}

	//GETTERS AND SETTERS
	
	public void setFilmRepositoryApi(FilmRepositoryApi filmRepositoryApi) {
		this.filmRepositoryApi = filmRepositoryApi;
	}

	public FilmRepositoryApi getFilmRepositoryApi() {
		return filmRepositoryApi;
	}
	
	public UserRepositoryApi getUserRepositoryApi() {
		return userRepositoryApi;
	}

	public void setUserRepositoryApi(UserRepositoryApi userRepositoryApi) {
		this.userRepositoryApi = userRepositoryApi;
	}
	
	//UTILITES METHODS
	
	private FilmModel createFilmModel(Film film){
		FilmModel filmModel = new FilmModel();
		filmModel.setFilmID(film.getFilmID());
		filmModel.setCnt(film.getCnt());
		filmModel.setCreatDt(film.getCreatDt());
		filmModel.setCreatUsrId(film.getCreatUsrId());
		filmModel.setCreatUsr(userRepositoryApi.getUserById(film.getCreatUsrId()).getUsrnm());
		if(film.getDirector()!=null){
			filmModel.setDirector(film.getDirector());
			filmModel.setDirecotrName(film.getDirector().getDirectorName());
		}			
		filmModel.setTitl(film.getTitl());	
		filmModel.setImg(film.getImg());
                if(film.getGenre()!=null){
                    filmModel.setGenre(film.getGenre());
                }
                if(film.getStar()!=null){
                    filmModel.setStar(film.getStar());
                }             
		if(film.getUpdtUsrId()!=null){
			filmModel.setUpdtDt(film.getUpdtDt());
			filmModel.setUpdtUsrId(film.getUpdtUsrId());
			filmModel.setUpdtUsr(userRepositoryApi.getUserById(film.getUpdtUsrId()).getUsrnm());				
		}
		return filmModel;
	}
	
	private DirectorModel createDirectorModel(Director director){
		DirectorModel directorModel = new DirectorModel();
		directorModel.setDirectorID(director.getDirectorID());
		directorModel.setName(director.getDirectorName());
		directorModel.setCreatDt(director.getCreatDt());
		directorModel.setCreatUsrId(director.getCreatUsrId());
		directorModel.setCreatUsr(userRepositoryApi.getUserById(director.getCreatUsrId()).getUsrnm());
		directorModel.setDtOfBth(director.getDtOfBth());
		if(director.getUpdtUsrId()!=null){
			directorModel.setUpdtDt(director.getUpdtDt());
			directorModel.setUpdtUsrId(director.getUpdtUsrId());	
			directorModel.setUpdtUsr(userRepositoryApi.getUserById(director.getUpdtUsrId()).getUsrnm());
		}		
		return directorModel;
	}

}
