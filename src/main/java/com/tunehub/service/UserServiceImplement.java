package com.tunehub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.tunehub.entity.Users;
import com.tunehub.repository.UsersRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
@Service
public class UserServiceImplement implements UsersService
{
	@Autowired
	UsersRepository repos;
	
	JavaMailSender javamailsender;
	
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
	public void generateOTP(String email) {
		Users user=repos.findByEmail(email);
//		MimeMessage mimemessage=javamailsender.createMimeMessage();
//		MimeMessageHelper helper=new MimeMessageHelper(mimemessage,"utf-8");
//		try
//		{
//			helper.setText(body,true);
//			helper.setTo(sentTo);
//			helper.setSubject(subject);
//			helper.setFrom("sudhakar2001dpi@gmail.com");
//			javamailsender.send(mimemessage);
//		} 
//		catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		

	}
	@Override
	public String deleteCustomer(String email) {
		Users user=repos.findByEmail(email);
		if(user.getRole().equals("customer"))
		{
			repos.delete(user);
			return "Customer is deleted Sucessfully";
		}
		else
		{
			return "This is a admin user";
		}
	}
	@Override
	public boolean checkLoginUser(String email)
	{
	;
		if(repos.findByEmail(email)==null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

}
