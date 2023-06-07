package com.example.mvcsample.controller;

import com.example.mvcsample.dto.StudentDTO;
import com.example.mvcsample.model.Student;
import com.example.mvcsample.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Igor Suvorov
 */

@AllArgsConstructor
@Controller
public class StudentController {

    private StudentService studentService;

    @GetMapping("/students")
    public String getAllStudents(Model model) {
        List<StudentDTO> studentDTOS = studentService.getAllStudents();
        model.addAttribute("students", studentDTOS);
        return "students";
    }

    @GetMapping("/new")
    public String newStudent(Model model) {
        StudentDTO savedStudent = new StudentDTO();
        model.addAttribute("student", savedStudent);
        return "addStudent";
    }

    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") StudentDTO studentDTO) {
        studentService.createStudent(studentDTO);
        return "redirect:/students";
    }

}
