package com.lluqteq.demoShell.service.impl;

import com.lluqteq.demoShell.domain.Score;
import com.lluqteq.demoShell.domain.Student;
import com.lluqteq.demoShell.repository.ScoreRepository;
import com.lluqteq.demoShell.service.ScoreService;
import com.lluqteq.demoShell.service.StudentService;
import com.lluqteq.demoShell.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

	/*
	 * (?=.*[0-9]) a digit must occur at least once (?=.*[a-z]) a lower case letter
	 * must occur at least once (?=.*[A-Z]) an upper case letter must occur at least
	 * once (?=.*[@#$%^&+=]) a special character must occur at least once (?=\\S+$)
	 * no whitespace allowed in the entire string .{8,} at least 8 characters
	 */

	private StudentRepository repository;
	private ScoreService scoreService;

	@Autowired
	public StudentServiceImpl(StudentRepository repository, ScoreService scoreService) {
		this.repository = repository;
		this.scoreService = scoreService;
	}

	public List<Student> findAllStudents() {
		return repository.findAll();
	}

	public Optional<Student> findById(String id) {
		return repository.findById(id);
	}

	public void save(final Student student) {
		repository.save(student);
	}

	public void save(String name, String surname) {
		Student student = new Student();
		student.setName(name);
		student.setSurname(surname);
		student.setId(generateStudentId(name, surname));
		save(student);
	}

	public String generateStudentId(String name, String surname) {
		StringBuilder builder = new StringBuilder();
		builder.append(name.charAt(0));
		builder.append(LocalDate.now().getYear());
		builder.append(findAllStudents().size());
		builder.append(surname.charAt(0));
		return builder.toString();
	}

	public Score getCurrentScore(Student student){
		List<Score> studentScores = scoreService.getStudentScores(student);
		Score currentScore =  new Score();
		if(studentScores.size() > 0){
			currentScore = studentScores.get(studentScores.size() -1);
		}
		return currentScore;
	}

}
