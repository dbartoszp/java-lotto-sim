/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.polsl.tests;

import pl.polsl.controller.Controller;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import pl.polsl.model.*;
import pl.polsl.view.SummaryGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author Bartosz Pomierny
 */
public class ControllerTest {

    private Controller controller;
    private SummaryGenerator smg;
    private Raffles rfs;
    private RaffleDatabase rdb;
    private List list;

    @BeforeEach
    public void setUp() {
//        Does not work for an unknown reason, so I put the code into each test
//        rfs = new Raffles(list);
//        controller = new Controller(rdb, rfs, smg);
    }

    /**
     * Testing if the main method checkAll works correctly, assuming the input
     * is correct
     *
     * @param testString strings to be tested
     */
    @ParameterizedTest
    @ValueSource(strings = {"12", "1", "20", "49"})
    public void testCheckAllValid(String testString) {
        rfs = new Raffles(list);
        controller = new Controller(rdb, rfs, smg);
        String[] arrayParam = new String[1];
        arrayParam[0] = testString;
        assertFalse(controller.checkAll(arrayParam, 49));
    }

    /**
     * Testing if the main method checkAll works correctly, assuming the input
     * is wrong
     *
     * @param testString strings to be tested
     */
    @ParameterizedTest
    @ValueSource(strings = {"b", "122", "-5", "b"})
    public void testCheckAllInvalid(String testString) {
        rfs = new Raffles(list);
        controller = new Controller(rdb, rfs, smg);
        String[] arrayParam = new String[1];
        arrayParam[0] = testString;
        assertTrue(controller.checkAll(arrayParam, 49));
    }

    /**
     * Testing if an exception is thrown when arguments contain a white space
     *
     * @param testString strings to be tested
     */
    @ParameterizedTest
    @ValueSource(strings = {"12 1", " 1", "5 "," "})
    public void testCheckAllWhiteSpace(String testString) {
        rfs = new Raffles(list);
        controller = new Controller(rdb, rfs, smg);
        String[] testStringArr = {testString};
        try {
            controller.checkAll(testStringArr, 49);
            fail("An exception was supposed to be thrown");
        } catch (HasWhiteSpaceException expectedException) {
        }
    }

    /**
     * Methods countOcc, generateRaffles, displayResult have already been tested
     */
}
