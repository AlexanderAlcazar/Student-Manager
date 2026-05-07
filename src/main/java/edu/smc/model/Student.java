package edu.smc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * Represents a Student, which is a specific type of User.
 * Implements the Person interface for personal information.
 */
@Entity
@Table(name = "students")
public class Student extends User implements Person {
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Id
    @Column(nullable = false, updatable = false)
    private int studentID;

    @Column(nullable = false)
    private String major;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String address;
    private static final String DEFAULT_PASSWORD = "student";

    /**
     * JPA constructor.
     */
    protected Student() {
    }

    /**
     * Constructs a new Student with the specified details.
     *
     *  Used only when student is added manually via application.
     *
     * @param firstName The first name of the student.
     * @param lastName The last name of the student.
     * @param studentID The student ID.
     * @param phoneNumber The phone number of the student.
     * @param address The address of the student.
     * @param major The major of the student.
     */
    public Student(String firstName, String lastName, int studentID, String phoneNumber, String address, String major) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentID = studentID;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.major = major;
        setUsername(generateStudentEmail(studentID));
        setPassword(DEFAULT_PASSWORD);
    }
    /**
     * Constructs a new Student with the specified details.
     *
     * Used only when data is being loaded from a file.
     *
     * @param firstName The first name of the student.
     * @param lastName The last name of the student.
     * @param studentID The student ID.
     * @param phoneNumber The phone number of the student.
     * @param address The address of the student.
     * @param major The major of the student.
     * @param username The major of the student.
     * @param password The major of the student.
     */
    public Student(String firstName, String lastName, int studentID, String phoneNumber, String address, String major, String username, String password) {
        this(firstName, lastName, studentID, phoneNumber, address, major);
        setUsername(username);
        setPassword(password);
    }

    /**
     * Generates an email address for the student based on their student ID.
     *
     * @param studentID The student ID.
     * @return The generated email address.
     */
    private String generateStudentEmail(int studentID) {
        return studentID + "@smc.edu";
    }

    /**
     * Gets the student ID.
     *
     * @return The student ID.
     */
    public int getStudentID() {
        return studentID;
    }


    /**
     * Retrieves First name of the object.
     * @return a String representing first name.
     */
    @Override
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets a new First name for the object.
     * @param firstName a String representing first name.
     */
    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Retrieves Last name of the object.
     * @return a String representing last name.
     */
    @Override
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets a new Last name for the object.
     * @param lastName a String representing last name.
     */
    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Retrieves Phone Number of the object.
     * @return a String representing phone number.
     */
    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets a new Phone Number for the object.
     * @param phoneNumber a String representing phone number.
     */
    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Retrieves Address of the object.
     * @return a String representing address.
     */
    @Override
    public String getAddress() {
        return address;
    }

    /**
     * Sets a new Address for the object.
     * @param address a String representing address.
     */
    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the major of the student.
     *
     * @return The major of the student.
     */
    public String getMajor() {
        return major;
    }

    /**
     * Sets the major of the student.
     *
     * @param major The new major of the student.
     */
    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return   "[ "+ firstName + ", " + lastName + ", " + studentID +
                ", " + phoneNumber +", " + address + ", "+ major + " ]" + "#";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;
        return studentID == student.studentID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentID);
    }
}
