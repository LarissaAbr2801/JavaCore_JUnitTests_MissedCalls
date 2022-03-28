package input;

import java.util.Scanner;

public class Input {

    public static Scanner scanner = new Scanner(System.in);

    public static String getValue(Scanner scanner, String asking) {
        System.out.println(asking);
        return scanner.nextLine();
    }

    public static String requestContacts() {
        return getValue(scanner, "Введите имя, фамилию, номер телефона контакта через пробел");
    }

    public static String deleteContact() {
        return getValue(scanner, "Введите имя и фамилию через пробел для удаления контакта");
    }

}