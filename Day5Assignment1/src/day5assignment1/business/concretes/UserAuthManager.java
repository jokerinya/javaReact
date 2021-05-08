package day5assignment1.business.concretes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import day5assignment1.core.abstracts.UserAuthService;
import day5assignment1.entities.concretes.User;

public class UserAuthManager implements UserAuthService{

	@Override
	public boolean checkUserCredentials(User user) {
		return (isEmailValid(user.getEmail()) && 
				isPasswordValid(user.getPassword()) && 
				isFirstLastNameValid(user.getFirstName()) && 
				isFirstLastNameValid(user.getLastName()));
	}
	
	public boolean isEmailValid(String email) {
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		boolean res = matcher.matches();
		if (!res) {
			System.out.println("Email address error, please check your email address.");
		}
		return res;
	}
	
	public boolean isPasswordValid(String password) {
		boolean res = (password != null) && (password.length() >= 6 );
		if (!res) {
			System.out.println("Password should be at least 6 chars, please check your password.");
		}
		return res;
	}
	
	public boolean isFirstLastNameValid(String name) {
		boolean res = (name != null) && (name.length() >= 2 );
		if (!res) {
			System.out.println("First and last name must be at least 2 chars.");
		}
		return res;
	}
	
	

}
