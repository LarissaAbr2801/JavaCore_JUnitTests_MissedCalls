package calls.JUnit;

import calls.MissedCalls;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSources;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.w3c.dom.ls.LSOutput;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class MissedCallsTests {
    MissedCalls sut;

    @BeforeEach
    public void initEach() {
        System.out.println("Тест для метода класса MissedCalls запущен");
    }

    @AfterEach
    public void finishEach(){
        System.out.println("Тест для метода класса MissedCalls завершен");
    }

    @Test
    public void testIfMapEmpty() {
        sut = new MissedCalls(new TreeMap<>());

        boolean result = sut.isMapEmpty();

        assertTrue(result);

        sut = new MissedCalls(null);

        System.out.println("Проверка на выброс исключения здесь");
        assertThrows(NullPointerException.class, () -> sut.isMapEmpty());
    }

    @Test
    public void testAddMissedCallsForException() {
        sut = new MissedCalls(null);

        assertThrows(NullPointerException.class, () -> sut.addMissedCall("345678", new HashMap<>(null)));
    }
}
