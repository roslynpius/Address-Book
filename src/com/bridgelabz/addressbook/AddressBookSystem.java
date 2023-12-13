package com.bridgelabz.addressbook;

import java.util.HashMap;
import java.util.Map;

/**
 * @desc The AddressBookSystem class represents a system that manages multiple Address Books.
 *       It maintains a dictionary of Address Book names to corresponding AddressBook objects.
 */
class AddressBookSystem {
    // Dictionary to store Address Books
    private Map<String, AddressBookList> addressBooks;

    /**
     * @desc Constructs a new AddressBookSystem with an empty dictionary of Address Books.
     */
    public AddressBookSystem() {
        addressBooks = new HashMap<>();
    }

    /**
     * @desc Adds a new Address Book to the system.
     *
     * @param name The unique name of the Address Book.
     */
    public void addAddressBook(String name) {
        if (!addressBooks.containsKey(name)) {
            AddressBookList newAddressBook = new AddressBookList();
            addressBooks.put(name, newAddressBook);
            System.out.println("Address Book '" + name + "' added to the system.");
        } else {
            System.out.println("An Address Book with the name '" + name + "' already exists.");
        }
    }

    /**
     * @desc Displays all Address Books in the system.
     */
    public void displayAllAddressBooks() {
        System.out.println("Address Books in the System:");
        for (String name : addressBooks.keySet()) {
            System.out.println(name);
        }
        System.out.println("-------------------------");
    }

    /**
     * @desc Gets the Address Book with the specified name from the system.
     *
     * @param name The name of the Address Book to get.
     * @return The AddressBook object.
     */
    public AddressBookList getAddressBook(String name) {
        return addressBooks.get(name);
    }
}

