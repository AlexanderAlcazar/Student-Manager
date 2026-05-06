package edu.smc.service;

import edu.smc.data.Database;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final Database database;

    public AuthService(Database database) {
        this.database = database;
    }

    /**
     * Verify credentials against the student database.
     * Administrator-specific handling has been removed; all logins are
     * validated using the same data store (students file) for now.
     */
    public boolean login(String username, String password) {
        return database.verify(username, password);
    }
}

