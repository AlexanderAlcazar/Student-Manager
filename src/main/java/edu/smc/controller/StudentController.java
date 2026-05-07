package edu.smc.controller;

import edu.smc.dto.StudentCreateRequest;
import edu.smc.dto.StudentResponse;
import edu.smc.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@Validated
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    //reference listStudent function in the StudentService class
    @GetMapping
    public List<StudentResponse> listStudents() {
        return studentService.listStudents();
    }
    //reference getStudent function in the StudentService class
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudent(@PathVariable int id) {
        return studentService.getStudent(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    //reference addStudent function in the StudentService class and StudentCreateRequest class in the dto package
    @PostMapping
    public ResponseEntity<StudentResponse> addStudent(@Valid @RequestBody StudentCreateRequest request) {
        StudentResponse created = studentService.addStudent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    //reference removeStudent function in the StudentService class
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeStudent(@PathVariable int id) {
        boolean removed = studentService.removeStudent(id);
        if (removed) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

