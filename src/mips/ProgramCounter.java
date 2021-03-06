/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mips;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Bruno
 */
public class ProgramCounter extends Component {

    private static ProgramCounter pc;

    public static ProgramCounter getInstance() {
        if (pc == null) {
            pc = new ProgramCounter(new Rectangle(36, 48), Color.black);
        }

        return pc;
    }
    private int id;
    private final String TEXT;

    private ProgramCounter(Rectangle bounds, Color color) {
        super(bounds, color);
        TEXT = "PC";
    }

    public int getId() {
        return this.id;
    }

    @Override
    public void addPoles(Rectangle bounds) {
        addPoles(bounds, new Dimension(8, 8));
    }

    public void advanceInstruction() {
        if (Math.addExact(id, 4) < (Math.pow(2, 32) - 1)) {
            id += 4;
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(getColor());
        Rectangle r = getBounds();
        g.drawRect(r.x, r.y, r.width, r.height);
        drawText(g, TEXT);
        drawPoles(g);
    }
}
