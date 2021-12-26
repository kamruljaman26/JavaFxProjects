package system.model;

/**
 * a person class, hold all information about a person
 * @author kamruljaman
 */
public abstract class Person {
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
}
