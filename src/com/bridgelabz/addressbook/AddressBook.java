package com.bridgelabz.addressbook;
import java.util.*;
import java.io.File;

/**
 * @desc Class that represents creation of address book
 */
public class AddressBook {

    private static final String ENTITY_CASE_CITY="City";
    private static final String  ENTITY_CASE_STATE="State";
    private static final String ENTITY_CASE_ZIPCODE="ZipCode";
    private static final String CONTACTS_DIRECTORY = "Contacts";


    public static void main(String[] args) {

        // Create Contacts directory if it doesn't exist
        File contactsDirectory = new File(CONTACTS_DIRECTORY);
        if (!contactsDirectory.exists()) {
            boolean created = contactsDirectory.mkdir();
            if (created) {
                System.out.println("Contacts directory created.");
            } else {
                System.out.println("Failed to create Contacts directory.");
                return;
            }
        }

        Scanner scanner = new Scanner(System.in);

        // Create an AddressBookSystem
        AddressBookSystem addressBookSystem = new AddressBookSystem();

        int choice;

        do {
            // Display menu
            System.out.println("1. Add a new Address Book");
            System.out.println("2. View all Address Books");
            System.out.println("3. Work with an Address Book");
            System.out.println("4. Search persons in a city");
            System.out.println("5. Search persons in a state");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Add a new Address Book
                    System.out.print("Enter the name of the new Address Book: ");
                    String newAddressBookName = scanner.next();
                    addressBookSystem.addAddressBook(newAddressBookName);
                    break;

                case 2:
                    // View all Address Books in the system
                    addressBookSystem.displayAllAddressBooks();
                    break;

                case 3:
                    // Work with an Address Book
                    System.out.print("Enter the name of the Address Book to work with: ");
                    String selectedAddressBookName = scanner.next();
                    AddressBookList selectedAddressBook = addressBookSystem.getAddressBook(selectedAddressBookName);

                    if (selectedAddressBook != null) {
                        // Load contacts from file when working with an existing Address Book
                        selectedAddressBook.loadFromFile(selectedAddressBookName);
                        workWithAddressBook(selectedAddressBook, scanner);
                        // Save contacts to file when exiting the Address Book
                        selectedAddressBook.saveToFile(selectedAddressBookName);
                    } else {
                        System.out.println("Address Book not found.");
                    }
                    break;

                case 4:
                    // Search persons in a city
                    System.out.print("Enter the city to search for: ");
                    String city = scanner.next();
                    List<Contact> personsInCity = addressBookSystem.getContactsInCity(city);
                    displaySearchResults(personsInCity);
                    break;

                case 5:
                    // Search persons in a state
                    System.out.print("Enter the state to search for: ");
                    String state = scanner.next();
                    List<Contact> personsInState = addressBookSystem.getContactsInState(state);
                    displaySearchResults(personsInState);
                    break;

                case 6:
                    System.out.println("Exiting Address Book. Returning to the main menu.");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }

        } while (choice != 6);

