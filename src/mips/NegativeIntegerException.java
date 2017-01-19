/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mips;

/**
 * When a number need to be positive.
 * @author Bruno
 */
public class NegativeIntegerException extends IllegalArgumentException {
    public NegativeIntegerException() {
        super("The value need to be greater than zero.");
    }
}
