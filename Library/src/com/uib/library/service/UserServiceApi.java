package com.uib.library.service;


import com.uib.library.service.*;
import com.uib.library.domain.User;
import com.uib.library.model.GridModel;
import com.uib.library.model.RentModel;
import com.uib.library.model.SearchRegRepositoryModel;
import com.uib.library.model.UserModel;
import java.util.List;

public interface UserServiceApi {
	
	public List<User> getUsersbyRoleName(String roleName);
	public List<User> getUsersByUsername(String username);
	public List<User> getUsersByStatus(int status);
	public List<User> getInactiveUsersByGrid(GridModel grid);
	public int getNumberOfInactiveUsers();
	
	public UserModel getUserModelByUsernameAndPassword(String username,String password);
	public UserModel getUserModelByUsernameAndMD5Password(String username, String MD5password);
	public UserModel getUserModelByUserId(int id);
	
	public List<RentModel> getRentsbyUser(int usrId);
	
	public void inseretUser(UserModel userModel);
	public void updateUser(UserModel userModel);
	
	public boolean isUsernameActive(String username,int usrId);
	
	public SearchRegRepositoryModel searchRegByFirstName(String frstNm,int res);
	public SearchRegRepositoryModel searchRegByLastName(String lstNm,int res);
	public SearchRegRepositoryModel searchRegByLastUsername(String usrnm,int res);

}
