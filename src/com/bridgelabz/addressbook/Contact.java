package com.bridgelabz.addressbook;

import java.util.Objects;

/**
 * @desc The Contact class represents a person's contact information.
 *       It includes fields for the first name, last name, address,
 *       city, state, ZIP code, phone number, and email.
 *       This class provides a constructor to initialize a new contact
 *       and a method to display the contact information.
 */
public class Contact {
    // Fields representing contact information
    String firstName;
    String lastName;
    String address;
    String city;
    String state;
    String zip;
    String phoneNumber;
    String email;

    /**
     * @desc Constructs a new Contact with the specified information.
     * @param firstName   The first name of the contact.
     * @param lastName    The last name of the contact.
     * @param address     The address of the contact.
     * @param city        The city of the contact.
     * @param state       The state of the contact.
     * @param zip         The ZIP code of the contact.
     * @param phoneNumber The phone number of the contact.
     * @param email       The email address of the contact.
     */
    public Contact(String firstName, String lastName, String address,
                   String city, String state, String zip, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    /**
     * @desc Gets the city of the contact.
     *
     * @return The city of the contact.
     */
    public String getCity() {
        return city;
    }

    /**
     * @desc Gets the state of the contact.
     *
     * @return The state of the contact.
     */
    public String getState() {
        return state;
    }

    /**
     * @desc Displays the contact information on the console.
     */
    public void displayContact() {
        System.out.println("Contact Information:");
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Address: " + address);
        System.out.println("City: " + city);
        System.out.println("State: " + state);
        System.out.println("ZIP Code: " + zip);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Email: " + email);
    }

    /**
     * @desc Overrides the equals method to compare Contact objects based on their firstName and lastName fields for equality.
     *
     * @param obj The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Contact contact = (Contact) obj;
        return Objects.equals(firstName, contact.firstName) && Objects.equals(lastName, contact.lastName);
    }


    /**
     * @desc Overrides the hashCode method to generate a hash code based on the firstName and lastName fields.
     * @return The hash code of the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}




