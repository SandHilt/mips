/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mips;

/**
 * When arguments need to be same.
 * @author Bruno
 */
public class DifferentValueException extends IllegalArgumentException {
    public DifferentValueException() {
        super("The value need to be same value.");
    }
}
