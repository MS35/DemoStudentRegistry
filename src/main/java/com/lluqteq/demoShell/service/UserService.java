package com.lluqteq.demoShell.service;

import com.lluqteq.demoShell.domain.User;
import com.lluqteq.demoShell.exceptions.*;

import java.util.List;

public interface UserService {

//Maybe use runtime exceptions and still keep them in the signature (form of validation)

	public User findUser(String username, String password) throws NoSuchUserException, IncorrectPasswordException;


	public void save(User user) throws DuplicateEmailAddressException, WeakPasswordException, InvalidEmailAddressException;

	public List<User> findAllUsers();
}
