/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.controller;

import Annotations.ToBeModified;
import pl.polsl.view.*;
import pl.polsl.model.*;
import pl.polsl.model.HasWhiteSpaceException;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author Bartosz Pomierny
 * @version 1.0
 */
public class Controller {

    /**
     * constructor of the Controller class
     *
     * @param rdb RaffleDatabase object
     * @param rfs Raffles object
     * @param sumg SummaryGenerator object
     */
    public Controller(RaffleDatabase rdb, Raffles rfs, SummaryGenerator sumg) {
        this.raffleDB = rdb;
        this.raffles = rfs;
        this.summaryGen = sumg;
    }

    /**
     * represent RaffleDatabase
     */
    private RaffleDatabase raffleDB;
    /**
     * represent Raffles
     */
    public Raffles raffles;
    /**
     * represent SummaryGenerator
     */
    private SummaryGenerator summaryGen;

//    static Scanner userInput = new Scanner(System.in);
    /**
     * method for checking if there are any arguments
     *
     * @param arg the arguments input
     * @return true if there are no arguments, false if there are
     */
    private boolean noArgs(String[] arg) {
        return arg.length == 0;
    }

    ;
    /**
     * method for checking if the user put the correct value
     * @param arg the arguments input
     * @return true if the value is not correct, false if it is correct
     */
    private boolean wrongVal(String[] arg, int max) {
        return Integer.parseInt(String.join("", arg)) < 1 || Integer.parseInt(String.join("", arg)) > max;
    }

    /**
     * method for checking if the input is a numeric uses regex to check if the
     * input is a numeric
     *
     * @param arg the arguments input
     * @return true if not parse-able, false otherwise
     */
    private boolean notaNumber(String arg[]) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        if (arg == null) {
            return true;
        }
        return !pattern.matcher(arg[0]).matches();
    }

    private boolean hasWhiteSpace(String[] arg) {
//        System.out.println(arg[0]);
        return arg[0].contains(" ");
    }

    /**
     * method combining all the previous checking methods
     *
     * @param max maximum number in case we have to change the range
     * @param arg the arguments input
     * @return true if an error has been detected, false if everything is okay
     */
    public boolean checkAll(String[] arg, int max) {
        if (noArgs(arg)) {
//            System.out.println("No arguments!!!");
            return true;
        }
        if (hasWhiteSpace(arg)) {
            throw new HasWhiteSpaceException("Your argument consists of a white space.");
        }
        if (notaNumber(arg)) {
//            System.out.println("This is not a number. Please try again.");
            return true;
        }
        if (wrongVal(arg, max)) {
//            System.out.println("Wrong value! Input a number between 1 and " + max);
            return true;
        }

        return false;
    }

    /**
     * method for generating Raffles
     *
     * @param num number of raffles to be generated
     *
     * @return generated raffle results as an array
     */
    public List generateRaffles(int num) {
        this.raffleDB.raffleArray = this.raffles.generateRaffles(num);
        return this.raffleDB.raffleArray;
    }

    /**
     * method for counting occurrences
     *
     * @param number number we're looking for
     * @param list array we're searching through
     * @return count of occurrences
     */
    public int countOcc(int number, List list) {
        return this.raffleDB.countOccurrence(number, list);
    }

    /**
     * method for displaying results
     *
     * @param raffleNum number of raffles
     * @param occ number of occurrences
     * @param prob probability of appearing in the next raffle
     * @param list raffles
     */
    @ToBeModified
    public void displayResult(int raffleNum, int occ, double prob, List list) {
        this.summaryGen.printCount(occ, raffleNum, prob);
        this.summaryGen.printLastTen(list);
    }

    /**
     * simple setter for Raffles
     *
     * @param rfs raffles to be set to
     */
    public void setRFS(Raffles rfs) {
        this.raffles = rfs;
    }

    /**
     * simple setter for RaffleDatabase
     *
     * @param rdb raffleDB to be set to
     */
    public void setRDB(RaffleDatabase rdb) {
        this.raffleDB = rdb;
    }

    /**
     * simple setter for SummaryGenerator
     *
     * @param sumg summaryGen to be set to
     */
    public void setSUMG(SummaryGenerator sumg) {
        this.summaryGen = sumg;
    }
}
