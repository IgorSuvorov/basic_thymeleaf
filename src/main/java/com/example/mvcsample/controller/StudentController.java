package com.example.mvcsample.controller;

import com.example.mvcsample.dto.StudentDTO;
import com.example.mvcsample.model.Student;
import com.example.mvcsample.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String saveStudent(@Valid @ModelAttribute("student") StudentDTO studentDTO,
                              BindingResult bindingResult,
                              Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("student", studentDTO);
            return "addStudent";
        }

        studentService.createStudent(studentDTO);
        return "redirect:/students";
    }

    @GetMapping("/students/{studentId}/edit")
    public String editStudent(@PathVariable("studentId") Long studentId,
                              Model model) {
        StudentDTO studentDTO = studentService.getStudentById(studentId);
        model.addAttribute("student", studentDTO);
        return "editStudent";
    }

    @PostMapping("/students/{studentId}")
    public String updateStudent(@PathVariable("studentId") Long studentId,
                                @Valid @ModelAttribute("student") StudentDTO studentDTO,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("student", studentDTO);
            return "editStudent";
        }
        studentDTO.setId(studentId);
        studentService.updateStudent(studentDTO);
        return "redirect:/students";
    }

    @GetMapping("/students/{studentId}/delete")
    public String deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
        return "redirect:/students";
    }

    @GetMapping("/students/{studentId}/view")
    public String viewStudent(@PathVariable("studentId") Long studentId,
                              Model model) {
        StudentDTO studentDTO = studentService.getStudentById(studentId);
        model.addAttribute("student", studentDTO);
        return "viewStudent";
    }

}
