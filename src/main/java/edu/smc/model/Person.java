// Defines package that this interface belongs to
package edu.smc.model;

/**
 * Defines the Person interface which outlines methods for getting and setting personal information.
 */
public interface Person {
    /**
     * Returns the first name of a person as a string.
     *
     * @return The first name of the person.
     */
    String getFirstName();

    /**
     * Sets the first name of a person.
     *
     * @param firstName The first name to set.
     */
    void setFirstName(String firstName);

    /**
     * Returns the last name of a person as a string.
     *
     * @return The last name of the person.
     */
    String getLastName();

    /**
     * Sets the last name of a person.
     *
     * @param lastName The last name to set.
     */
    void setLastName(String lastName);

    /**
     * Returns the phone number of a person as a string.
     *
     * @return The phone number of the person.
     */
    String getPhoneNumber();

    /**
     * Sets the phone number of a person.
     *
     * @param phoneNumber The phone number to set.
     */
    void setPhoneNumber(String phoneNumber);

    /**
     * Returns the address of a person as a string.
     *
     * @return The address of the person.
     */
    String getAddress();

    /**
     * Sets the address of a person.
     *
     * @param address The address to set.
     */
    void setAddress(String address);
}
