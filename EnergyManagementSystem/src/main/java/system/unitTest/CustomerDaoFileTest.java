package system.unitTest;

import org.junit.jupiter.api.*;
import system.model.adt.User;
import system.model.dao.CustomerDaoFile;
import system.model.dao.Dao;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerDaoFileTest {

    private static Dao<User> dao;
    private static ArrayList<User> list;


    @BeforeEach
    void setup() {
        //log.info("@BeforeEach - executes before each test method in this class");
        // "@BeforeAll - executes once before all test methods in this class"
        dao = new CustomerDaoFile();
        list = new ArrayList<>();
        User customer = new User("Jhon Due", "+0011223344", "USA",
                "jhon12", "jhon1122");
        ;
        User customer2 = new User("Jony Hakon", "+003425334", "CA",
                "jony23", "11jony");

        // save data
        dao.save(customer);
        dao.save(customer2);
        list.add(customer);
        list.add(customer2);
    }

    @DisplayName("TestGetCustomer")
    @Test
    void testGetCustomer() {
        User customer = new User("Jhon Due", "+0011223344", "USA",
                "jhon12", "jhon1122");
        assertEquals(dao.get(customer.getUsername()), customer);
    }

    @DisplayName("TestGetAllCustomer")
    @Test
    void testGetAllCustomer() {
        assertEquals(dao.getAll(), list);
    }

    @DisplayName("TestSaveCustomer")
    @Test
    void testSaveCustomer() {
        // Create list
        User customer = new User("Jocab", "112233", "AUS", "jocab1", "123456");
        dao.save(customer); // save on file
        // test
        assertEquals(dao.get(customer.getUsername()), customer);
        dao.delete(customer);
    }

    @DisplayName("TestUpdateCustomer")
    @Test
    void testUpdateCustomer() {
        // Create list
        User cus = new User("Jhon Due", "+0011223344", "USA",
                "jhon12", "jhon1122");
        User updatedCus = new User("Honor Milan", "+1111223344", "CA",
                "jhon12", "jhon1122");

        dao.update(cus.getUsername(),updatedCus); // update file
        list.add(list.indexOf(cus),updatedCus); // update list


        // test
        assertEquals(dao.get(cus.getUsername()), updatedCus);
    }

    @DisplayName("TestDeleteCustomer")
    @Test
    void testDeleteCustomer() {
        // Create list
        User customer = new User("Rohan", "112233", "AUS", "rohan1", "123456");
        dao.save(customer); // add to file db
        dao.delete(customer); // delete from file db

        // test
        assertEquals(dao.get(customer.getUsername()), null);
    }

}
