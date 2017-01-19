/*
 * This is project to show how to mips work
 */
package mips;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Bruno
 */
public class Client extends JFrame implements Runnable {

    private final Player player;
    private final ArrayList<Square> squares;
    private BufferStrategy bs;
    private Thread appThread;
    private boolean running;
    
    public Client() {
        player = new Player();
        squares = new ArrayList<>();

        DimensionSameSize dimensionComponents = new DimensionSameSize(32);
        Point pointComponents = new Point(16,16);
        
        Color[] colors = {Color.RED, Color.GREEN, Color.ORANGE, Color.MAGENTA};

        for (int i = 0; i < 4; i++) {
            squares.add(new Square(dimensionComponents, pointComponents));
            pointComponents.translate(16 * (i % 2), 16 * i / 2);
        }
    }

    protected void createAndShowGui() {
        /* Canvas */
        Canvas canvas = new Canvas();
        canvas.setSize(800, 600);
        canvas.setBackground(Color.CYAN);
        canvas.setIgnoreRepaint(true);
        
        /* JFrame components */
        getContentPane().add(canvas);
        setTitle("Hey guys!");
        setIgnoreRepaint(true);
        pack();
        /* center view */
        setLocationRelativeTo(null);
        setVisible(true);
        
        canvas.createBufferStrategy(2);
        bs = canvas.getBufferStrategy();
        
        appThread = new Thread(this);
        appThread.start();
    }
  
    @Override
    public void run() {
        running = true;
        while(running) {
            appLoop();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {}
        }
    }
    
    private void appLoop() {
        do{
            do {
                Graphics g = null;
                try {
                    g = bs.getDrawGraphics();
                    g.clearRect(0, 0, getWidth(), getHeight());
                    //render(g);
                    for (Square square : squares) {
                        square.render(g);
                    }
                } finally {
                    if (g != null) {
                        g.dispose();
                    }
                }
            } while (bs.contentsRestored());
            bs.show();
        } while(bs.contentsLost());
    }
    private void render(Graphics g) {
        //g.setColor(Color.red);
        //g.drawString(square.toString(), 0, 0);
    }
    
    /**
     * To close window.
     */
    protected void onWindowClosing() {
        try {
            running = false;
            appThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.exit(0);
    }

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final Client cl = new Client();
        
        cl.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cl.onWindowClosing();
            }
        });
        
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                cl.createAndShowGui();
            }
        });
    }

}
