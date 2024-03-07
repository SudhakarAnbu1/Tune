package com.tunehub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tunehub.entity.Users;
import com.tunehub.repository.UsersRepository;
@Service
public class UserServiceImplement implements UsersService
{
	@Autowired
	UsersRepository repos;
	@Override
	public String addUsers(Users user) {
		repos.save(user);
		return "User is added sucessfully.....";
	}
	@Override
	public boolean emailExists(String email) {
		if(repos.findByEmail(email)==null)
		{
		return false;
		}
		else
		{
			return true;
		}
	}
	@Override
	public boolean validate(String email, String password) {
		Users user=repos.findByEmail(email);
		String db_pass=user.getPassword();
		if(password.equals(db_pass))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	@Override
	public String getRole(String email) {
		Users user=repos.findByEmail(email);
		return user.getRole();
	}
	@Override
	public Users getUser(String email) {
		// TODO Auto-generated method stub
		return repos.findByEmail(email);
	}
	@Override
	public void updateuser(Users user) {
		// TODO Auto-generated method stub
		repos.save(user);
		
	}
	@Override
	public void updatePassword(String email, String newPassword) {
		Users user=repos.findByEmail(email);
		user.setPassword(newPassword);
		repos.save(user);
		
		
	}
	@Override
	public String deleteCustomer(String email) {
		Users user=repos.findByEmail(email);
		repos.delete(user);
		return "Customer is deleted Sucessfully";
	}

}
