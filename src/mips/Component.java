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
public abstract class Component implements IRenderable {
    private Rectangle bounds;
    private Color color;
    private Point origin;

    public Point getOrigin() {
        return origin;
    }
    
    /**
     *
     * @return
     */
    public Rectangle getBounds(){
        return this.bounds;
    }
    
    /**
     *
     * @return
     */
    public Color getColor(){
        return this.color;
    }

    private static final String[] colors = {"f44336", "e91e63", "9c27b0",
        "673ab7", "3f51b5", "2196f3", "03a9f4", "00bcd4", "009688", "4caf50",
        "8bc34a", "cddc39", "ffeb3b", "ffc107", "ff9800", "ff5722", "795548",
        "9e9e9e", "607d8b"
    };
    
    public void changeColor(){
        int idx = new Random().nextInt(colors.length);
        this.color = Color.decode("#" + colors[idx]);
    }

    /**
     *
     * @param bounds
     * @param color
     */
    public Component(Rectangle bounds, Color color) {
        this.color = color;
        this.bounds = bounds;
        this.origin = bounds.getLocation();
    }
    
    /**
     *
     * @param g
     */
    public void fillRectangle(Graphics g) {
        g.setColor(color);
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
}
