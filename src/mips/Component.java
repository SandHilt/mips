/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mips;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
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
    public Rectangle getBounds() {
        return this.bounds;
    }

    /**
     *
     * @return
     */
    public Color getColor() {
        return this.color;
    }

    public Component(Rectangle bounds, Color color) {
        this.bounds = bounds;
        this.color = color;
    }
    
    public void drawText(Graphics g, String text) {
        g.setColor(color);
        
        FontMetrics fm = g.getFontMetrics();
        
        Rectangle fontBounds = fm.getStringBounds(text, g).getBounds();
        center(bounds, fontBounds);
        fontBounds.y += fm.getAscent();
        
        for (String line : text.split("\n")) {
            g.drawString(line, fontBounds.x, fontBounds.y);
            fontBounds.translate(0, g.getFontMetrics().getHeight());
        }
    }

    public static void center(Rectangle outside, Rectangle inside) {
        int x = (outside.width - inside.width) / 2;
        int y = (outside.height - inside.height) / 2;

        x += outside.x;
        y += outside.y;

        inside.setLocation(x, y);
    }
}
