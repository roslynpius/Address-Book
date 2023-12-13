import com.bridgelabz.addressbook.AddressBookList;
import com.bridgelabz.addressbook.Contact;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @desc Test cases to check the count by state and count by city function
 */
public class AddressBookTest {

    @Test
    void testGetCountByCity() {
        // Create a sample AddressBookList
        AddressBookList addressBookList = new AddressBookList();

        // Create some sample contacts with different cities
        Contact contact1 = new Contact("John", "Doe", "Address1", "City1", "State1", "Zip1", "Phone1", "Email1");
        Contact contact2 = new Contact("Jane", "Doe", "Address2", "City1", "State2", "Zip2", "Phone2", "Email2");
        Contact contact3 = new Contact("Alice", "Smith", "Address3", "City2", "State2", "Zip3", "Phone3", "Email3");

        // Add contacts to the address book
        addressBookList.addContact(contact1);
        addressBookList.addContact(contact2);
        addressBookList.addContact(contact3);

        // Get the count by city
        Map<String, Long> countByCity = addressBookList.getCountByCity();

        // Validate the results
        assertEquals(2, countByCity.get("City1")); // Expecting 2 persons in City1
        assertEquals(1, countByCity.get("City2")); // Expecting 1 person in City2
        assertEquals(null, countByCity.get("NonExistentCity")); // Expecting null for a city with no persons
    }

    @Test
    void testGetCountByState() {
        // Create a sample AddressBookList
        AddressBookList addressBookList = new AddressBookList();

        // Create some sample contacts with different states
        Contact contact1 = new Contact("John", "Doe", "Address1", "City1", "State1", "Zip1", "Phone1", "Email1");
        Contact contact2 = new Contact("Jane", "Doe", "Address2", "City2", "State1", "Zip2", "Phone2", "Email2");
        Contact contact3 = new Contact("Alice", "Smith", "Address3", "City3", "State2", "Zip3", "Phone3", "Email3");

        // Add contacts to the address book
        addressBookList.addContact(contact1);
        addressBookList.addContact(contact2);
        addressBookList.addContact(contact3);

        // Get the count by state
        Map<String, Long> countByState = addressBookList.getCountByState();

        // Validate the results
        assertEquals(2, countByState.get("State1")); // Expecting 2 persons in State1
        assertEquals(1, countByState.get("State2")); // Expecting 1 person in State2
        assertEquals(null, countByState.get("NonExistentState")); // Expecting null for a state with no persons
    }
}
