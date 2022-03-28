package calls.Hamcrest;

import calls.MissedCalls;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MissedCallsTestsHamcrest {
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

        assertThat(result, is(true));
    }

    @Test
    public void testIfMapNUll() {
        sut = new MissedCalls(new TreeMap<>());

        assertThat(sut, notNullValue());
    }
}