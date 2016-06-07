package com.uib.library.service;

import com.uib.library.service.*;
import java.util.List;

import com.uib.library.model.DirectorModel;
import com.uib.library.model.FilmModel;
import com.uib.library.model.GridModel;
import com.uib.library.model.SearchDirectorServiceModel;
import com.uib.library.model.SearchFilmServiceModel;

public interface FilmServiceApi {
	
	public List<FilmModel> getAllFilms();
	public List<FilmModel> getFilmsByGrid(GridModel grid);
	public List<FilmModel> getAvailableFilms();
	public List<FilmModel> getAvailableFilmsFilmsByGrid(GridModel grid);
	public int getNumOfFilms();
	public int getNumOfAvailableFilms();
	public FilmModel getFilmModelByFilmId(int filmId);
	public void insertFilm(FilmModel filmModel);
	public void updateFilm(FilmModel FilmModel);
	public boolean isFilmActive(String filmName,int filmId,boolean isNew);
	public boolean isExistingImg(String img,int filmId,boolean isNew);
	public void deleteFilmById(int filmId);
	
	public List<DirectorModel> getAllDirectors();
	public List<DirectorModel> getDirectorsByGrid(GridModel grid);
	public int getNumOfDirectors();
	public DirectorModel getDirectorModelByDirectorId(int directorId);
	public void insertDirector(DirectorModel directorModel);
	public void updateDirector(DirectorModel directorModel);
	public boolean isDirectorActive(String directorName,int directorId,boolean isNew);	
	public void delteDirectorById(int directorId);
	
	public int rentFilm(int usrId, int filmId);
	public void deleteRentsByUserId(int usrId);
	
	public List<FilmModel> getSortedFilms(String sort);
	public List<DirectorModel> getSortedDirectors(String sort);
	
	public SearchDirectorServiceModel searchDirectorsByName(String directorNm,int res);
	public SearchFilmServiceModel searchFilmByTitle(String filmTitl,boolean rent,int res);
	public SearchFilmServiceModel searchFilmCnt(String cnt,boolean rent,int res);
        public SearchFilmServiceModel searchFilmByGenre(String genre,boolean rent,int res);
	public SearchFilmServiceModel searchFilmByDirector(String filmNm,boolean rent,int res);
	
	public void insertImgToFilm(String img,int filmId);

}
