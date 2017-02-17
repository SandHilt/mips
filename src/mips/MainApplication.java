/*
 * This is project to show how to mips work
 */
package mips;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Rectangle;
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
public class MainApplication extends JFrame implements Runnable {

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

    private final Player player;
    private final ArrayList<Component> components;
    private BufferStrategy bs;
    private Thread appThread;
    private boolean running;
    private final Rectangle application;

    public MainApplication() throws HeadlessException {
        super();
        player = new Player();
        components = new ArrayList<Component>();
        application = new Rectangle(800, 600);

        addComponents();
    }

    private void addComponents() {

        ALU alu = new ALU();
        Rectangle half = application.getBounds();
        half.width = Math.round(half.width * 1 / 2);
        Component.center(half, alu);
        components.add(alu);

        DataMemory dm = DataMemory.getInstance();
        half = application.getBounds();
        half.width /= 2;
        half.x = half.width;
        Component.center(half, dm);
        components.add(dm);

        InstructionMemory im = InstructionMemory.getInstance();
        Component.center(application, im);
        components.add(im);

        half = application.getBounds();
        half.width /= 4;
        ProgramCounter pc = ProgramCounter.getInstance();
        Component.center(half, pc);
        components.add(pc);

        im.wire(dm);
        pc.wire(alu, Component.InputPole.SECOND);
    }

    protected void createAndShowGui() {
        /* Canvas */
        Canvas canvas = new Canvas();
        canvas.setSize(application.getSize());
        canvas.setIgnoreRepaint(true);

        /* JFrame components */
        getContentPane().add(canvas);
        setTitle("Illusion");
        setIgnoreRepaint(true);
        pack();
        /* center view */
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setSize(application.getSize());

        canvas.createBufferStrategy(2);
        bs = canvas.getBufferStrategy();

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
                    g.clearRect(0, 0, getWidth(), getHeight());

                    render(g);

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
        } while (bs.contentsLost());
    }

    private void render(Graphics g) {
        g.setColor(Color.black);
        g.drawString("MIPS", 5, 15);
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

}
