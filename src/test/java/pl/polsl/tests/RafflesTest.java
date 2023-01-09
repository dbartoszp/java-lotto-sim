/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.polsl.tests;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pl.polsl.model.Raffles;

/**
 *
 * @author Bartosz Pomierny
 */
public class RafflesTest {

    private Raffles raffle;

    @BeforeEach
    public void setUp() {
//        Does not work for an unknown reason, so I put the code into each test
//        List list = new ArrayList<>();    
//        raffle = new Raffles(list);        
    }

    /**
     * Testing if GenerateRaffles generates a correct number of raffles if the
     * number of raffles is below zero, no raffles should be generated
     *
     * @param numOfRaffles the number of raffles to be generated
     */
    @ParameterizedTest
    @ValueSource(ints = {-1, 5, 10, 23})
    public void testGenerateRafflesValid(int numOfRaffles) {
        List list = new ArrayList<>();
        raffle = new Raffles(list);
        int expected = 6 * numOfRaffles;
        if (numOfRaffles > 0) {
            assertEquals(expected, raffle.generateRaffles(numOfRaffles).size());
        } else {
            assertEquals(0, raffle.generateRaffles(numOfRaffles).size());
        }
    }

    /**
     * Testing if the probability is correct | this method has not yet been
     * implemented so cannot be tested
     */
    @Test
    public void testProbability() {
        //todo
    }
}
