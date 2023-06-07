package com.example.mvcsample.repository;

import com.example.mvcsample.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Igor Suvorov
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
