import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * The Contact class represents a person's contact information.
 * It includes fields for the first name, last name, address,
 * city, state, ZIP code, phone number, and email.
 *
 * This class provides a constructor to initialize a new contact
 * and a method to display the contact information.
 */
class Contact {
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
     * Constructs a new Contact with the specified information.
     *
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
     * Displays the contact information on the console.
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
}

/**
 * The AddressBook class represents a collection of contacts.
 * It provides methods to add a new contact, display all contacts,
 * edit an existing contact, delete a contact using their name,
 * and add multiple persons to the address book.
 */
class AddressBookList {
    // List to store contacts
    private List<Contact> contacts;

    /**
     * Constructs a new AddressBook with an empty list of contacts.
     */
    public AddressBookList() {
        contacts = new ArrayList<>();
    }

    /**
     * Adds a new contact to the address book.
     *
     * @param contact The contact to be added.
     */
    public void addContact(Contact contact) {
        contacts.add(contact);
        System.out.println("Contact added to the address book.");
    }

    /**
     * Displays all contacts in the address book.
     */
    public void displayAllContacts() {
        System.out.println("Address Book Contacts:");
        for (Contact contact : contacts) {
            contact.displayContact();
            System.out.println("-------------------------");
        }
    }

    /**
     * Edits an existing contact using their first and last names.
     * Prompts the user to enter new information for the contact.
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
     * Deletes a contact using their first and last names.
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
}

public class AddressBook{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create an AddressBook
        AddressBookList addressBook = new AddressBookList();

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
                    // Display all contacts in the address book
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
                    System.out.println("Exiting the program. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }

        } while (choice != 5);

        scanner.close();
    }
}
