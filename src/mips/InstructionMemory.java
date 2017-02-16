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
public class InstructionMemory extends Component {

    private static InstructionMemory comp;

    public static InstructionMemory getInstance() {
        if (comp == null) {
            comp = new InstructionMemory(new Rectangle(95, 90), Color.black);
        }

        return comp;
    }

    private final String TEXT;

    private InstructionMemory(Rectangle bounds, Color color) {
        super(bounds, color);
        TEXT = "Instruction\nMemory";
    }

    @Override
    public void render(Graphics g) {
        g.setColor(getColor());
        Rectangle r = getBounds();
        g.drawRect(r.x, r.y, r.width, r.height);
        drawText(g, TEXT);
    }

}
