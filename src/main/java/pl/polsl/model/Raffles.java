/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.model;

import Annotations.ToBeModified;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 *
 * @author Bartosz Pomierny
 * @version 1.0
 */
public class Raffles {

    /**
     * generated raffles are stored in this variable
     */
    public List rafflesGenerated;

    public Raffles(List list) {
        rafflesGenerated = list;
    }

    /**
     * generates a set number of raffles of 6 unique numbers between 1 and 49
     * (inclusive) creates a list of numbers between 1 and 49, and with each
     * raffle shuffles it, adding six first elements to the rafflesGenerated
     * List
     *
     *
     * @param numOfRaffles number of raffles to be generated
     * @return list of raffles
     */
    public List generateRaffles(int numOfRaffles) {

        List<Integer> rafflesList = new ArrayList<>();
        List<Integer> numbersList;
        numbersList = IntStream.rangeClosed(1, 49).boxed().toList();
        List<Integer> modifiableList;
        modifiableList = new ArrayList<>(numbersList);

        int counter;
         for (int i = 0; i < numOfRaffles; i++) {
            Collections.shuffle(modifiableList);
            counter = 0;
            for (int ii=0;ii<6;ii++) {
                rafflesList.add(modifiableList.get(counter));
                counter++;
            }
        }

//        System.out.println(rafflesList); //testing purposes
        rafflesGenerated = rafflesList;
        return rafflesList;
    }

    /**
     * method counting probability of a number appearing
     *
     * @param occ occurrences of a chosen number
     * @param numRaffles number of raffles
     * @return
     */
    public double probability(int occ, int numRaffles) {
       Double occ1 = new Double(occ);
       Double numRaffles1 = new Double(numRaffles);
        double probability = occ1/numRaffles1;
        if(probability>0.5) probability=1-probability;
        return probability;
    }

}
