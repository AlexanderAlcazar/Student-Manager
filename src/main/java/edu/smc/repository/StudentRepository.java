package edu.smc.repository;

import edu.smc.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    boolean existsByUsernameAndPassword(String username, String password);
}

