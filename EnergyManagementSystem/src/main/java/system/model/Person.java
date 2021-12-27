package system.model;


import java.io.Serializable;

/**
 * a person class, hold all information about a person
 *
 * @author kamruljaman
 */
public abstract class Person implements Serializable {

    protected String name;
    protected String phoneNum; // phone number
    protected String curAddress; // current address

    public Person() {
        name = "";
        phoneNum = "";
        curAddress = "";
    }

    public Person(String name, String phoneNum, String curAddress) {
        this.name = name;
        this.phoneNum = phoneNum;
        this.curAddress = curAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getCurAddress() {
        return curAddress;
    }

    public void setCurAddress(String curAddress) {
        this.curAddress = curAddress;
    }
}
