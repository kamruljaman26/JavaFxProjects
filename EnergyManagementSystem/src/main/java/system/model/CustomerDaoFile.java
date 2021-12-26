package system.model;

import system.utils.PasswordManager;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class CustomerDaoFile implements Dao<Customer> {

    private Hashtable<String, Customer> customers = new Hashtable<>();
    private final String fileName = "fileDB/customerDB.txt";

    public CustomerDaoFile() {
        // read and init from file
        readDataFromFile();
    }

    /**
     * Read all Customers from file and init to Map
     */
    private void readDataFromFile() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            this.customers = (Hashtable) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read all Customers from file and init to Map
     */
    private void writeDataIntoFile() {
        try {
            FileOutputStream fout = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(customers);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer get(String id) {
        return customers.get(id);
    }

    @Override
    public List<Customer> getAll() {
        return new ArrayList<>(customers.values());
    }

    public Hashtable getAllHTable() {
        return customers;
    }

    @Override
    public boolean save(Customer customer) {
        Customer put = customers.put(customer.getUsername(), customer);
        writeDataIntoFile();
        return put != null;
    }

    @Override
    public boolean update(String key, Customer customer) {
        if (customers.get(key) == null) return false;
        customers.computeIfPresent(customer.getUsername(), (k, v) -> customer);
        writeDataIntoFile();
        return true;
    }


    @Override
    public boolean delete(Customer customer) {
        boolean value = customers.remove(customer.getUsername()) != null;
        writeDataIntoFile();
        return value;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        // save text data for check login
        Dao<Customer> dao = new CustomerDaoFile();
        dao.save(new Customer("Kamrul", "+880", "AAA", "kamrul1",
                PasswordManager.encode("jaman")
        ));
    }
}
