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
public class RegisterFile extends Component {
    public enum OPCODES {
        ADD, SUB, ADDI, AND, OR, NOR, ANDI, ORI, SLL, SRL, LW, SW
    }

    public RegisterFile(Rectangle bounds, Color color) {
        super(bounds, color);
    }

    @Override
    public void render(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
