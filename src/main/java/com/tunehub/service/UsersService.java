package com.tunehub.service;

import com.tunehub.entity.Users;

public interface UsersService 
{
	public String addUsers(Users user);
	public boolean emailExists(String email);
	public boolean validate(String email,String password);
	public String getRole(String email);
	public Users getUser(String email);
	public void updateuser(Users user);
	public boolean updatePassword(String email,String password,int otp);
	public boolean generateOTP(String email);
	public String deleteCustomer(String email);
	public boolean checkLoginUser(String email);
	
}
