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
public class ProgramCounter extends Component {
    private static ProgramCounter pc;
    private int id;
    private final String TEXT;

    private ProgramCounter(Rectangle bounds, Color color) {
        super(bounds, color);
        TEXT = "PC";
    }
    
    public static ProgramCounter getInstance() {
        if (pc == null) {
            pc = new ProgramCounter(new Rectangle(36, 48), Color.black);
        }
        
        return pc;
    }

    public int getId() {
        return this.id;
    }

    public void advanceInstruction() {
        this.id++;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(getColor());
        Rectangle r = getBounds();
        g.drawRect(r.x, r.y, r.width, r.height);
        drawText(g, TEXT);
    }
}
