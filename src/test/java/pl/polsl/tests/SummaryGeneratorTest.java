/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.polsl.tests;

import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pl.polsl.view.SummaryGenerator;

/**
 *
 * @author Bartosz Pomierny
 */
public class SummaryGeneratorTest {

    private SummaryGenerator summaryGen;

    @BeforeEach
    public void setUp() {
//        Does not work for an unknown reason, so I put the code into each test
//        summaryGen = new SummaryGenerator();  
    }

    /**
     * Testing if the printed message matches the one we'd want it to display
     * This method does not 'know' that -1 is invalid, since it gets its
     * parameters from other sources that do not return invalid parameters
     *
     * @param num numbers to be printed
     */
    @ParameterizedTest
    @ValueSource(ints = {-1, 2, 3, 10, 1000})
    public void testPrintSummary(int num) {
        summaryGen = new SummaryGenerator();
        String testMessage = "This number has appeared " + num + " times in the last " + num + " raffles, so it's probability of being raffled again in the next raffle is equal to: " + num + "%";
        summaryGen.printCount(num, num, num);
        assertEquals(testMessage, summaryGen.message);
    }

    /**
     * Testing if the method prints out a correct amount of numbers | Not much
     * to be parameterized since it should print a list of a fixed size
     */
    @Test
    public void testPrintLastTen() {
        summaryGen = new SummaryGenerator();
        List<Integer> list = IntStream.rangeClosed(0, 60).boxed().toList();
        summaryGen.printLastTen(list);
        assertEquals(60, summaryGen.listLastTen.size());
    }
}
