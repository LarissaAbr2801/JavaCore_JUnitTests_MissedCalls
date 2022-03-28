package calls;

import java.util.*;

import input.*;

public class PhoneBook {

    private Map<String, Contact> phoneBook;

    public PhoneBook(Map<String, Contact> phoneBook) {
        this.phoneBook = phoneBook;
    }

    public boolean isMapEmpty() {
        return phoneBook.isEmpty();
    }

    public Contact fillContact(String[] contactData, Enum groups) {
        String name = contactData[0];
        String surname = contactData[1];
        String telephoneNumber = contactData[2];
        return new Contact(name, surname, telephoneNumber, groups);
    }

    public Group makeGroup(String input) {
        for (Group group : Group.values()) {
            if (group.getTitle().equals(input)) {
                return group;
            }
        }
        return null;
    }

    public void addContact(String[] contactData, String input) {
        Group group = makeGroup(input);

        if (group != null) {
            Contact contact = fillContact(contactData, group);

            if (phoneBook.containsKey(contact.getTelephoneNumber())) {
                System.out.println("Контакт с таким номером телефона уже существует");
            } else {
                phoneBook.put(contact.getTelephoneNumber(), contact);
            }
        } else {
            System.out.println("Такой группы не существует!");
        }
    }


    public void deleteContact(String[] nameAndSurname) {

        String name = nameAndSurname[0];
        String surname = nameAndSurname[1];
        int count = 0; //Если count останется нулевым, значит, ни один элемент в мапе не нашелся

        Iterator<Map.Entry<String, Contact>> iter = phoneBook.entrySet().iterator();

        while (iter.hasNext()) {
            Map.Entry<String, Contact> entry = iter.next();

            if (name.equals(entry.getValue().getName()) && (surname.equals(entry.getValue().getSurname()))) {
                iter.remove();
                System.out.println("Контакт успешно удален!");
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Такого контакта не существует!");
        }


    }

    public void editContact(String[] contactData) {
        String name = contactData[0];
        String surname = contactData[1];
        String telephoneNumber = contactData[2];
        int count = 0;//он будет в в цикле for. Если count останется нулевым, значит, ни один элемент в мапе не нашелся

        for (Map.Entry<String, Contact> entry : phoneBook.entrySet()) {

            if (name.equals(entry.getValue().getName()) && (surname.equals(entry.getValue().getSurname())) &&
                    (telephoneNumber.equals(entry.getValue().getTelephoneNumber()))) {

                String newName = Input.getValue(Input.scanner,
                        "Изменить имя: " + entry.getValue().getName() + " ?");

                if ((newName.equals("Да")) || (newName.equals("да"))) {
                    newName = Input.getValue(Input.scanner, "Введите новое имя:");
                    entry.getValue().setName(newName);
                }

                String newSurname = Input.getValue(Input.scanner,
                        "Изменить фамилию: " + entry.getValue().getSurname() + " ?");

                if ((newSurname.equals("Да")) || (newSurname.equals("да"))) {
                    newSurname = Input.getValue(Input.scanner, "Введите новую фамилию:");
                    entry.getValue().setSurname(newSurname);
                }

                String newNumber = Input.getValue(Input.scanner,
                        "Изменить номер телефона: " + entry.getValue().getTelephoneNumber() + " ?");

                if ((newNumber.equals("Да")) || (newNumber.equals("да"))) {
                    newNumber = Input.getValue(Input.scanner, "Введите новый номер телефона:");
                    entry.getValue().setTelephoneNumber(newNumber);
                }

                String newGroup = Input.getValue(Input.scanner,
                        "Изменить группу: " + entry.getValue().getGroup() + " ?");

                if ((newGroup.equals("Да")) || (newGroup.equals("да"))) {
                    newGroup = Input.getValue(Input.scanner, "Введите новую группу:");
                    entry.getValue().setGroup(makeGroup(newGroup));
                }

                count++;
            }
        }

        if (count == 0) {
            System.out.println("Контакт с таким именем, с такой фамилией и/или номером телефона не найден!"
                    + "\n");

        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, Contact> entry : phoneBook.entrySet()) {
            sb.append(entry.getValue() + "\n");

        }

        return sb.toString();
    }
}