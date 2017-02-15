/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mips;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;

/**
 *
 * @author Bruno
 */
public class ALU extends Component {

    private final Polygon shape;

    public ALU(Rectangle r, Color c) {
        super(r, c);

        shape = new Polygon();
        rectToPol(r);
    }
    
    private void rectToPol(Rectangle r) {
        shape.reset();
        
        int[] xpoints = {
            r.x, r.x + r.width, r.x + r.width, r.x
        };

        int[] ypoints = {
            r.y, r.y, r.y + r.height, r.y + r.height
        };
        
        int npoints = 4;
        
        for(int i = 0, j = npoints; i < j; i++) {
            int x = xpoints[i];
            int y = ypoints[i];
            
            shape.addPoint(x, y);
        }
    }

    @Override
    public void setSize(Dimension d) {
        super.setSize(d);
        
        Rectangle r = getShape();
        r.setSize(d);
        rectToPol(r);
    }

    @Override
    public void setLocation(Point p) {
        super.setLocation(p);
        
        Rectangle r = getShape();
        r.setLocation(p);
        rectToPol(r);
    }
    
    @Override
    public void render(Graphics g) {
        g.setColor(getColor());
        g.fillPolygon(shape);
    }
}
