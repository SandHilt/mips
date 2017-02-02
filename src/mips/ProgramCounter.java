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
public class ProgramCounter extends Component {
    private Rectangle bounds;
    private Color color;

    public ProgramCounter() {
        this.bounds = new Rectangle(3, 4);
    }
    
    public ProgramCounter(Rectangle bounds, Color color) {
        this.bounds = bounds;
        this.color = color;
    }
    
    public ProgramCounter(int x, int y, int widht, int height) {
        this();
        this.bounds = new Rectangle(x, y, widht, height);
    }
    public ProgramCounter(int x, int y, int widht, int height, Color color) {
        this(new Rectangle(x, y, widht, height), color);
    }

    public ProgramCounter(int widht, int height) {
        this();
        this.bounds = new Rectangle(widht, height);
    }
    
    public ProgramCounter(int widht, int height, Color color) {
        this(new Rectangle(widht, height), color);
    }
    
    @Override
    public void render(Graphics g) {
        super.fillRectangle(g, this.bounds, this.color);
    }

    public Rectangle getBounds() {
        return this.bounds;
    }

    @Override
    public Color getColor() {
        return this.color;
    }
}
