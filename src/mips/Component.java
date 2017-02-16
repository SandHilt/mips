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
import java.awt.geom.Line2D;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Bruno
 */
public abstract class Component implements IRenderable {

    public static void center(Rectangle outside, Component component) {
        Rectangle inside = component.getBounds();
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

    private final Rectangle bounds;
    private final Color color;
    private Rectangle input;
    private Rectangle output;
    private List<Line2D> lines;

    public Component(Rectangle bounds, Color color) {
        this.bounds = bounds;
        this.color = color;
        this.lines = new LinkedList<Line2D>();

        if (bounds != null) {
            fixPoles();
        }
    }

    private void fixPoles() {
        Dimension d = new Dimension(16, 16);

        Point in = new Point(bounds.x, (int) bounds.getCenterY());
        in.translate(Math.floorDiv(-d.width, 2), Math.floorDiv(-d.height, 2));
        Point out = in.getLocation();
        out.translate(bounds.width, 0);

        input = new Rectangle(in, d);
        output = new Rectangle(out, d);
    }

    private void drawLines(Graphics g) {
        for (Line2D l : lines) {
            Point p = new Point((int) l.getX1(), (int) l.getY1());
            Point q = new Point((int) l.getX2(), (int) l.getY2());

            g.drawLine(p.x, p.y, q.x, q.y);
        }
    }

    public void drawPoles(Graphics g) {
        try {
            if (input == null || output == null) {
                throw new NullPointerException("Input or output is null.");
            }
            drawPoles(g, input, output);
        } catch (NullPointerException e) {
        }
    }

    public void drawPoles(Graphics g, Rectangle input, Rectangle output) {
        g.setColor(color);
        g.fillRect(input.x, input.y, input.width, input.height);
        g.fillRect(output.x, output.y, output.width, output.height);
        drawLines(g);
    }

    public static void wire(Component c, Component d) {
        Point output = c.getOutput();
        Point input = d.getInput();
        Line2D line = new Line2D.Double(input, output);
        c.addLine(line);
    }

    public void addLine(Line2D line) {
        this.lines.add(line);
    }

    public void drawText(Graphics g, String text, Rectangle context) {
        g.setColor(color);

        FontMetrics fm = g.getFontMetrics();

        /* rectangle from text */
        Rectangle fontBounds = fm.getStringBounds(text, g).getBounds();

        /* center in component */
        center(context, fontBounds);

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
                center(context, block);
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

    public void drawText(Graphics g, String text) {
        drawText(g, text, bounds);
    }

    public Rectangle getBounds() {
        return bounds.getBounds();
    }

    public Color getColor() {
        return color;
    }

    public void setLocation(Point p) {
        bounds.setLocation(p);
        fixPoles();
    }

    public void setSize(Dimension d) {
        bounds.setSize(d);
        fixPoles();
    }

    public Point getInput() {
        return new Point((int) input.getCenterX(), (int) input.getCenterY());
    }

    public Point getOutput() {
        return new Point((int) output.getCenterX(), (int) output.getCenterY());
    }

}
