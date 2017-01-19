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
 * This class is used to generate square in project
 * @author Bruno
 */
public class Square extends Rectangle implements IRenderable {
    private Color color = Color.RED;
    private static Rectangle bounds = null;
    
    /**
     * Get the value of color
     *
     * @return the value of color
     */
    public Color getColor() {
        return color;
    }
    
    public Square() {
        super();
    }
    
    public Square(DimensionSameSize side, Point point) {
        super(point, side);
    }

    public static void setBoundsCanvas(Rectangle bounds) 
            throws InstantiationException{
        if(Square.bounds == null){
            Square.bounds = bounds;
        } else {
            throw new InstantiationException();
        }
    }

    public Square(DimensionSameSize side) {
        super(side);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
}
