package com.lluqteq.demoShell.service.impl;

import com.lluqteq.demoShell.domain.User;
import com.lluqteq.demoShell.exceptions.DuplicateEmailAddressException;
import com.lluqteq.demoShell.exceptions.IncorrectPasswordException;
import com.lluqteq.demoShell.exceptions.InvalidEmailAddressException;
import com.lluqteq.demoShell.exceptions.NoSuchUserException;
import com.lluqteq.demoShell.exceptions.WeakPasswordException;
import com.lluqteq.demoShell.repository.UserRepository;
import com.lluqteq.demoShell.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	/*
	 * (?=.*[0-9]) a digit must occur at least once (?=.*[a-z]) a lower case letter
	 * must occur at least once (?=.*[A-Z]) an upper case letter must occur at least
	 * once (?=.*[@#$%^&+=]) a special character must occur at least once (?=\\S+$)
	 * no whitespace allowed in the entire string .{8,} at least 8 characters
	 */

	private static final String STRONG_PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}";

	private static final String VALID_EMAIL_PATTERN =  "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";


	private UserRepository repository;

	@Autowired
	public UserServiceImpl(UserRepository repository) {
		this.repository = repository;
	}

	public User findUser(String username, String password) throws NoSuchUserException, IncorrectPasswordException {

		User user = repository.findByEmailAddress(username);

		if (user == null) {
			throw new NoSuchUserException();
		}
		if (!user.getPassword().equals(password)) {
			throw new IncorrectPasswordException();
		}


		return user;
	}

	public void save(User user) throws DuplicateEmailAddressException, WeakPasswordException, InvalidEmailAddressException {
		final String emailAddress = user.getEmailAddress();

		if (repository.findByEmailAddress(emailAddress) != null) {
			throw new DuplicateEmailAddressException();
		}
		if (!isStrongPassword(user.getPassword())) {
			throw new WeakPasswordException();
		}

		if (!isValidEmailAddress(emailAddress)) {
			throw new InvalidEmailAddressException();
		}

		repository.save(user);

	}

	@Override
	public List<User> findAllUsers() {
		return repository.findAll();
	}

	private boolean isValidEmailAddress(String emailAddress) {
		return  emailAddress.matches(VALID_EMAIL_PATTERN);
	}

	private boolean isStrongPassword(String password) {

		return password.matches(STRONG_PASSWORD_PATTERN);
	}

}
