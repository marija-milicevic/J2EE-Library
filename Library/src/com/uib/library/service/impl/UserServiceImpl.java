package com.uib.library.service.impl;


import com.uib.library.service.impl.*;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


import com.uib.library.domain.Rent;
import com.uib.library.domain.User;
import com.uib.library.model.GridModel;
import com.uib.library.model.RentModel;
import com.uib.library.model.SearchRegRepositoryModel;
import com.uib.library.model.UserModel;
import com.uib.library.repository.UserRepositoryApi;
import com.uib.library.service.UserServiceApi;

@Transactional
@TransactionConfiguration(transactionManager="transactionManager")
public class UserServiceImpl implements UserServiceApi {
	
	private UserRepositoryApi userRepositoryApi;	
	
	public List<User> getUsersbyRoleName(String roleName){
		return userRepositoryApi.getUsersByRoleName(roleName);
	}
	
	@Override
	public List<User> getUsersByUsername(String username){
		return userRepositoryApi.getUsersByUsername(username);
	}
	
	@Override
	public List<User> getUsersByStatus(int status) {
		return userRepositoryApi.getUsersByStatus(status);
	}
	
	public List<User> getInactiveUsersByGrid(GridModel grid){
		return userRepositoryApi.getInactiveUsersByGrid(grid);
	}
	
	public int getNumberOfInactiveUsers(){
		return userRepositoryApi.getNumberOfInactiveUsers();
	}
	
	@Override
	public UserModel getUserModelByUsernameAndPassword(String username, String password) {
		User libUsr = userRepositoryApi.getUserByUsrnmPswd(username, password);
		if(libUsr==null){
			return null;
		}
		return createUserModel(libUsr);
	}
	
	@Override
	public UserModel getUserModelByUsernameAndMD5Password(String username, String MD5password) {
		User libUsr = userRepositoryApi.getUserByUsrnmMD5Pswd(username, MD5password);
		if(libUsr==null){
			return null;
		}
		return createUserModel(libUsr);
	}
	

	@Override
	public UserModel getUserModelByUserId(int id) {
		User libUsr = userRepositoryApi.getUserById(id);
		if(libUsr==null){
			return null;
		}
		return createUserModel(libUsr);
	}
	
	public List<RentModel> getRentsbyUser(int usrId){
		List<RentModel> retList = new ArrayList<RentModel>();
		List<Rent> list = userRepositoryApi.getRentsByUserId(usrId);
		for(Rent libRnt:list){
			RentModel rentModel = new RentModel();
			rentModel.setTitl(libRnt.getFilm().getTitl());
			rentModel.setDirector(libRnt.getFilm().getDirector().getDirectorName());
			rentModel.setDateOfRent(libRnt.getDtOfRnt());
			retList.add(rentModel);
		}
		return retList;
	}
	
	@Override
	public void inseretUser(UserModel userModel) {
		User libUsr = new User();
		libUsr.setFrstNm(userModel.getFirstName());
		libUsr.setLstNm(userModel.getLastName());
		libUsr.setUsrnm(userModel.getUsername());
		libUsr.setPswd(userModel.getPassword());	
		libUsr.setMd5Pswd(MD5(userModel.getPassword()));
		libUsr.setEmail(userModel.getEmail());
		if(userModel.isAdmin()){
			libUsr.setLibRole(userRepositoryApi.getRoleByName("administrator"));
		}
		else{
			libUsr.setLibRole(userRepositoryApi.getRoleByName("client"));
		}
		libUsr.setSts(0);
		userRepositoryApi.save(libUsr);
	}
	
