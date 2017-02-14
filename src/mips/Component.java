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
    private boolean once;

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
        this.once = false;
    }

    public void drawText(Graphics g, String text) {
        g.setColor(color);

        FontMetrics fm = g.getFontMetrics();

        Rectangle fontBounds = fm.getStringBounds(text, g).getBounds();
        center(bounds, fontBounds);
        fontBounds.translate(0, fm.getAscent());

        if (once == false) {
            System.out.println("-------------");
        }
        for (String line : text.split("\n")) {
            Rectangle lineBound = fm.getStringBounds(line, g).getBounds();
            lineBound.setLocation(fontBounds.getLocation());

            center(fontBounds, lineBound);
            
            if (once == false) {
                System.out.println("fontBounds: " + fontBounds);
                System.out.println("line: " + line + " x " + lineBound);
            }
            
            g.drawRect(
                    fontBounds.x, 
                    fontBounds.y - fm.getAscent(), 
                    fontBounds.width, 
                    fontBounds.height
            );
            
            g.setColor(Color.red);
            g.drawRect(
                    lineBound.x, 
                    lineBound.y - fm.getAscent(), 
                    lineBound.width, 
                    lineBound.height
            );
            g.setColor(color);
            
            g.drawString(line, lineBound.x, lineBound.y);
            fontBounds.translate(0, fm.getHeight());
        }
        if (once == false) {
            System.out.println("-------------");
            once = true;
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
