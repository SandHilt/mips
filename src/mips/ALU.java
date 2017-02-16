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

    public ALU(Rectangle r, Color c) {
        super(r, c);
        name = "ALU";
        transform = new AffineTransform();
        polygon = new Polygon();
        rectToPol(r);
    }

    public ALU() {
        this(new Rectangle(64, 64), Color.black);
    }

    private void rectToPol(Rectangle r) {
        polygon.reset();

        int[] xpoints = {
            r.x, r.x + r.width, r.x + r.width, r.x
        };

        int[] ypoints = {
            r.y, r.y, r.y + r.height, r.y + r.height
        };

        int npoints = 4;

        for (int i = 0, j = npoints; i < j; i++) {
            int x = xpoints[i];
            int y = ypoints[i];

            polygon.addPoint(x, y);
        }
    }

    @Override
    public void setSize(Dimension d) {
        super.setSize(d);

        Rectangle r = getBounds();
        r.setSize(d);
        rectToPol(r);
        resetTransform();
    }

    @Override
    public void setLocation(Point p) {
        super.setLocation(p);

        Rectangle r = getBounds();
        r.setLocation(p);
        rectToPol(r);
        resetTransform();
    }
    
    private void resetTransform() {
        transform = new AffineTransform();
        Rectangle r = polygon.getBounds();
        transform.rotate(Math.toRadians(45), r.getCenterX(), r.getCenterY());
    }

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
