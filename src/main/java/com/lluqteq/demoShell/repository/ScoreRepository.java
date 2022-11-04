package com.lluqteq.demoShell.repository;

import java.math.BigDecimal;
import java.util.List;

import com.lluqteq.demoShell.domain.Score;
import com.lluqteq.demoShell.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {

	List<Score> findByStudentId(String studentId);

}
