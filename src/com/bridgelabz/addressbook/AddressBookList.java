package com.bridgelabz.addressbook;

import java.util.*;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.List;
import java.io.*;

/**
 * @desc The AddressBook class represents a collection of contacts.
 *       It provides methods to add a new contact, display all contacts,
 *       edit an existing contact, delete a contact using their name,
 *       and add multiple persons to the address book.
 */
public class AddressBookList {
    // List to store contacts
    private List<Contact> contacts;
    private static final String FILE_EXTENSION = ".txt";
    private static final String CONTACTS_DIRECTORY = "Contacts";

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

    /**
     * @desc Get the count of contact persons by city in this Address Book.
     *
     * @return Map containing the count of contact persons by city.
     */
    public Map<String, Long> getCountByCity() {
        return contacts.stream()
                .collect(Collectors.groupingBy(Contact::getCity, Collectors.counting()));
    }

    /**
     * @desc Get the count of contact persons by state in this Address Book.
     *
     * @return Map containing the count of contact persons by state.
     */
    public Map<String, Long> getCountByState() {
        return contacts.stream()
                .collect(Collectors.groupingBy(Contact::getState, Collectors.counting()));
    }

    /**
     * @desc Sorts all contacts in the address book alphabetically by person's name.
     */
    public void sortByName() {
        contacts.sort(Comparator.naturalOrder());
    }

    /**
     * @desc Overrides the toString method to finally print person entries in the console.
     * @return A formatted string representing the address book.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Contact contact : contacts) {
            result.append(contact.toString()).append("\n");
        }
        return result.toString();
    }

    /**
     * @desc Sorts the contacts in the address book by city.
     * @return List of contacts sorted by city.
     */
    public List<Contact> sortByCity() {
        return contacts.stream()
                .sorted(Comparator.comparing(Contact::getCity))
                .collect(Collectors.toList());
    }

    /**
     * @desc Sorts the contacts in the address book by state.
     * @return List of contacts sorted by state.
     */
    public List<Contact> sortByState() {
        return contacts.stream()
                .sorted(Comparator.comparing(Contact::getState))
                .collect(Collectors.toList());
    }

    /**
     * @desc Sorts the contacts in the address book by ZIP code.
     * @return List of contacts sorted by ZIP code.
     */
    public List<Contact> sortByZip() {
        return contacts.stream()
                .sorted(Comparator.comparing(Contact::getZip))
                .collect(Collectors.toList());
    }

    /**
     * @desc Printing contents in the desired order
     * @param sortedByEntity Contacts sorted in a particular order
     * @param entity Entity by which user wanted to sort contacts
     */
    public void printSorted(List<Contact> sortedByEntity,String entity) {
        System.out.println("Contacts Sorted by "+entity+":");
        for (Contact contact : sortedByEntity) {
            System.out.println(contact);
            System.out.println("-------------------------");
        }
    }

    /**
     * @desc Saves the contacts to a file in CSV format.
     * @param addressBookName The name of the address book.
     */
    public void saveToFile(String addressBookName) {
        File file = new File(CONTACTS_DIRECTORY + File.separator + addressBookName + FILE_EXTENSION);

        try (PrintWriter writer = new PrintWriter(file)) {
            for (Contact contact : contacts) {
                writer.println(contact.toCsvString()); // Assuming you have a toCsvString() method in your Contact class
            }
            System.out.println("Contacts saved successfully to file.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to save contacts to file.");
        }
    }


    /**
     * @desc Loads contacts from a file in CSV format.
     * @param addressBookName The name of the address book.
     */
    public void loadFromFile(String addressBookName) {
        File file = new File(CONTACTS_DIRECTORY + File.separator + addressBookName + FILE_EXTENSION);

        if (file.exists() && file.isFile()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Contact contact = Contact.fromCsvString(line); // Assuming you have a fromCsvString() method in your Contact class
                    if (!contacts.contains(contact)) {
                        contacts.add(contact);
                    }
                }
                System.out.println("Contacts loaded successfully from file.");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Failed to load contacts from file.");
            }
        } else {
            System.out.println("File does not exist. Creating a new one.");
            // Create an empty ArrayList<Contact>
            contacts = new ArrayList<>();
        }
    }



}