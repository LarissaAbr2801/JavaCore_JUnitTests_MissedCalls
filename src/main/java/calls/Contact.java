package calls;

import java.util.Objects;

public class Contact {

    private String name;
    private String surname;
    private String telephoneNumber;
    private Enum group;

    public Contact(String name, String surname, String telephoneNumber, Enum group) {
        this.name = name;
        this.surname = surname;
        this.telephoneNumber = telephoneNumber;
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(name, contact.name) && Objects.equals(surname, contact.surname) && Objects.equals(telephoneNumber, contact.telephoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, telephoneNumber);
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public Enum getGroup() {
        return group;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public void setGroup(Enum group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return String.format("%-20s%-20s%-20s%-20s", name, surname, telephoneNumber, group);
    }
}
