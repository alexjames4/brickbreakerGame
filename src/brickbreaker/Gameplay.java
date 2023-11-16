package brickbreaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false; //initially false so game doesn't play on load
    private int score = 0; //initially 0 will increment as game progresses
    private int totalBricks = 21;

    //need time for setting the speed of the ball
    private Timer timer;
    private int delay = 0;

    private int playerX = 310; //starting position of the slider
    private int ballposX = 120; //starting pos of ball
    private int ballposY = 350; //starting pos of ball
    private int ballXdir = -1; //ball x direction
    private int ballYdir = -2; //ball y direction


    //constructor
    public Gameplay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {
        //background
        g.setColor(Color.black);
        g.fillRect(1,1, 692, 592);

        //borders
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(684, 0, 3, 592);

        //paddle
        g.setColor(Color.green);
        g.fillRect(playerX, 550, 100, 8);

        //ball
        g.setColor(Color.orange);
        g.fillOval(ballposX, ballposY, 20, 20);

        g.dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(play) {
            //intersection with paddle
            if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
                ballYdir = -ballYdir;
            }

            ballposX += ballXdir;
            ballposY += ballYdir;

            //these if statements allow the ball to rebound off of the borders
            if(ballposX < 0) {
                ballXdir = -ballXdir;
            }
            if(ballposX > 670) {
                ballXdir = -ballXdir;
            }
            if(ballposY < 0) {
                ballYdir = -ballYdir;
            }
        }

        repaint(); //redraws all the graphics
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX >= 600) {
                playerX = 600; //this keeps the slider in the game screen
            } else {
                moveRight();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX < 10) {
                playerX = 10; //this keeps the slider in the game screen
            } else {
                moveLeft();
            }
        }
    }

    public void moveRight() {
        play = true;
        playerX += 20;
    }
    public void moveLeft() {
        play = true;
        playerX -= 20;
    }


}
