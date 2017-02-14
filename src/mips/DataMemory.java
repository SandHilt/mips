/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mips;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Bruno
 */
public class DataMemory extends Component {
    private static DataMemory comp;
    private final String TEXT;
    
    private DataMemory(Rectangle bounds, Color color) {
        super(bounds, color);
        TEXT = "Data Memory";
    }
    
    public static DataMemory getInstance() {
        if (comp == null) {
            comp = new DataMemory(new Rectangle(90, 80), Color.black);
        }
        
        return comp;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(getColor());
        Rectangle r = getBounds();
        g.drawRect(r.x, r.y, r.width, r.height);
        drawText(g, TEXT);
    }
    
}
