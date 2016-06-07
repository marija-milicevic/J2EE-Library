/**
 * 
 */
package com.uib.library.repository;

import com.uib.library.repository.*;
import java.util.List;

import com.uib.library.domain.Rent;
import com.uib.library.domain.Role;
import com.uib.library.domain.User;
import com.uib.library.model.GridModel;
import com.uib.library.model.SearchRegRepositoryModel;

/**
 * @author marija.milicevic
 *
 */
public interface UserRepositoryApi extends BaseRepositoryApi {
	
	public List<User> getUsersByRoleName(String roleName);
	public List<User> getUsersByUsername(String username);
	public List<User> getUsersByStatus(int status);
	public List<User> getInactiveUsersByGrid(GridModel grid);
	public int getNumberOfInactiveUsers();
	public List<User> getUserByUsersnameExceptId(String username,int usrId);
	
	public User getUserByUsrnmPswd(String username,String password);
	public User getUserByUsrnmMD5Pswd(String username,String MD5Password);
	public User getUserById(int id);	
	
	public List<Rent> getRentsByUserId(int usrId);
	
	public Role getRoleByName(String roleName);
	
	public void deleteAllUsers();
	public void deleteAllRoles();
	public void deleteAllRents();
	public List<Rent> deleteRentsbyUsrId(int usrId);
	
	public SearchRegRepositoryModel searchRegByFirstName(String frstNm,int res);
	public SearchRegRepositoryModel searchRegByLastName(String lstNm,int res);
	public SearchRegRepositoryModel searchRegByLastUsername(String usrnm,int res);
		
}
