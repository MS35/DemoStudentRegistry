package com.lluqteq.demoShell.repository;

import com.lluqteq.demoShell.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {

}