        scanner.close();
    }

    /**
     * @desc Performs operations on the specified Address Book.
     *
     * @param addressBook The AddressBook to work with.
     * @param scanner     The Scanner object for user input.
     */
    private static void workWithAddressBook(AddressBookList addressBook, Scanner scanner) {
        int choice;
        AddressBookSystem addressBookSystem = new AddressBookSystem();

        do {
            // Display menu
            System.out.println("1. Add a new contact");
            System.out.println("2. View all contacts (SORTED BY NAME)"); //UC 11
            System.out.println("3. Edit an existing contact");
            System.out.println("4. Delete a contact");
            System.out.println("5. View persons by city");
            System.out.println("6. View persons by state");
            System.out.println("7. Get count of contact persons by city");
            System.out.println("8. Get count of contact persons by state");
            System.out.println("9. Sort Contacts By City");
            System.out.println("10. Sort Contacts By State");
            System.out.println("11. Sort Contacts By ZipCode");
            System.out.println("12. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Add multiple persons to the address book
                    System.out.print("Enter the number of persons to add: ");
                    int numberOfPersons = scanner.nextInt();

                    for (int i = 0; i < numberOfPersons; i++) {
                        System.out.println("Enter Contact Information for Person " + (i + 1) + ":");
                        System.out.print("First Name: ");
                        String newFirstName = scanner.next();

                        System.out.print("Last Name: ");
                        String newLastName = scanner.next();

                        System.out.print("Address: ");
                        String newAddress = scanner.next();

                        System.out.print("City: ");
                        String newCity = scanner.next();

                        System.out.print("State: ");
                        String newState = scanner.next();

                        System.out.print("ZIP Code: ");
                        String newZip = scanner.next();

                        System.out.print("Phone Number: ");
                        String newPhoneNumber = scanner.next();

                        System.out.print("Email: ");
                        String newEmail = scanner.next();

                        // Create a new contact
                        Contact newContact = new Contact(newFirstName, newLastName, newAddress, newCity, newState, newZip, newPhoneNumber, newEmail);

                        // Add the new contact to the address book
                        addressBook.addContact(newContact);
                    }
                    break;

                case 2:
                    // View all contacts in the address book
                    //addressBook.displayAllContacts();

                    // Sort contacts alphabetically by name
                    addressBook.sortByName();

                    // Print sorted address book
                    System.out.println("Sorted Address Book:");
                    System.out.println(addressBook);
                    break;

                case 3:
                    // Edit an existing contact
                    System.out.println("Enter the name of the contact to edit:");
                    System.out.print("First Name: ");
                    String editFirstName = scanner.next();

                    System.out.print("Last Name: ");
                    String editLastName = scanner.next();

                    addressBook.editContact(editFirstName, editLastName);
                    break;

                case 4:
                    // Delete a contact
                    System.out.println("Enter the name of the contact to delete:");
                    System.out.print("First Name: ");
                    String deleteFirstName = scanner.next();

                    System.out.print("Last Name: ");
                    String deleteLastName = scanner.next();

                    addressBook.deleteContact(deleteFirstName, deleteLastName);
                    break;

                case 5:
                    // View persons by city
                    System.out.print("Enter the city to view persons for: ");
                    String cityToView = scanner.next();
                    List<Contact> personsByCity = addressBookSystem.viewPersonsByCity(cityToView);
                    displaySearchResults(personsByCity);
                    break;

                case 6:
                    // View persons by state
                    System.out.print("Enter the state to view persons for: ");
                    String stateToView = scanner.next();
                    List<Contact> personsByState = addressBookSystem.viewPersonsByState(stateToView);
                    displaySearchResults(personsByState);
                    break;

                case 7:
                    // Get count of contact persons by city
                    Map<String, Long> countByCity = addressBookSystem.getCountByCity();
                    displayCountResults(countByCity, ENTITY_CASE_CITY);
                    break;

                case 8:
                    // Get count of contact persons by state
                    Map<String, Long> countByState = addressBookSystem.getCountByState();
                    displayCountResults(countByState, ENTITY_CASE_STATE);
                    break;

                case 9:
                    List<Contact> sortedByCity = addressBook.sortByCity();
                    addressBook.printSorted(sortedByCity,ENTITY_CASE_CITY);
                    break;

                case 10:
                    List<Contact> sortedByState = addressBook.sortByState();
                    addressBook.printSorted(sortedByState,ENTITY_CASE_STATE);
                    break;

                case 11:
                    List<Contact> sortedByZipCode = addressBook.sortByZip();
                    addressBook.printSorted(sortedByZipCode,ENTITY_CASE_ZIPCODE);
                    break;

                case 12:
                    System.out.println("Exiting Address Book. Returning to the main menu.");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }

        } while (choice != 12);
    }

    /**
     * @desc Helper method to display search results.
     * @param searchResults The list of contacts to display.
     */
    private static void displaySearchResults(List<Contact> searchResults) {
        System.out.println("Search Results:");
        if (searchResults.isEmpty()) {
            System.out.println("No matching persons found.");
        } else {
            for (Contact contact : searchResults) {
                contact.displayContact();
                System.out.println("-------------------------");
            }
        }
    }

    /**
     * @desc Helper method to display count results.
     * @param countResults The list of contacts to display.
     * @param entityType Either state or city
     */
    private static void displayCountResults(Map<String, Long> countResults, String entityType) {
        System.out.println("Count Results by " + entityType + ":");
        countResults.forEach((key, value) -> System.out.println(key + ": " + value));
        System.out.println("-------------------------");
    }


}
