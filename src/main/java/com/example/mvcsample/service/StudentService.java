package com.example.mvcsample.service;

import com.example.mvcsample.dto.StudentDTO;
import com.example.mvcsample.model.Student;

import java.util.List;

/**
 * @author Igor Suvorov
 */

public interface StudentService {
    List<StudentDTO> getAllStudents();
    StudentDTO createStudent(StudentDTO studentDTO);
}
