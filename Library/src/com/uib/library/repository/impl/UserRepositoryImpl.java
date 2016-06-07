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

import com.uib.library.domain.Rent;
import com.uib.library.domain.Role;
import com.uib.library.domain.User;
import com.uib.library.model.GridModel;
import com.uib.library.model.SearchRegRepositoryModel;
import com.uib.library.repository.UserRepositoryApi;

/**
 * @author marija.milicevic
 *
 */
public class UserRepositoryImpl extends BaseRepositoryImpl implements UserRepositoryApi {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsersByRoleName(String roleName){
		Criteria crit = getSession().createCriteria(User.class, "user");
		crit.createAlias("user.libRole", "role");
		crit.add(Restrictions.eq("role.roleNm", roleName));
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsersByUsername(String username){
		Criteria crit = getSession().createCriteria(User.class, "user");
		crit.add(Restrictions.eq("usrnm", username));
		
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsersByStatus(int status) {
		Criteria crit = getSession().createCriteria(User.class, "user");
		crit.add(Restrictions.eq("sts", status));
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getInactiveUsersByGrid(GridModel grid){
		Criteria crit = getSession().createCriteria(User.class, "user");
		crit.add(Restrictions.eq("sts", 0));
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
	
	public int getNumberOfInactiveUsers(){
		Criteria crit = getSession().createCriteria(User.class, "user");
		crit.add(Restrictions.eq("sts", 0));
		return crit.list().size();
	}

	@Override
	public User getUserByUsrnmPswd(String username, String password) {
		Criteria crit = getSession().createCriteria(User.class, "user");
		
		if(username!=null && password!=null){
			if(!username.equals("") && !password.equals("")){
				crit.add(Restrictions.eq("sts", 1));
				crit.add(Restrictions.eq("usrnm", username));
				crit.add(Restrictions.eq("pswd", password));
				return (User)crit.uniqueResult();
			}			
		}
		return null;
	}
	
	@Override
	public User getUserByUsrnmMD5Pswd(String username, String MD5password) {
		Criteria crit = getSession().createCriteria(User.class, "user");
		
		if(username!=null && MD5password!=null){
			if(!username.equals("") && !MD5password.equals("")){
				crit.add(Restrictions.eq("sts", 1));
				crit.add(Restrictions.eq("usrnm", username));
				crit.add(Restrictions.eq("md5Pswd", MD5password));
				return (User)crit.uniqueResult();
			}			
		}
		return null;
	}
	
	@Override
	public User getUserById(int id) {
		Criteria crit = getSession().createCriteria(User.class, "user");		
		if(id!=0){
			crit.add(Restrictions.eq("usrId", id));
			return (User)crit.uniqueResult();			
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserByUsersnameExceptId(String username,int usrId) {
		Criteria crit = getSession().createCriteria(User.class, "user");		
		crit.add(Restrictions.eq("usrnm", username));
		crit.add(Restrictions.ne("usrId", usrId));			
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rent> getRentsByUserId(int usrId) {
		Criteria crit = getSession().createCriteria(Rent.class, "rent");
		crit.add(Restrictions.eq("rent.libUsr.usrId", usrId));
		return crit.list();
		
	}
	
	@Override
	public Role getRoleByName(String roleName) {
		Criteria crit = getSession().createCriteria(Role.class, "role");
		crit.add(Restrictions.eq("roleNm", roleName));
		return (Role)crit.uniqueResult();
	}

	@Override
	public void deleteAllUsers() {
		deleteAll("LibUsr");
	}

	@Override
	public void deleteAllRoles() {
		deleteAll("LibRole");		
	}

	@Override
	public void deleteAllRents() {
		deleteAll("LibRnt");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rent> deleteRentsbyUsrId(int usrId) {
		Criteria crit = getSession().createCriteria(Rent.class, "rent");
		crit.add(Restrictions.eq("rent.libUsr.usrId", usrId));
		List<Rent> list = crit.list();
		for(Rent libRnt:list){
			delete(libRnt);
		}		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public SearchRegRepositoryModel searchRegByFirstName(String frstNm,int res){
		SearchRegRepositoryModel ret = new SearchRegRepositoryModel();
		Criteria crit = getSession().createCriteria(User.class, "user");	
		crit.add(Restrictions.eq("sts", 0));
		crit.addOrder(Order.asc("frstNm"));
		crit.add(Restrictions.like("frstNm", frstNm, MatchMode.ANYWHERE));
		ret.setResults(crit.list().size());
		crit.setMaxResults(res);
		ret.setList(crit.list());
		return ret;
	}
	
	@SuppressWarnings("unchecked")
	public SearchRegRepositoryModel searchRegByLastName(String lstNm,int res){
		SearchRegRepositoryModel ret = new SearchRegRepositoryModel();
		Criteria crit = getSession().createCriteria(User.class, "user");	
		crit.add(Restrictions.eq("sts", 0));
		crit.addOrder(Order.asc("frstNm"));
		crit.add(Restrictions.like("lstNm", lstNm, MatchMode.ANYWHERE));
		ret.setResults(crit.list().size());
		crit.setMaxResults(res);
		ret.setList(crit.list());
		return ret;
	}
	
	@SuppressWarnings("unchecked")
	public SearchRegRepositoryModel searchRegByLastUsername(String usrnm,int res){
		SearchRegRepositoryModel ret = new SearchRegRepositoryModel();
		Criteria crit = getSession().createCriteria(User.class, "user");	
		crit.add(Restrictions.eq("sts", 0));
		crit.addOrder(Order.asc("frstNm"));
		crit.add(Restrictions.like("usrnm", usrnm, MatchMode.ANYWHERE));
		ret.setResults(crit.list().size());
		crit.setMaxResults(res);
		ret.setList(crit.list());
		return ret;
	}


}
