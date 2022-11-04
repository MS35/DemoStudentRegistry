package com.lluqteq.demoShell.service;

import java.math.BigDecimal;
import java.util.List;

import com.lluqteq.demoShell.domain.Score;
import com.lluqteq.demoShell.domain.Student;

public interface ScoreService {

	List<Score> getAll();


	List<Score> getStudentScores(Student student);


	BigDecimal getAverageScore(Student student);


	void save(Score score);
}
