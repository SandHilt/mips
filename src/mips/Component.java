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

/**
 *
 * @author Bruno
 */
public abstract class Component implements IRenderable {
    private final Rectangle bounds;
    private final Color color;

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

    /**
     *
     * @param bounds
     * @param color
     */
    public Component(Rectangle bounds, Color color) {
        this.color = color;
        this.bounds = bounds;
    }
    
    /**
     *
     * @param g
     */
    public void fillRectangle(Graphics g) {
        g.setColor(color);
        g.draw3DRect(bounds.x, bounds.y, bounds.width, bounds.height, true);
    }
    
    public static void center(Rectangle outside, Rectangle inside) {
        int x = (outside.width - inside.width) / 2;
        int y = (outside.height - inside.height) / 2;

        x += outside.x;
        y += outside.y;
        
        inside.setLocation(x, y);
    }
}
