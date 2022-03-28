package calls.Hamcrest;

import calls.Contact;
import calls.Group;
import calls.PhoneBook;
import org.hamcrest.collection.IsMapContaining;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static org.hamcrest.CoreMatchers.is;

public class PhoneBookTestsHamcrest {
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

        assertThat(result, is(true));
    }

    @ParameterizedTest
    @MethodSource("sourceForMakeGroup")
    public void testMakeGroup(String input, Group expected) {
        sut = new PhoneBook(new HashMap<>());
        Group result = sut.makeGroup(input);
        assertThat(result, is(expected));

    }

    private static Stream<Arguments> sourceForMakeGroup() {
        return Stream.of(Arguments.of("работа", null), Arguments.of("коллеги", null), Arguments.of("Друзья", Group.FRIENDS),
                Arguments.of("Работа", Group.WORK));
    }

    @ParameterizedTest
    @MethodSource("sourceForFillContact")
    public void testFillContact(String[] contactData, Group group, Contact expected) {
        sut = new PhoneBook(new HashMap<>());
        Contact result = sut.fillContact(contactData, group);
        assertThat(result, is(expected));
    }

    private static Stream<Arguments> sourceForFillContact() {
        return Stream.of(Arguments.of(new String[]{"иван", "иванов", "12345"}, Group.WORK,
                        new Contact("иван", "иванов", "12345", null)),
                Arguments.of(new String[]{"мария", "иванова", "123"}, Group.valueOf("WORK"),
                        new Contact("мария", "иванова", "123", Group.WORK)));
    }

    @Test
    public void testDeleteContact() {
        Map<String, Contact> hashMap = new HashMap<>();
        sut = new PhoneBook(hashMap);
        Contact contact = new Contact("Илья", "Петров", "893578593", Group.WORK);
        hashMap.put(contact.getTelephoneNumber(), contact);
        sut.deleteContact(new String[] {"Илья", "Петров"});

        assertThat(hashMap, equalTo(Collections.EMPTY_MAP));
    }

    @Test
    public void testAddContact() {
        sut = new PhoneBook(new HashMap<>());
        sut.addContact(new String[] {"Илья", "Иванов", "23456"}, "семья");

        assertThat(sut.isMapEmpty(), is(true));
    }
}
