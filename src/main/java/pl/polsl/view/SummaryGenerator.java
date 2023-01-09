/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.view;

import Annotations.ToBeModified;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 * @author Bartosz Pomierny
 * @version 1.0
 */
public class SummaryGenerator {

    public String message;
    public Stream stream;
    public List listLastTen;

    /**
     * method for printing the number of appearances of previously set number
     *
     * @param raffleNum number of raffles
     * @param occ the number of times our number has appeared
     * @param prob the probability of the number's occurrence
     */
    @ToBeModified
    public void printCount(int occ, int raffleNum, double prob) {
        message = "This number has appeared " + occ + " times in the last " + raffleNum + " raffles, so it's probability of being raffled again in the next raffle is equal to: " + prob + "%";
        System.out.println(message);
    }

    /**
     * method for printing last ten raffles using streams and foreach loop
     *
     * @param list complete list of numbers generated via Raffles
     */
    public void printLastTen(List list) {
        listLastTen = list.subList(0, 60);
        stream = listLastTen.stream();
        System.out.println("Last ten raffles:");
        stream.forEach(p -> System.out.print(p + ","));
    }
}
