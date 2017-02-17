/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mips;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Bruno
 */
public class Multiplexer extends ALU {

    public Multiplexer(Rectangle r, Color c) {
        super(r, c);
    }

    public Multiplexer() {
        this(new Rectangle(150, 30), Color.black);
    }
}
