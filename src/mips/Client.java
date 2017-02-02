/*
 * This is project to show how to mips work
 */
package mips;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import javax.swing.border.Border;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Bruno
 */
public class Client extends JFrame implements Runnable {

    private final Player player;
    private final ArrayList<Component> components;
    private BufferStrategy bs;
    private Thread appThread;
    private boolean running;

    public Client() throws HeadlessException {
        super();
        player = new Player();
        components = new ArrayList<Component>();
        addComponents();
    }
    
    private void addComponents() {
        ProgramCounter pc = new ProgramCounter(30, 40);
        components.add(pc);
    }
    
    protected void createAndShowGui() {
        GridBagLayout layout = new GridBagLayout();
        JPanel pane = new JPanel(layout);
        
        /* Canvas */
        Canvas canvas = new Canvas();
        canvas.setSize(400, 600);
        canvas.setBackground(Color.ORANGE);
        canvas.setIgnoreRepaint(true);
        pane.add(canvas);
        
        Canvas test = new Canvas();
        test.setSize(400,600);
        test.setBackground(Color.GREEN);
        canvas.setIgnoreRepaint(true);
        pane.add(test);
        
        /* JFrame components */
        getContentPane().add(pane);
        setTitle("Hey guys!");
        setIgnoreRepaint(true);
        pack();
        /* center view */
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        
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
                    for (Component component : components) {
                        component.render(g);
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
