package system.model.adt;

import java.io.Serializable;
import java.util.Objects;

public class User extends Person implements Serializable {

    // username & password
    private String username;
    private String password;

    public User(String name, String phoneNum, String curAddress, String username, String password) {
        super(name, phoneNum, curAddress);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", curAddress='" + curAddress + '\'' +
                '}';
    }
}
