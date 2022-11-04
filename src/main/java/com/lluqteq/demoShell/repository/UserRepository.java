package com.lluqteq.demoShell.repository;

import com.lluqteq.demoShell.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findByNameAndPassword(String name, String password);

	public User findByEmailAddress(String emailAddress);


}
