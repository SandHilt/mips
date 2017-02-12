/*
 * This is project to show how to mips work
 */
package mips;

import com.sun.javafx.geom.Vec2d;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Bruno
 */
public class MainApplication extends JFrame implements Runnable {

    private final Player player;
    private final ArrayList<Component> components;
    private BufferStrategy bs;
    private Thread appThread;
    private boolean running;
    private final Dimension HALF_DIMENSION;

    private volatile boolean clear;
    private volatile boolean pause;

    public MainApplication() throws HeadlessException {
        super();
        player = new Player();
        components = new ArrayList<Component>();
        HALF_DIMENSION = new Dimension(800, 600);

        clear = false;
        pause = false;

        addComponents();
    }

    private void addComponents() {
        Rectangle bnd = new Rectangle(400, 210, 30, 20);
        Color col = Color.magenta;

        ProgramCounter pc = new ProgramCounter(bnd, col);
        components.add(pc);
    }

    protected void createAndShowGui() {
        GridBagLayout layout = new GridBagLayout();
        JPanel pane = new JPanel(layout);

        /* Canvas */
        Canvas canvas = new Canvas();
        canvas.setSize(HALF_DIMENSION);
//        canvas.setBackground(Color.ORANGE);
        canvas.setIgnoreRepaint(true);
        pane.add(canvas);

//        Canvas test = new Canvas();
//        test.setSize(HALF_DIMENSION);
//        test.setBackground(Color.GREEN);
//        canvas.setIgnoreRepaint(true);
//        pane.add(test);

        /* JFrame components */
        getContentPane().add(pane);
        setTitle("Illusion");
        setIgnoreRepaint(true);
        pack();
        /* center view */
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        canvas.createBufferStrategy(2);
        bs = canvas.getBufferStrategy();

        canvas.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case (KeyEvent.VK_A):
                        clear = true;
                        break;

                    case (KeyEvent.VK_ENTER):
                        pause = !pause;
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        appThread = new Thread(this);
        appThread.start();
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            appLoop();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }
    }

    private void appLoop() {
        do {
            do {
                Graphics g = null;
                try {
                    g = bs.getDrawGraphics();
                    if (clear == true) {
                        g.clearRect(0, 0, getWidth(), getHeight());
                        clear = false;
                    }
                    //render(g);
                    if (pause == false) {
                        for (Component component : components) {
                            component.render(g);

                            final int speed = new Random().nextInt(50);
                            final double angle = 2 * Math.PI * new Random().nextDouble();
                            final Point osc = new Point();

                            osc.x = (int) (speed * Math.sin(angle));
                            osc.y = (int) (speed * Math.cos(angle));

                            Rectangle cenario = new Rectangle(HALF_DIMENSION);

                            if (cenario.contains(component.getBounds())) {
                                component.getBounds().translate(osc.x, osc.y);
                                component.changeColor();
                            } else {
                                Point origin = component.getOrigin();
                                component.getBounds().setLocation(origin);
                            }
                        }
                    }
                } finally {
                    if (g != null) {
                        g.dispose();
                    }
                }
            } while (bs.contentsRestored());
            bs.show();
        } while (bs.contentsLost());
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
        final MainApplication cl = new MainApplication();

        cl.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cl.onWindowClosing();
            }
        });

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                cl.createAndShowGui();
            }
        });
    }

}
