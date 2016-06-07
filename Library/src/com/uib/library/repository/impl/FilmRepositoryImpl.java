/**
 * 
 */
package com.uib.library.repository.impl;

import com.uib.library.repository.impl.*;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.uib.library.domain.Director;
import com.uib.library.domain.Film;
import com.uib.library.domain.Rent;
import com.uib.library.domain.base.RentId;
import com.uib.library.model.GridModel;
import com.uib.library.model.SearchDirectorRepositoryModel;
import com.uib.library.model.SearchFilmRepositoryModel;
import com.uib.library.repository.FilmRepositoryApi;

/**
 * @author marija.milicevic
 *
 */
public class FilmRepositoryImpl extends BaseRepositoryImpl implements FilmRepositoryApi {
		
	@SuppressWarnings("unchecked")
	@Override
	public List<Film> getAvailableFilms() {
		Criteria crit = getSession().createCriteria(Film.class, "film");		
		crit.add(Restrictions.gt("cnt", new Integer(0)));		
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Film> getFilmsByGrid(GridModel grid){
		Criteria crit = getSession().createCriteria(Film.class, "film");	
		if(!grid.isSortDesc()){
			crit.addOrder(Order.asc(grid.getCmpString()));
		}
		else{
			crit.addOrder(Order.desc(grid.getCmpString()));
		}
		String search=grid.getSearchString();
		if(!search.equals("")){
			if(search.contains("Rnt")){
				search=search.substring(3);
			}
			if(search.contains("director")){
				crit.createAlias("film.director","director");				
				crit.add(Restrictions.like("director.directorName", search.substring(search.lastIndexOf('_')+1) , MatchMode.ANYWHERE));
			}			
			else if(search.substring(0, search.lastIndexOf('_')).equals("cnt") ){
				crit.add(Restrictions.eq(search.substring(0, search.lastIndexOf('_')),Integer.parseInt(search.substring(search.lastIndexOf('_')+1))));	
			}
			else{
				crit.add(Restrictions.like(search.substring(0, search.lastIndexOf('_')),search.substring(search.lastIndexOf('_')+1) , MatchMode.ANYWHERE));					
			}
		}
		crit.setFirstResult(grid.getItemsPerPage()*grid.getPageNumber());	
		crit.setMaxResults(grid.getItemsPerPage());		
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Film> getAvailableFilmsFilmsByGrid(GridModel grid){
		Criteria crit = getSession().createCriteria(Film.class, "film");	
		crit.add(Restrictions.gt("cnt", new Integer(0)));
		if(!grid.isSortDesc()){
			crit.addOrder(Order.asc(grid.getCmpString()));
		}
		else{
			crit.addOrder(Order.desc(grid.getCmpString()));
		}
		String search=grid.getSearchString();
		if(!search.equals("")){
			if(search.contains("director")){
				crit.createAlias("film.director","director");				
				crit.add(Restrictions.like("director.directorName", search.substring(search.lastIndexOf('_')+1) , MatchMode.ANYWHERE));
			}			
			else if(search.substring(0, search.lastIndexOf('_')).equals("cnt") ){
				crit.add(Restrictions.eq(search.substring(0, search.lastIndexOf('_')),Integer.parseInt(search.substring(search.lastIndexOf('_')+1))));	
			}
			else{
				crit.add(Restrictions.like(search.substring(0, search.lastIndexOf('_')),search.substring(search.lastIndexOf('_')+1) , MatchMode.ANYWHERE));					
			}
		}
		crit.setFirstResult(grid.getItemsPerPage()*grid.getPageNumber());	
		crit.setMaxResults(grid.getItemsPerPage());		
		return crit.list();
	}
	
	// Get all films by tiltle (exclude film with filmID) if the film is
	// new then it adds it too (create film) hence if the film is updated
	// (update film) id doesn't add it to list - method for checking if film
	//	with given title already exists
	@SuppressWarnings("unchecked")
	@Override
	public List<Film> getFilmsByTitleExceptId(String titl, int filmId, boolean isNew) {
		Criteria crit = getSession().createCriteria(Film.class, "film");

		if (!isNew) {
			if (titl != null) {
				if (filmId != 0 && titl != "") {
					crit.add(Restrictions.eq("titl", titl));
					crit.add(Restrictions.ne("filmID", filmId));
				} else
					return null;
			}
		} else {
			if (titl != null) {
				if (titl != "") {
					crit.add(Restrictions.eq("titl", titl));
				} else
					return null;
			}
		}
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Film> getFilmsByImgExceptId(String img, int filmId, boolean isNew) {
		Criteria crit = getSession().createCriteria(Film.class, "film");

		if (!isNew) {
			if (img != null) {
				if (filmId != 0 && img != "") {
					crit.add(Restrictions.eq("img", img));
					crit.add(Restrictions.ne("filmID", filmId));
				} else
					return null;
			}
		} else {
			if (img != null) {
				if (img != "") {
					crit.add(Restrictions.eq("img", img));
				} else
					return null;
			}
		}
		return crit.list();
	}
	
	@Override
	public Film getFilmById(int filmId) {
		Criteria crit = getSession().createCriteria(Film.class, "film");		
		if(filmId!=0){
			crit.add(Restrictions.eq("filmID", filmId));
			return (Film)crit.uniqueResult();			
		}
		return null;
	}
	
	public int getNumOfFilms(){
		Criteria crit = getSession().createCriteria(Film.class, "film");
		return crit.list().size();
	}
	
	public int getNumOfAvailableFilms(){
		Criteria crit = getSession().createCriteria(Film.class, "film");
		crit.add(Restrictions.gt("cnt", new Integer(0)));
		return crit.list().size();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Director> getDirectorsByNameExceptId(String directorName, int directorId, boolean isNew) {
		Criteria crit = getSession().createCriteria(Director.class, "director");		
		
		if(!isNew){
			if(directorName!=null){
				if(directorId!=0 && directorName!=""){
					crit.add(Restrictions.eq("directorName", directorName));
					crit.add(Restrictions.ne("directorID", directorId));			
				}
				else return null;
			}
		}
		else{
			if(directorName!=null){
				if(directorName!=""){
					crit.add(Restrictions.eq("directorName", directorName));		
				}
				else return null;
			}
		}
		
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Director> getDirectorsByGrid(GridModel grid){
		Criteria crit = getSession().createCriteria(Director.class, "director");	
		if(!grid.isSortDesc()){
			crit.addOrder(Order.asc(grid.getCmpString()));
		}
		else{
			crit.addOrder(Order.desc(grid.getCmpString()));
		}
		String search=grid.getSearchString();
		if(!search.equals("")){
				crit.add(Restrictions.like(search.substring(0, search.lastIndexOf('_')),search.substring(search.lastIndexOf('_')+1) , MatchMode.ANYWHERE));					
		}
		crit.setFirstResult(grid.getItemsPerPage()*grid.getPageNumber());	
		crit.setMaxResults(grid.getItemsPerPage());		
		return crit.list();
	}
	
        @Override
	public int getNumOfDirectors(){
		Criteria crit = getSession().createCriteria(Director.class, "director");
		return crit.list().size();
	}

	@Override
	public Director getDirectorByName(String directorName) {
		Criteria crit = getSession().createCriteria(Director.class, "director");
		
		if(directorName!=null){
			if(!directorName.equals("")){
				crit.add(Restrictions.eq("directorName", directorName));
			}		
			else return null;
		}
		
		return (Director)crit.uniqueResult();
		
	}
	
	@Override
	public Director getDirectorById(int directorId) {
		Criteria crit = getSession().createCriteria(Director.class, "director");		
		if(directorId!=0){
			crit.add(Restrictions.eq("directorID", directorId));
			return (Director)crit.uniqueResult();			
		}
		return null;
	}

	@Override
	public void deleteAllDirectors() {
		deleteAll("Director");
	}

	@Override
	public void deleteAllFilms() {
		deleteAll("Film");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rent> getRentByRntId(RentId rntId) {
		Criteria crit = getSession().createCriteria(Rent.class, "rnt");
		crit.add(Restrictions.eq("id", rntId));
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Director> getSortedDirectors(String sort) {
		Criteria crit = getSession().createCriteria(Director.class, "director");
		crit.addOrder(Order.asc(sort));	
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Film> getSortedFilms(String sort) {
		Criteria crit = getSession().createCriteria(Film.class, "film");		
		if(sort.equals("director")){
			crit.createAlias("film.director","director");			
			crit.addOrder(Order.asc("director.directorName"));
		}
		else if(sort.substring(0, 3).equals("Rnt")){
			if(sort.equals("director")){
				crit.createAlias("film.director","director");			
				crit.addOrder(Order.asc("director.directorName"));
			}
			crit.add(Restrictions.gt("cnt", new Integer(0)));
			crit.addOrder(Order.asc(sort.substring(3)));			
		}
		else {
			crit.addOrder(Order.asc(sort));
		}
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SearchDirectorRepositoryModel searchDirectorByName(String directorNm,int res) {
		SearchDirectorRepositoryModel ret = new SearchDirectorRepositoryModel();
		Criteria crit = getSession().createCriteria(Director.class, "director");
		crit.add(Restrictions.like("directorName", directorNm , MatchMode.ANYWHERE));
		ret.setResults(crit.list().size());
		crit.setMaxResults(res);
		ret.setList(crit.list());
		return ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public SearchFilmRepositoryModel searchFilmByTitle(String filmTitl,boolean rent,int res) {
		SearchFilmRepositoryModel ret = new SearchFilmRepositoryModel();
		Criteria crit = getSession().createCriteria(Film.class, "film");
		crit.addOrder(Order.asc("titl"));
		if(rent){
			crit.add(Restrictions.gt("cnt", new Integer(0)));
		}		
		crit.add(Restrictions.like("titl", filmTitl , MatchMode.ANYWHERE));
		ret.setResults(crit.list().size());
		crit.setMaxResults(res);
		ret.setList(crit.list());
		return ret;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public SearchFilmRepositoryModel searchFilmCnt(String cnt,boolean rent,int res) {
		SearchFilmRepositoryModel ret = new SearchFilmRepositoryModel();
		Criteria crit = getSession().createCriteria(Film.class, "film");
		crit.addOrder(Order.asc("titl"));
		if(rent){
			crit.add(Restrictions.gt("cnt", new Integer(0)));
		}
		int iCnt=0;		
		if(!cnt.equals("")){
			iCnt = Integer.parseInt(cnt);			
			crit.add(Restrictions.eq("cnt", iCnt));
		}
		ret.setResults(crit.list().size());
		crit.setMaxResults(res);
		ret.setList(crit.list());
		return ret;
	}
        
        @SuppressWarnings("unchecked")
	@Override
	public SearchFilmRepositoryModel searchFilmByGenre(String genre,boolean rent,int res) {
		SearchFilmRepositoryModel ret = new SearchFilmRepositoryModel();
		Criteria crit = getSession().createCriteria(Film.class, "film");	
		crit.addOrder(Order.asc("genre"));
		if(rent){
			crit.add(Restrictions.gt("cnt", new Integer(0)));
		}
		crit.add(Restrictions.like("genre", genre , MatchMode.ANYWHERE));
                int test = crit.list().size();
		ret.setResults(crit.list().size());
		crit.setMaxResults(res);
		ret.setList(crit.list());
		return ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public SearchFilmRepositoryModel searchFilmByDirector(String directorNm,boolean rent,int res) {
		SearchFilmRepositoryModel ret = new SearchFilmRepositoryModel();
		Criteria crit = getSession().createCriteria(Film.class, "film");	
		crit.addOrder(Order.asc("titl"));
		if(rent){
			crit.add(Restrictions.gt("cnt", new Integer(0)));
		}
		crit.createAlias("film.director","director");
		crit.add(Restrictions.like("director.directorName", directorNm , MatchMode.ANYWHERE));
		ret.setResults(crit.list().size());
		crit.setMaxResults(res);
		ret.setList(crit.list());
		return ret;
	}

	@Override
	public void insertImgToFilm(String img, int bkId) {
		Film libBk = getFilmById(bkId);
		libBk.setImg(img);
		update(libBk);		
	}

}
