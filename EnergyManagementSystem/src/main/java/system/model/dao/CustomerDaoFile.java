package system.model.dao;

import system.model.adt.User;

import java.io.*;
import java.util.*;

public class CustomerDaoFile implements Dao<User> {

    private static Hashtable<String, User> customers;
    private final String fileName = "fileDB/customerDB.txt";

    public CustomerDaoFile() {
        // read and init from file
        customers = new Hashtable<>();
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
//            e.printStackTrace();
            System.out.println("File was not created");
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
    public User get(String id) {
        return customers.get(id);
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public boolean save(User customer) {
        User put = customers.put(customer.getUsername(), customer);
        writeDataIntoFile();
        return put == null;
    }

    @Override
    public boolean update(String key, User customer) {
        if (customers.get(key) == null) return false;
        customers.computeIfPresent(customer.getUsername(), (k, v) -> customer);
        writeDataIntoFile();
        return true;
    }

    @Override
    public boolean delete(User customer) {
        boolean value = customers.remove(customer.getUsername()) != null;
        writeDataIntoFile();
        return value;
    }
}
