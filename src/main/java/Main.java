import calls.*;
import input.Input;

import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        HashMap<String, Contact> hashMap = new HashMap<>();
        PhoneBook phoneBook1 = new PhoneBook(hashMap);
        MissedCalls missedCalls1 = new MissedCalls(new TreeMap<>());

        System.out.println("Программа: пропущенные вызовы");
        out:
        while (true) {
            try {
                System.out.println();
                String choice = Input.getValue(Input.scanner, "Меню:"
                        + "\n1. Добавить контакт"
                        + "\n2. Удалить контакт"
                        + "\n3. Редактировать контакт"
                        + "\n4. Вывести список контактов"
                        + "\n5. Добавить пропущенный вызов"
                        + "\n6. Вывести все пропущенные вызовы"
                        + "\n7. Очистить пропущенные вызовы"
                        + "\n8. Выход"
                        + "\nВыберите пункт из меню (1 - 8):");
                int number = Integer.parseInt(choice);

                switch (number) {
                    case 1:
                        try {
                            String[] contactData = Input.requestContacts().split(" ");
                            System.out.println("Группы: " + Arrays.toString(Group.values()));
                            String input = Input.getValue(Input.scanner, "Выберите группу: ");
                            phoneBook1.addContact(contactData, input);
                        } catch (RuntimeException e) {
                            System.out.println("Некорректно введенные данные!");
                        }
                        break;
                    case 2:
                        try {
                            if (!phoneBook1.isMapEmpty()) {
                                String[] nameAndSurname = Input.deleteContact().split(" ");
                                phoneBook1.deleteContact(nameAndSurname);
                            } else {
                                System.out.println("Список контактов пуст!");
                            }
                        } catch (RuntimeException e) {
                            System.out.println("Введите все данные!");
                        }
                        break;
                    case 3:
                        try {
                            if (!phoneBook1.isMapEmpty()) {
                                String[] contactData = Input.requestContacts().split(" ");
                                phoneBook1.editContact(contactData);
                            } else {
                                System.out.println("Список контактов пуст!");
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Введите все данные!");
                        }
                        break;
                    case 4:
                        if (!phoneBook1.isMapEmpty()) {
                            System.out.println("Список контактов"
                                    + "\n================================================================");
                            System.out.format("%-20s%-20s%-20s%-20s", "ИМЯ", "ФАМИЛИЯ", "НОМЕР", "ГРУППА");
                            System.out.println();
                            System.out.println(phoneBook1);

                        } else {
                            System.out.println("Список контактов пуст!");
                        }
                        break;
                    case 5:
                        String phoneNumber = Input.getValue(Input.scanner, "Введите номер телефона");
                        missedCalls1.addMissedCall(phoneNumber, hashMap);
                        break;
                    case 6:
                        if (!missedCalls1.isMapEmpty()) {
                            System.out.println("Список пропущенных звонков:");
                            System.out.format("%-30s%-30s", "Время", "Номер телефона");
                            System.out.println("\n" + missedCalls1);
                        } else {
                            System.out.println("Список пропущенных звонков пуст!");

                        }

                        break;
                    case 7:
                        missedCalls1.clearMissedCalls();
                        System.out.println("Список пропущенных звонков очищен!");
                        break;
                    case 8:
                        break out;
                    default:
                        System.out.println("Такого номера нет в меню!");
                }
            } catch (
                    NumberFormatException e) {
                System.out.println("Введите цифру!");
            }
        }
    }
}