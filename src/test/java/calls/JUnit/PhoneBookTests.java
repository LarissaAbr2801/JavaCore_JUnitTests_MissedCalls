package calls.JUnit;

import calls.Contact;
import calls.Group;
import calls.PhoneBook;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class PhoneBookTests {
    PhoneBook sut;

    @BeforeEach
    public void initEach() {
        System.out.println("Тест для метода класса PhoneBook запущен");
    }

    @AfterEach
    public void finishEach() {
        System.out.println("Тест для метода класса PhoneBook завершен");
    }

    @Test
    public void testIsMapEmpty() {
        sut = new PhoneBook(new HashMap<>());

        boolean result = sut.isMapEmpty();

        assertTrue(result);
    }

    @ParameterizedTest
    @MethodSource("sourceForMakeGroup")
    public void testMakeGroup(String input, Group expected) {
        sut = new PhoneBook(new HashMap<>());
        Group result = sut.makeGroup(input);
        assertEquals(expected, result);

    }

    private static Stream<Arguments> sourceForMakeGroup() {
        return Stream.of(Arguments.of("работа", null), Arguments.of("коллеги", null), Arguments.of("Друзья", Group.FRIENDS),
                Arguments.of("Работа", Group.WORK));
    }

    @Test
    public void testFillContactExc() {
        sut = new PhoneBook(new HashMap<>());

        assertThrows(RuntimeException.class, () -> sut.fillContact(new String[] {"a"}, Group.WORK));
        assertThrows(IllegalArgumentException.class, () -> sut.fillContact(new String[] {"иван","иванов", "12345"},
                Group.valueOf( "work")));
    }

    @ParameterizedTest
    @MethodSource("sourceForFillContact")
    public void testFillContact(String[] contactData, Group group, Contact expected) {
        sut = new PhoneBook(new HashMap<>());
        Contact result = sut.fillContact(contactData, group);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> sourceForFillContact() {
        return Stream.of(Arguments.of(new String[]{"иван", "иванов", "12345"}, Group.WORK,
                        new Contact("иван", "иванов", "12345", null)),
                Arguments.of(new String[]{"мария", "иванова", "123"}, Group.valueOf("WORK"),
                        new Contact("мария", "иванова", "123", Group.WORK)));
    }

}
