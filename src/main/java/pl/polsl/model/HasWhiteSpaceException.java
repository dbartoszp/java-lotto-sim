/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.model;

/**
 * exception that is being thrown when input contains a white space(s)
 *
 * @version 1.0
 * @author Bartosz Pomierny
 */
public class HasWhiteSpaceException extends RuntimeException {

    public HasWhiteSpaceException(String message) {
        super(message);
    }
}
