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
public abstract class Component implements IRenderable {
    public abstract Color getColor();
    
    public void fillRectangle(Graphics g, Rectangle r, Color color) {
        g.setColor(color);
        g.fillRect(r.x, r.y, r.width, r.height);
    }
}
