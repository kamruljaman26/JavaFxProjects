package system.model.adt;

import java.io.Serializable;

public class Customer extends User implements Serializable {
    public Customer(String name, String phoneNum, String curAddress, String username, String password) {
        super(name, phoneNum, curAddress, username, password);
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Phone: %s, Address: %s, Username: %s",
                getName(),getPhoneNum(), getCurAddress(), getUsername());
    }
}
