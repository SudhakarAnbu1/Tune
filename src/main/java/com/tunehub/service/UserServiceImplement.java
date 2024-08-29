package com.tunehub.service;

import java.util.Random;

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

	@Autowired
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
	public boolean generateOTP(String email) {
		Users user=repos.findByEmail(email);
		if(user==null)
		{
			return false;
		}
		else
		{

		int otp=user.getOtp();
		//for generating the random nummbers for the otp
		Random random=new Random();
		int num=random.nextInt(20);

		num=num*1000+num;

		user.setOtp(num);

		repos.save(user);


		MimeMessage mimemessage=javamailsender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(mimemessage,"utf-8");
		try
		{
			helper.setText("Hi "+user.getName()+"," +" use this "+num+" to change Password, Do Not Share the OTP anyone",true);
			helper.setTo(user.getEmail());
			helper.setSubject("OTP for Updating Password");
			helper.setFrom("sudhakar2001dpi@gmail.com");
			javamailsender.send(mimemessage);
		} 
		catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
		}


	}
	@Override
	public boolean updatePassword(String email, String password, int otp) {
		// TODO Auto-generated method stub
		Users user=repos.findByEmail(email);
		if(user!=null)
		{
			if(user.getOtp()==otp)
			{
				String pass=user.getPassword();
				pass=password;
				repos.save(user);


				//after updating the password change some different otp for security
				int changeotp=user.getOtp();
				//for generating the random nummbers for the otp
				Random random=new Random();
				int num=random.nextInt(34);

				num=num*1000+num;

				user.setOtp(num);

				repos.save(user);
			}
			return true;
		}
		else
		{
			return false;
		}

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
