package edu.smc.service;

import edu.smc.base.Student;
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

    public List<StudentResponse> listStudents() {
        return database.getStudents().stream()
                .sorted((left, right) -> Integer.compare(left.getStudentID(), right.getStudentID()))
                .map(StudentResponse::fromStudent)
                .collect(Collectors.toList());
    }

    public Optional<StudentResponse> getStudent(int studentId) {
        Student student = database.getStudent(studentId);
        if (student == null) {
            return Optional.empty();
        }
        return Optional.of(StudentResponse.fromStudent(student));
    }

    public StudentResponse addStudent(StudentCreateRequest request) {
        Student student = database.addStudentRecord(
                request.getFirstName(),
                request.getLastName(),
                request.getPhoneNumber(),
                request.getAddress(),
                request.getMajor()
        );

        if (student == null) {
            throw new IllegalStateException("Student already exists");
        }

        database.saveData();
        return StudentResponse.fromStudent(student);
    }

    public boolean removeStudent(int studentId) {
        boolean removed = database.removeStudent(studentId);
        if (removed) {
            database.saveData();
        }
        return removed;
    }
}

