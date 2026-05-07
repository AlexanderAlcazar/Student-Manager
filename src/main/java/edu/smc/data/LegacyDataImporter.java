package edu.smc.data;

import edu.smc.model.Student;
import edu.smc.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class LegacyDataImporter implements CommandLineRunner {
    private static final Path DATA_FILE = Paths.get("data.txt");

    private final StudentRepository studentRepository;

    public LegacyDataImporter(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (studentRepository.count() > 0 || !Files.exists(DATA_FILE)) {
            return;
        }

        importLegacyData();
    }

    private void importLegacyData() throws IOException {
        try (var lines = Files.lines(DATA_FILE)) {
            lines.map(String::trim)
                    .filter(line -> !line.isEmpty())
                    .map(this::parseLegacyStudent)
                    .filter(student -> !studentRepository.existsById(student.getStudentID()))
                    .forEach(studentRepository::save);
        }
    }

    private Student parseLegacyStudent(String line) {
        String[] data = line.split(",", -1);
        if (data.length < 8) {
            throw new IllegalArgumentException("Invalid legacy student record: " + line);
        }

        return new Student(
                data[0],
                data[1],
                Integer.parseInt(data[2]),
                data[3],
                data[4],
                data[5],
                data[6],
                data[7]
        );
    }
}
