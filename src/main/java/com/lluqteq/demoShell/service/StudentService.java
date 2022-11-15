package com.lluqteq.demoShell.service;

import com.lluqteq.demoShell.domain.Score;
import com.lluqteq.demoShell.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

//Maybe use runtime exceptions and still keep them in the signature (form of validation)

	Optional<Student> findById(String id);

	List<Student> findAllStudents();

	void save(Student student);

	void save(String name, String surname);

	String generateStudentId(String name, String surname);

	Score getCurrentScore(Student student);

}
