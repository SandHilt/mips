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
import java.awt.Rectangle;

/**
 * This class is used to generate square in project
 * @author Bruno
 */
public class Square extends Rectangle implements IRenderable {

    public Square() {
        super();
    }

    public Square(int side, Point point) {
        super(point, new Dimension(side, side));
    }

    public Square(int side) {
        super(side, side);
    }

    public void render(Graphics g, Color c) {
        g.setColor(c);
        g.fillRect(x, y, width, height);
    }
    
    @Override
    public void render(Graphics g) {
        render(g, Color.RED);
    }
}
