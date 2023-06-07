package com.example.mvcsample.service.implementation;

import com.example.mvcsample.dto.StudentDTO;
import com.example.mvcsample.exception.StudentNotFoundException;
import com.example.mvcsample.model.Student;
import com.example.mvcsample.repository.StudentRepository;
import com.example.mvcsample.service.StudentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Igor Suvorov
 */

@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private ModelMapper modelMapper;
    private StudentRepository studentRepository;

    @Override
    public List<StudentDTO> getAllStudents() {
       List<Student> students = studentRepository.findAll();
       return students.stream().map((student) -> modelMapper.map(student, StudentDTO.class)).
               collect(Collectors.toList());
    }

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student savedStudent = studentRepository.save(modelMapper.map(studentDTO, Student.class));
        return modelMapper.map(savedStudent, StudentDTO.class);
    }

    @Override
    public StudentDTO getStudentById(Long id) {
        Student foundStudent = studentRepository.findById(id).orElseThrow(
                () -> new StudentNotFoundException("student", "id", id)
        );
        return modelMapper.map(foundStudent, StudentDTO.class);
    }

    @Override
    public void updateStudent(StudentDTO studentDTO) {
        Student foundStudent = studentRepository.findById(studentDTO.getId()).orElseThrow(
                () -> new StudentNotFoundException("student", "id", studentDTO.getId())
        );
        foundStudent.setFirstName(studentDTO.getFirstName());
        foundStudent.setLastName(studentDTO.getLastName());
        foundStudent.setEmail(studentDTO.getEmail());
        studentRepository.save(foundStudent);
    }

    @Override
    public void deleteStudent(Long studentId) {
        Student foundStudent = studentRepository.findById(studentId).orElseThrow(
                () -> new StudentNotFoundException("student", "id", studentId)
        );
        studentRepository.deleteById(studentId);
    }
}
