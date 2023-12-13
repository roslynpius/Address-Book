package com.bridgelabz.addressbook;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @desc The AddressBook class represents a collection of contacts.
 *       It provides methods to add a new contact, display all contacts,
 *       edit an existing contact, delete a contact using their name,
 *       and add multiple persons to the address book.
 */
class AddressBookList {
    // List to store contacts
    private List<Contact> contacts;

    /**
     * @desc Constructs a new AddressBook with an empty list of contacts.
     */
    public AddressBookList() {
        contacts = new ArrayList<>();
    }

    /**
     * @desc Adds a new contact to the address book. Also checks for duplicate entry (UC-7)
     *
     * @param contact The contact to be added.
     */
    public void addContact(Contact contact) {
        // Check for duplicate using Java streams
        boolean isDuplicate = contacts.stream().anyMatch(c -> c.equals(contact));

        if (isDuplicate) {
            System.out.println("Duplicate entry! This contact already exists in the address book.");
        } else {
            contacts.add(contact);
            System.out.println("Contact added to the address book.");
        }
    }

    /**
     * @desc Displays all contacts in the address book.
     */
    public void displayAllContacts() {
        System.out.println("Address Book Contacts:");
        for (Contact contact : contacts) {
            contact.displayContact();
            System.out.println("-------------------------");
        }
    }

    /**
     * @desc Edits an existing contact using their first and last names.
     *       Prompts the user to enter new information for the contact.
     *
     * @param firstName The first name of the contact to be edited.
     * @param lastName  The last name of the contact to be edited.
     */
    public void editContact(String firstName, String lastName) {
        for (Contact contact : contacts) {
            if (contact.firstName.equals(firstName) && contact.lastName.equals(lastName)) {
                Scanner scanner = new Scanner(System.in);

                System.out.println("Enter new information for the contact:");
                System.out.print("Address: ");
                contact.address = scanner.nextLine();

                System.out.print("City: ");
                contact.city = scanner.nextLine();

                System.out.print("State: ");
                contact.state = scanner.nextLine();

                System.out.print("ZIP Code: ");
                contact.zip = scanner.nextLine();

                System.out.print("Phone Number: ");
                contact.phoneNumber = scanner.nextLine();

                System.out.print("Email: ");
                contact.email = scanner.nextLine();

                System.out.println("Contact updated successfully.");
                return;
            }
        }

        System.out.println("Contact not found. Unable to edit.");
    }

    /**
     * @desc Deletes a contact using their first and last names.
     *
     * @param firstName The first name of the contact to be deleted.
     * @param lastName  The last name of the contact to be deleted.
     */
    public void deleteContact(String firstName, String lastName) {
        Iterator<Contact> iterator = contacts.iterator();
        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            if (contact.firstName.equals(firstName) && contact.lastName.equals(lastName)) {
                iterator.remove();
                System.out.println("Contact deleted successfully.");
                return;
            }
        }

        System.out.println("Contact not found. Unable to delete.");
    }

    /**
     * @desc Search for a person in a city in this Address Book.
     * @param cityName The city to search for.
     * @return List of persons in the specified city in this Address Book.
     */
    public List<Contact> searchPersonInCity(String cityName) {
        return contacts.stream()
                .filter(contact -> contact.city.equals(cityName))
                .collect(Collectors.toList());
    }

    /**
     * @desc Search for a person in a state in this Address Book.
     * @param stateName The state to search for.
     * @return List of persons in the specified state in this Address Book.
     */
    public List<Contact> searchPersonInState(String stateName) {
        return contacts.stream()
                .filter(contact -> contact.state.equals(stateName))
                .collect(Collectors.toList());
    }
}