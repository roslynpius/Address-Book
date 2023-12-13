package com.bridgelabz.addressbook;
import java.util.*;

/**
 * @desc Class that represents creation of address book
 */
public class AddressBook {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create an AddressBookSystem
        AddressBookSystem addressBookSystem = new AddressBookSystem();

        int choice;

        do {
            // Display menu
            System.out.println("1. Add a new Address Book");
            System.out.println("2. View all Address Books");
            System.out.println("3. Work with an Address Book");
            System.out.println("4. Exit");
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
                        workWithAddressBook(selectedAddressBook, scanner);
                    } else {
                        System.out.println("Address Book not found.");
                    }
                    break;

                case 4:
                    System.out.println("Exiting the program. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }

        } while (choice != 4);

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

        do {
            // Display menu
            System.out.println("1. Add a new contact");
            System.out.println("2. View all contacts");
            System.out.println("3. Edit an existing contact");
            System.out.println("4. Delete a contact");
            System.out.println("5. Exit");
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
                    addressBook.displayAllContacts();
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
                    System.out.println("Exiting Address Book. Returning to main menu.");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }

        } while (choice != 5);
    }
}
