/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mips;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

/**
 *
 * @author Bruno
 */
public class ALU extends Component {

    private final Polygon polygon;
    private final String name;
    private AffineTransform transform;

    public enum InputPole {
        RD, RT
    };

    @Override
    public Point getInput() {
        return null;
    }
    
    /**
     *
     * @param r
     * @param c
     */
    public ALU(Rectangle r, Color c) {
        super(null, c);
        name = "ALU";
        polygon = new Polygon();
        rectToPol(r);
        resetTransform();
    }

    /**
     *
     */
    public ALU() {
        this(new Rectangle(80, 80), Color.black);
    }
    
    private void rectToPol(Rectangle r) {
        polygon.reset();

        int[] xpoints = {
            r.x,
            r.x + r.width,
            r.x + r.width,
            r.x,
            r.x,
            r.x + r.width * 3 / 8,
            r.x + r.width * 3 / 8,
            r.x
        };
        int[] ypoints = {
            r.y,
            r.y + r.height / 4,
            r.y + r.height * 3 / 4,
            r.y + r.height,
            r.y + r.height * 3 / 4,
            r.y + r.height * 5 / 8,
            r.y + r.height * 3 / 8,
            r.y + r.height / 4
        };

        int npoints = Math.min(xpoints.length, ypoints.length);

        for (int i = 0, j = npoints;
                i < j;
                i++) {
            int x = xpoints[i];
            int y = ypoints[i];

            polygon.addPoint(x, y);
        }
    }

    /**
     *
     * @return
     */
    @Override
    public Rectangle getBounds() {
        return polygon.getBounds();
    }

    /**
     *
     * @param d
     */
    @Override
    public void setSize(Dimension d) {
        Rectangle r = polygon.getBounds();
        r.setSize(d);
        rectToPol(r);
        resetTransform();
    }

    /**
     *
     * @param p
     */
    @Override
    public void setLocation(Point p) {
        Rectangle r = polygon.getBounds();
        r.setLocation(p);
        rectToPol(r);
        resetTransform();
    }

    private void resetTransform() {
        transform = new AffineTransform();
        Rectangle r = polygon.getBounds();
//        transform.rotate(Math.toRadians(45), r.getCenterX(), r.getCenterY());
    }

    /**
     *
     * @param g
     * @param text
     */
    @Override
    public void drawText(Graphics g, String text) {
        Rectangle r = polygon.getBounds();
        r.translate(Math.floorDiv(r.width * 3, 8), 0);
        r.width = Math.floorDiv(r.width * 5, 8);
        super.drawText(g, text, r);
    }

    /**
     *
     * @param g
     */
    @Override
    public void render(Graphics g) {
        g.setColor(getColor());

        Shape transformed = transform.createTransformedShape(polygon);

        Graphics2D g2 = (Graphics2D) g.create();

        g2.draw(transformed);
        g2.dispose();

        drawText(g, name);
    }
}
