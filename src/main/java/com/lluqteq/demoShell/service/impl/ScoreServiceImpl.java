package com.lluqteq.demoShell.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.lluqteq.demoShell.domain.Score;
import com.lluqteq.demoShell.domain.Student;
import com.lluqteq.demoShell.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lluqteq.demoShell.repository.ScoreRepository;

@Service
public class ScoreServiceImpl implements ScoreService {

	private ScoreRepository repository;

	@Autowired
	public ScoreServiceImpl(ScoreRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Score> getAll() {
		return repository.findAll();
	}

	@Override
	public List<Score> getStudentScores(Student student) {
		return repository.findByStudentId(student.getId());
	}

	@Override
	public BigDecimal getAverageScore(Student student) {
		List<Score> studentScores = getStudentScores(student);
		double averageScore = studentScores.stream()
				.mapToDouble(score -> score.getScore().doubleValue())
				.average().orElse(0.0);
		return BigDecimal.valueOf(averageScore);
	}

	@Override
	public void save(Score score) {
		repository.save(score);
	}

}
