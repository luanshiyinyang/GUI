package screensaver;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Frame extends JFrame {


    private int Width;
    private int Height;

    public Frame(String title, int width, int height){
        super(title);
        this.Width = width;
        this.Height =height;
        myPanel panel = new myPanel();
        setContentPane(panel);
        pack();
        setSize(800,800);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    public int getWidth(){return Width;}
    public int getHeight(){return Height;}
    private Circle[] circles;
    public void render(Circle[] circles){
        this.circles = circles;
        repaint();
    }


    private class myPanel extends JPanel{
        public myPanel(){
            super(true);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
            Operation.setStrokeWidth(g2d,1);
            Operation.setColor(g2d, Color.cyan);
            for(Circle circle: circles){
                Operation.drawCircle(g2d, circle.x, circle.y, circle.getradius());}
        }


        @Override
        public Dimension getPreferredSize(){
            return new Dimension(Width, Height);
        }
    }
}
