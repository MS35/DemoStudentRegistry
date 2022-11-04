package com.lluqteq.demoShell.controller;

import com.lluqteq.demoShell.domain.Score;
import com.lluqteq.demoShell.domain.Student;
import com.lluqteq.demoShell.service.ScoreService;
import com.lluqteq.demoShell.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Controller("scoreController")
@RequestScope
public class ScoreController {

	private String scoresPage = "studentScores.xhtml";

	private Map<String, BigDecimal> averageScores = new HashMap();

	private Map<String, List<Score>> studentScores = new HashMap();

	private ScoreService scoreService;

	private StudentService studentService;

	private LocalDate scoreDate;

	private String studentId;

	private BigDecimal score;

	@Autowired
	public ScoreController(ScoreService scoreService, StudentService studentService) {
		this.scoreService = scoreService;
		this.studentService = studentService;
	}

	public List<Score> getAll() {
		return scoreService.getAll();
	}

	public List<Score> getScores(Student student) {
		return scoreService.getStudentScores(student);
	}

	public BigDecimal getAverageScore(String studentId) {
		Student student = studentService.findById(studentId).orElse(null);
		BigDecimal averageScore = Objects.nonNull(student) ? getAverageScore(student) : BigDecimal.ZERO;
		return averageScore;
	}

	public BigDecimal getAverageScore(Student student) {
		return scoreService.getAverageScore(student);
	}

	@PostConstruct
	private void initContributionsAndTransactions(){
		List<Student> students = studentService.findAllStudents();
		for (Student student : students){
			String name = student.getName() + " " + student.getSurname();
			averageScores.put(name, getAverageScore(student));
			studentScores.put(name, getScores(student));
		}

	}

	public String addScore() {
		scoreService.save(addNewStudentScore());
		return scoresPage;
	}

	private Score addNewStudentScore() {

		if (!validInputs()) {
			throw new IllegalArgumentException("One or more of the required fields were not added");
		}

		Score score = new Score();
		score.setDate(getScoreDate());
		score.setStudentId(getStudentId());
		score.setScore(getScore());
		return score;
	}

	private boolean validInputs() {
		if (Objects.isNull(getScoreDate()) || Objects.isNull(getStudentId()) || Objects.isNull(getScore())) {
			return false;
		}
		return true;
	}

	public LocalDate getScoreDate() {
		return scoreDate;
	}

	public void setScoreDate(LocalDate scoreDate) {
		this.scoreDate = scoreDate;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}
}
