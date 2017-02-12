/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mips;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author Bruno
 */
public class ProgramCounter extends Component {
    private int id;

    public ProgramCounter() {
        super(new Rectangle(), null);
    }
    
    public ProgramCounter(Rectangle bounds, Color color) {
        super(bounds, color);
    }
    
    ProgramCounter(int width, int height, Color color) {
        this(new Rectangle(width, height), color);
    }

    public int getId() {
        return this.id;
    }

    public void advanceInstruction() {
        this.id++;
    }
    
    @Override
    public void render(Graphics g) {
        super.fillRectangle(g);
    }
}
