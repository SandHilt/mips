/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mips;

import java.awt.Dimension;

/**
 *
 * @author Bruno
 */
public class DimensionSameSize extends Dimension {

    /**
     *
     */
    public DimensionSameSize() {
        super();
    }

    /**
     *
     * @param d
     */
    public DimensionSameSize(DimensionSameSize d) {
        super(d);
    }

    /**
     *
     * @param size
     */
    public DimensionSameSize(int size) {
        super(size, size);
    }
   
    /**
     *
     * @param size
     */
    public void setSize(int size){
        super.setSize(size, size);
    }
    
    /**
     *
     * @param size
     */
    public void setSize(double size){
        super.setSize(size, size);
    }
    
    private boolean isSameSize(int x, int y) {
        if (x < 0 || y < 0) {
            throw new NegativeIntegerException();
        } else if ( x != y ) {
            throw new DifferentValueException();
        } else {
            return true;
        }
    }

    @Override
    public void setSize(int width, int height) {
        if(isSameSize(width, width)) {
            super.setSize(width, height);
        }
    }

    @Override
    public void setSize(double width, double height) {
        int w = (int) Math.ceil(width);
        int h = (int) Math.ceil(height);
        this.setSize(w, h);
    }
    
    @Override
    public void setSize(Dimension d) {
        if(isSameSize(d.height, d.width)) {
            super.setSize(d);
        }
    }
}
