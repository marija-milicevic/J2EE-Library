package com.uib.library.repository;

import com.uib.library.repository.*;
import java.util.List;

import com.uib.library.domain.Director;
import com.uib.library.domain.Film;
import com.uib.library.domain.Rent;
import com.uib.library.domain.base.RentId;
import com.uib.library.model.GridModel;
import com.uib.library.model.SearchDirectorRepositoryModel;
import com.uib.library.model.SearchFilmRepositoryModel;

public interface FilmRepositoryApi extends BaseRepositoryApi{
	
	public List<Film> getAvailableFilms();	
	public List<Film> getFilmsByGrid(GridModel grid);
	public List<Film> getAvailableFilmsFilmsByGrid(GridModel grid);
	public List<Film> getFilmsByTitleExceptId(String titl,int filmId,boolean isNew);
	public List<Film> getFilmsByImgExceptId(String img,int filmId,boolean isNew);
	public Film getFilmById(int filmId);
	public int getNumOfFilms();
	public int getNumOfAvailableFilms();
	
	public List<Director> getDirectorsByNameExceptId(String directorName, int directorId, boolean isNew);
	public List<Director> getDirectorsByGrid(GridModel grid);
	public int getNumOfDirectors();
	public Director getDirectorByName(String directorName);	
	public Director getDirectorById(int directorId);
	
	public void deleteAllDirectors();
	public void deleteAllFilms();
	
	public List<Rent> getRentByRntId(RentId rntId);
	
	public List<Film> getSortedFilms(String sort);
	public List<Director> getSortedDirectors(String sort);
	
	public SearchDirectorRepositoryModel searchDirectorByName(String directorNm,int res);
	public SearchFilmRepositoryModel searchFilmByTitle(String filmTitl,boolean rent,int res);
	public SearchFilmRepositoryModel searchFilmCnt(String cnt,boolean rent,int res);
        public SearchFilmRepositoryModel searchFilmByGenre(String genre,boolean rent,int res);
	public SearchFilmRepositoryModel searchFilmByDirector(String directorNm,boolean rent,int res);
	
	public void insertImgToFilm(String img,int filmId);

}
