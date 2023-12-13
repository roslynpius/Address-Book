package com.bridgelabz.addressbook;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @desc The AddressBookSystem class represents a system that manages multiple Address Books.
 *       It maintains a dictionary of Address Book names to corresponding AddressBook objects.
 */
class AddressBookSystem {
    // Dictionary to store Address Books
    private Map<String, AddressBookList> addressBooks;
    // Dictionary to store City-Person and State-Person associations
    private Map<String, List<Contact>> cityPersonDictionary;
    private Map<String, List<Contact>> statePersonDictionary;

    /**
     * @desc Constructs a new AddressBookSystem with an empty dictionary of Address Books and dictionaries for City-Person and State-Person associations.
     */
    public AddressBookSystem() {
        addressBooks = new HashMap<>();
        cityPersonDictionary = new HashMap<>();
        statePersonDictionary = new HashMap<>();
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

    /**
     * @desc Search for a person in a city across all Address Books.
     *
     * @param cityName The city to search for.
     * @return List of persons in the specified city across all Address Books.
     */
    public List<Contact> getContactsInCity(String cityName) {
        List<Contact> personsInCity = addressBooks.values()
                .stream()
                .flatMap(addressBookList -> addressBookList.searchPersonInCity(cityName).stream())
                .collect(Collectors.toList());

        cityPersonDictionary.put(cityName, personsInCity);
        return personsInCity;
    }

    /**
     * @desc Search for a person in a state across all Address Books.
     *
     * @param stateName The state to search for.
     * @return List of persons in the specified state across all Address Books.
     */
    public List<Contact> getContactsInState(String stateName) {
        List<Contact> personsInState = addressBooks.values()
                .stream()
                .flatMap(addressBookList -> addressBookList.searchPersonInState(stateName).stream())
                .collect(Collectors.toList());

        statePersonDictionary.put(stateName, personsInState);
        return personsInState;
    }

    /**
     * @desc View persons by city from the City-Person dictionary.
     * @param cityName The city to view persons for.
     * @return List of persons in the specified city.
     */
    public List<Contact> viewPersonsByCity(String cityName) {
        return cityPersonDictionary.getOrDefault(cityName, List.of());
    }

    /**
     * @desc View persons by state from the State-Person dictionary.
     * @param stateName The state to view persons for.
     * @return List of persons in the specified state.
     */
    public List<Contact> viewPersonsByState(String stateName) {
        return statePersonDictionary.getOrDefault(stateName, List.of());
    }

    /**
     * @desc Get the count of contact persons by city across all Address Books.
     *
     * @return Map containing the count of contact persons by city.
     */
    public Map<String, Long> getCountByCity() {
        return addressBooks.values()
                .stream()
                .flatMap(addressBookList -> addressBookList.getCountByCity().entrySet().stream())
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.counting()));
    }

    /**
     * @desc Get the count of contact persons by state across all Address Books.
     *
     * @return Map containing the count of contact persons by state.
     */
    public Map<String, Long> getCountByState() {
        return addressBooks.values()
                .stream()
                .flatMap(addressBookList -> addressBookList.getCountByState().entrySet().stream())
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.counting()));
    }

}

