package system.model;

import java.io.*;
import java.util.*;

public class CustomerDaoFile implements Dao<Customer> {

    private static Hashtable<String, Customer> customers = new Hashtable<>();
    private final String fileName = "fileDB/customerDB.txt";

    public CustomerDaoFile() {
        // read and init from file
        readDataFromFile();
    }

    /**
     * Read all Customers from file and init to Map
     */
    private synchronized void readDataFromFile() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            customers = (Hashtable) in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read all Customers from file and init to Map
     */
    private synchronized void writeDataIntoFile() {
        try {
            FileOutputStream fout = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(customers);
            out.flush();
            fout.close();
            out.close();
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

    @Override
    public boolean save(Customer customer) {
        Customer put = customers.put(customer.getUsername(), customer);
        writeDataIntoFile();
        return put == null;
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
}
