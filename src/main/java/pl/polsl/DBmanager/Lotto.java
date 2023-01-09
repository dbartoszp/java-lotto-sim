package pl.polsl.DBmanager;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
import Annotations.ToBeModified;
import java.lang.reflect.Method;
import pl.polsl.controller.*;
import pl.polsl.view.*;
import pl.polsl.model.*;
import java.util.Scanner;
import java.util.List;

/**
 *
 * @author Bartosz Pomierny
 * @version 1.0
 */
public class Lotto {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String[] argsStringArr = args;
//        String[] raffleNumArr = null;
        List raffleList = List.of("");
        RaffleDatabase raffleDB = new RaffleDatabase();
        Raffles raffles = new Raffles(raffleList);
        SummaryGenerator summaryGen = new SummaryGenerator();
        Controller controller = new Controller(raffleDB, raffles, summaryGen);

        int toBeModifiedCounter = 0;
        // by using annotations, checking if any of the main MVC classes have methods that are to be modified
        // this part of the main method is surely possible to be shortened, and is TODO
        for (Method method : Controller.class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ToBeModified.class)) {
                toBeModifiedCounter++;
                System.out.println("Controller - " + toBeModifiedCounter + " method(s) to be modified");
            }
        }
        for (Method method : SummaryGenerator.class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ToBeModified.class)) {
                toBeModifiedCounter++;
                System.out.println("SummaryGenerator - " + toBeModifiedCounter + " method(s) to be modified");
            }
        }
        toBeModifiedCounter = 0;
        for (Method method : Raffles.class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ToBeModified.class)) {
                toBeModifiedCounter++;
                System.out.println("Raffles - " + toBeModifiedCounter + " method(s) to be modified");
            }
        }
        toBeModifiedCounter = 0;
        for (Method method : RaffleDatabase.class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ToBeModified.class)) {
                toBeModifiedCounter++;
                System.out.println("RaffleDatabase - " + toBeModifiedCounter + " method(s) to be modified");
            }
        }

        while (true) {
            if (!controller.checkAll(argsStringArr, 49)) {
                break;
            }
            System.out.println("Please input a new number: ");
            argsStringArr = input.nextLine().split("");
        }
        String argsString = String.join("", argsStringArr);

        int numberOfRaffles = 10000;

        controller.generateRaffles(numberOfRaffles);
        int occurrences = controller.countOcc(Integer.parseInt(argsString), controller.raffles.rafflesGenerated);
        double probability = controller.raffles.probability(occurrences, numberOfRaffles);

        controller.displayResult(numberOfRaffles, occurrences, probability, controller.raffles.rafflesGenerated);
    }
    
}
