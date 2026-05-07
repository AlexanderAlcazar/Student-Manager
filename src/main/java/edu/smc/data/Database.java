package edu.smc.data;

import edu.smc.model.Student;
import edu.smc.repository.StudentRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Performs operations for storing and retrieving student information.
 * This class delegates persistence to Spring Data JPA.
 */
@Component
@Transactional(readOnly = true)
public class Database {
    private final StudentRepository studentRepository;

    public Database(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Adds a new Student to the database and returns the created record.
     *
     * @param firstname Student's first name
     * @param lastname Student's last name
     * @param phoneNumber Student's phone number
     * @param address Student's address
     * @param major Student's major
     * @return the created Student, or null if the student already exists
     */
    @Transactional
    public Student addStudent(
            String firstname,
            String lastname,
            String phoneNumber,
            String address,
            String major
    ) {
        int studentID = generateID();
        if (studentRepository.existsById(studentID)) {
            return null;
        }

        Student student = new Student(
                firstname,
                lastname,
                studentID,
                phoneNumber,
                address,
                major
        );
        return studentRepository.save(student);
    }

    /**
     * Removes a Student from the database.
     *
     * @param studentID ID of the student to remove
     * @return true if the student existed and was removed, false otherwise
     */
    @Transactional
    public boolean removeStudent(int studentID) {
        if (!studentRepository.existsById(studentID)) {
            return false;
        }
        studentRepository.deleteById(studentID);
        return true;
    }

    /**
     * Retrieves a Student from the database.
     *
     * @param studentID ID of the student to retrieve
     * @return the retrieved Student object, null if not found
     */
    public Student getStudent(int studentID) {
        return studentRepository.findById(studentID).orElse(null);
    }

    /**
     * Lists all Students in the database.
     *
     * @return a String representation of all Students
     */
    public String listStudents() {
        return allStudents().stream()
                .map(Student::toString)
                .collect(Collectors.joining());
    }

    /**
     * Returns a snapshot of all students currently stored in the database.
     *
     * @return copy of the student set
     */
    public Set<Student> getStudents() {
        return new HashSet<>(allStudents());
    }

    /**
     * Verifies a Student's login credentials.
     *
     * @param username the Student's username
     * @param password the Student's password
     * @return boolean value indicating the validity of the credentials
     */
    public boolean verify(String username, String password) {
        return studentRepository.existsByUsernameAndPassword(username, password);
    }

    /**
     * Generates a unique ID for a Student.
     *
     * @return the generated ID
     */
    private int generateID() {
        Random random = new Random();
        int randomNumber;
        do {
            randomNumber = random.nextInt(9000000) + 1000000;
        } while (studentRepository.existsById(randomNumber));
        return randomNumber;
    }

    private List<Student> allStudents() {
        return studentRepository.findAll().stream()
                .sorted((left, right) -> Integer.compare(left.getStudentID(), right.getStudentID()))
                .collect(Collectors.toList());
    }
}