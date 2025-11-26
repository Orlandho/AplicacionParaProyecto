package user;

public abstract class Person {

    protected String names, lastNames;
    protected int phone;
    protected long idNumber;

    public Person(String names, String lastNames, long idNumber, int phone) {
        this.names = names;
        this.lastNames = lastNames;
        this.idNumber = idNumber;
        this.phone = phone;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public long getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(long idNumber) {
        this.idNumber = idNumber;
    }

}
