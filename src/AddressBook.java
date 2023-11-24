import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Contact class represents a person's contact information.
 * It includes fields for the first name, last name, address,
 * city, state, ZIP code, phone number, and email.
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
 * It provides methods to add a new contact and display all contacts.
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
        System.out.println();
        System.out.println("Address Book Contacts:");
        System.out.println("-------------------------");
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
}

public class AddressBook {
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
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Get contact information from the user
                    System.out.println("Enter Contact Information:");
                    System.out.print("First Name: ");
                    String firstName = scanner.next();

                    System.out.print("Last Name: ");
                    String lastName = scanner.next();

                    System.out.print("Address: ");
                    String address = scanner.next();

                    System.out.print("City: ");
                    String city = scanner.next();

                    System.out.print("State: ");
                    String state = scanner.next();

                    System.out.print("ZIP Code: ");
                    String zip = scanner.next();

                    System.out.print("Phone Number: ");
                    String phoneNumber = scanner.next();

                    System.out.print("Email: ");
                    String email = scanner.next();

                    // Create a new contact
                    Contact newContact = new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email);

                    // Add the new contact to the address book
                    addressBook.addContact(newContact);
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
                    System.out.println("Exiting the program. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }

        } while (choice != 4);

        scanner.close();
    }
}
