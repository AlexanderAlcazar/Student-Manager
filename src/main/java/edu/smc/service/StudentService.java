package edu.smc.service;

import edu.smc.model.Student;
import edu.smc.data.Database;
import edu.smc.dto.StudentCreateRequest;
import edu.smc.dto.StudentResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final Database database;

    public StudentService(Database database) {
        this.database = database;
    }
    //reference getStudents function in the Database class and fromStudent function in the StudentResponse class
    public List<StudentResponse> listStudents() {
        return database.getStudents().stream()
                .sorted((left, right) -> Integer.compare(left.getStudentID(), right.getStudentID()))
                .map(StudentResponse::fromStudent)
                .collect(Collectors.toList());
    }
    // reference getStudent function in the Database class and fromStudent function in the StudentResponse class
    public Optional<StudentResponse> getStudent(int studentId) {
        Student student = database.getStudent(studentId);
        if (student == null) {
            return Optional.empty();
        }
        return Optional.of(StudentResponse.fromStudent(student));
    }

    public StudentResponse addStudent(StudentCreateRequest request) {
        Student student = database.addStudent(
                request.getFirstName(),
                request.getLastName(),
                request.getPhoneNumber(),
                request.getAddress(),
                request.getMajor()
        );

        if (student == null) {
            throw new IllegalStateException("Student already exists");
        }

        return StudentResponse.fromStudent(student);
    }

    public boolean removeStudent(int studentId) {
        return database.removeStudent(studentId);
    }
}
