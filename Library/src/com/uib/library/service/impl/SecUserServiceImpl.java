package com.uib.library.service.impl;

import com.uib.library.service.impl.*;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.uib.library.domain.User;
import com.uib.library.repository.UserRepositoryApi;
import com.uib.library.service.SecUserServiceApi;
import com.uib.library.userDetails.SecUser;

@Transactional
@TransactionConfiguration(transactionManager="transactionManager")
public class SecUserServiceImpl implements SecUserServiceApi {
	
	private UserRepositoryApi userRepositoryApi;

	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException, DataAccessException{
		//Try to find user in database
		List<User> listUsers = userRepositoryApi.getUsersByUsername(userName);
		if(listUsers.isEmpty()){
			throw new DataAccessResourceFailureException("user not found in database");
		}				
		//See if the user is active and then set his username,password
		for(User libUsr:listUsers){
			User usr = userRepositoryApi.getUserByUsrnmMD5Pswd(libUsr.getUsrnm(), libUsr.getMd5Pswd());
			if(usr!=null){
				/* If we dont use our own impl of UserDetails 
				 * ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				authorities.add(new GrantedAuthorityImpl(libUsr.getLibRole().getRoleNm()));
				*/
				SecUser user = new SecUser(usr);
				//If we dont use our own impl of UserDetails
				//User user = new User(usr.getUsrnm(), usr.getPswd(), true, true, true, true, authorities);			
				return user;
			}
		}
		throw new DataAccessResourceFailureException("user is inactive");
	}

	//GETTERS AND SETTERS
	
	public void setUserRepositoryApi(UserRepositoryApi userRepositoryApi) {
		this.userRepositoryApi = userRepositoryApi;
	}

	public UserRepositoryApi getUserRepositoryApi() {
		return userRepositoryApi;
	}

}