	@Override
	public void updateUser(UserModel userModel) {
		User libUsr = new User();
		libUsr.setUsrId(userModel.getId());
		libUsr.setFrstNm(userModel.getFirstName());
		libUsr.setLstNm(userModel.getLastName());
		libUsr.setUsrnm(userModel.getUsername());
		libUsr.setPswd(userModel.getPassword());
		libUsr.setMd5Pswd(MD5(userModel.getPassword()));
		libUsr.setEmail(userModel.getEmail());		
		libUsr.setNbrOfAllowFilms(userModel.getAllowedFilms());
		if(userModel.isAdmin()){
			libUsr.setLibRole(userRepositoryApi.getRoleByName("administrator"));
		}
		else{
			libUsr.setLibRole(userRepositoryApi.getRoleByName("client"));
		}
		String message="Welcome to the Library\n\n";
		if(userModel.getStatus().equals("yes")){
			libUsr.setSts(1);
			userRepositoryApi.saveOrUpdate(libUsr);	
			message+="You have successfully been registered\n\n";
			
			message+="***" + userModel.getFirstName() + " " +userModel.getLastName() + "***\n";
			message+="Username :" + userModel.getUsername() + "\n";
			message+="Password :" + userModel.getPassword() + "\n";
			message+="Allowed Films :" + userModel.getAllowedFilms()+ "\n";
			
			message+="\nGo to Library page :\n";
			message+="http://localhost:8080/Library/";
		}
		else if(userModel.getStatus().equals("no")){
			libUsr.setSts(0);
			userRepositoryApi.delete(libUsr);
			message+="Your registration has been denied\n";
		}			
		
	}
	

	@Override
	public boolean isUsernameActive(String username, int usrId) {
		List<User> list = userRepositoryApi.getUserByUsersnameExceptId(username, usrId);
		for(User libUsr:list){
			if(libUsr.getSts()==1){
				return true;
			}
		}
		return false;
	}
	
	public SearchRegRepositoryModel searchRegByFirstName(String frstNm,int res){
		return userRepositoryApi.searchRegByFirstName(frstNm,res);
	}
	
	public SearchRegRepositoryModel searchRegByLastName(String lstNm,int res){
		return userRepositoryApi.searchRegByLastName(lstNm,res);
	}
	
	public SearchRegRepositoryModel searchRegByLastUsername(String usrnm,int res){
		return userRepositoryApi.searchRegByLastUsername(usrnm,res);
	}
	
	//GETTERS AND SETTERS

	public void setUserRepositoryApi(UserRepositoryApi userRepositoryApi) {
		this.userRepositoryApi = userRepositoryApi;
	}

	public UserRepositoryApi getUserRepositoryApi() {
		return userRepositoryApi;
	}
	
	//UTILITES METHODS
	
	private String convertToHex(byte[] data) { 
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) { 
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do { 
                if ((0 <= halfbyte) && (halfbyte <= 9)) 
                    buf.append((char) ('0' + halfbyte));
                else 
                    buf.append((char) ('a' + (halfbyte - 10)));
                halfbyte = data[i] & 0x0F;
            } while(two_halfs++ < 1);
        } 
        return buf.toString();
    } 

	
	public String MD5(String text) {
		try{
        MessageDigest md;
        md = MessageDigest.getInstance("MD5");
        byte[] md5hash = new byte[32];
        md.update(text.getBytes("iso-8859-1"), 0, text.length());
        md5hash = md.digest();		
        return convertToHex(md5hash);
		}catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		return null;
    } 
	
	private UserModel createUserModel(User libUsr){
		UserModel userModel = new UserModel();
		userModel.setId(libUsr.getUsrId());
		userModel.setFirstName(libUsr.getFrstNm());
		userModel.setLastName(libUsr.getLstNm());
		userModel.setPassword(libUsr.getPswd());
		userModel.setEmail(libUsr.getEmail());
		if(libUsr.getNbrOfAllowFilms()!=null){
			userModel.setAllowedFilms(libUsr.getNbrOfAllowFilms());
		}
		else{
			userModel.setAllowedFilms(0);
		}
		userModel.setUsername(libUsr.getUsrnm());
		userModel.setAdmin(libUsr.getLibRole().getRoleNm().equals("administrator")? true : false);
		return userModel;
	}

	
}
