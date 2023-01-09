/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.polsl.tests;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pl.polsl.model.RaffleDatabase;

/**
 *
 * @author Bartosz Pomierny
 */
public class RaffleDatabaseTest {

    private RaffleDatabase raffleDB;

    @BeforeEach
    public void setUp() {
//        Does not work for an unknown reason, so I put the code into each test
//        this.raffleDB = new RaffleDatabase();
//        List list = new ArrayList<>();
    }

    /**
     * Testing if the method counts the occurrence of a number correctly,
     * assuming the input is valid
     *
     * There is no point in testing with an invalid argument type (for example:
     * string=("a","b","c")), since the code then will simply not compile
     *
     * @param value the number we weant to know the occurrence of
     */
    @ParameterizedTest
    @ValueSource(ints = {5, 10, 20})
    public void testCountOccurrenceValid(int value) {
        this.raffleDB = new RaffleDatabase();
        List list = new ArrayList<>();
        list.add(1);
        list.add(value);
        list.add(value);
        list.add(value);
        list.add(value);
        list.add(value);
        assertEquals(5, raffleDB.countOccurrence(value, list));
    }

}
