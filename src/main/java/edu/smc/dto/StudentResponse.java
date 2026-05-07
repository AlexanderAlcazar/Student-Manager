package edu.smc.dto;

import edu.smc.model.Student;

public class StudentResponse {
    private final String firstName;
    private final String lastName;
    private final int studentID;
    private final String phoneNumber;
    private final String address;
    private final String major;

    public StudentResponse(String firstName, String lastName, int studentID, String phoneNumber, String address, String major) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentID = studentID;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.major = major;
    }

    public static StudentResponse fromStudent(Student student) {
        return new StudentResponse(
                student.getFirstName(),
                student.getLastName(),
                student.getStudentID(),
                student.getPhoneNumber(),
                student.getAddress(),
                student.getMajor()
        );
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getMajor() {
        return major;
    }
}

