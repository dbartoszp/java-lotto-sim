/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.model;

import java.util.List;

/**
 *
 * @author SuperStudent
 * @version 1.0
 */
public class RaffleDatabase {

    /* an array of raffles already existing*/
    public List raffleArray;

    /**
     * method for counting how many times a number has been spotted in an array
     *
     * @param number the number we're looking for
     * @param list the array which the method is looking through
     * @return the count of occurrences of the number
     */
    public int countOccurrence(int number, List list) {
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(number)) {
                count++;
            }
        }
        return count;
    }
}
