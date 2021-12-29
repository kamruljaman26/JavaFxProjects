package system.model.adt;

import java.io.Serializable;

public class Customer extends User implements Serializable {
    public Customer(String name, String phoneNum, String curAddress, String username, String password) {
        super(name, phoneNum, curAddress, username, password);
    }
}
