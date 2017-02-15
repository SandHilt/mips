/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mips;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author Bruno
 */
public abstract class Component implements IRenderable {

    private final Rectangle shape;
    private final Color color;

    public Rectangle getShape() {
        return shape.getBounds();
    }
    
    public void setLocation(Point p) {
        shape.setLocation(p);
    }
    
    public void setSize(Dimension d) {
        shape.setSize(d);
    }
    
    public Color getColor() {
        return color;
    }

    public Component(Rectangle bounds, Color color) {
        this.shape = bounds;
        this.color = color;
    }
    
    private void drawRect(Graphics g, Rectangle r, Color c) {
        g.setColor(c);
        g.drawRect(r.x, r.y, r.width, r.height);
        g.setColor(color);
    }

    public void drawText(Graphics g, String text) {
        g.setColor(color);

        FontMetrics fm = g.getFontMetrics();

        /* rectangle from text */
        Rectangle fontBounds = fm.getStringBounds(text, g).getBounds();

        /* center in component */
        center(shape, fontBounds);

        /* translate height need to fix */
        fontBounds.translate(0, fm.getAscent());

        /* a auxiliar for each line */
        Rectangle aux = fontBounds.getBounds();

        String[] words = text.split("\n");

        for (int i = 0, len = words.length; i < len; i++) {
            String line = words[i];
            
            /* get bound of a line */
            Rectangle lineBound = fm.getStringBounds(line, g).getBounds();
            
            /* put bound in same position of initial center */
            lineBound.setLocation(aux.getLocation());
            
            if (len > 1) {
                /* first position of words */
                Rectangle block = aux.getBounds();
                block.height *= len;
                center(shape, block);
                block.translate(0, fm.getAscent());
                
                aux.setLocation(block.getLocation());
                aux.translate(0, fm.getHeight() * i);
                
                lineBound.setLocation(block.getLocation());
                lineBound.translate(0, fm.getHeight() * i);
            }
            
            /* align text in center */
            center(aux, lineBound);
            
            /* show border in texts */
            Rectangle q = lineBound.getBounds();
            q.translate(0, -fm.getAscent());

            g.drawString(line, lineBound.x, lineBound.y);
            aux.translate(0, fm.getHeight());
        }
    }

    public static void center(Rectangle outside, Component component) {
        Rectangle inside = component.getShape();
        center(outside, inside);
        component.setLocation(inside.getLocation());
    }
    
    public static void center(Rectangle outside, Rectangle inside) {
        int x = (outside.width - inside.width) / 2;
        int y = (outside.height - inside.height) / 2;

        x += outside.x;
        y += outside.y;

        inside.setLocation(x, y);
    }
}
