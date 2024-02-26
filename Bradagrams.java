import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.util.*;
import javax.sound.sampled.*;
import java.awt.image.BufferedImage;

public class Bradagrams extends ButtonFrame implements ActionListener {
    JButton button;
    Timer timer;
    int second;
    int p;
    ButtonFrame2 b;

    public Bradagrams(int player) {
        setTitle("Bradagrams");
        setSize(750, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setLocationRelativeTo(null);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        if (player == 1)
            setLocation(0, (int) height / 10);
        if (player == 2)
            setLocation((int) (width / 1.5), (int) height / 10);
        if (player == 3)
            setLocationRelativeTo(null);

        //second = 46;
        p = player;

        button = new JButton("Ready Player " + player + " ?");
        button.setActionCommand("ready");

        add(button);
        button.addActionListener(this);

        setVisible(true);
    }

    public static void main(String[] args) {
        Bradagrams bg = new Bradagrams(1);
        Bradagrams bg2 = new Bradagrams(2);
        //while (!bg2.getFinished()){
        //System.out.println("score 1 : " + bg.getScore());
        //System.out.println("score 2 : " + bg2.getScore());
        //}
        System.out.println(bg.getScore() == bg2.getScore());
        if (bg.getScore() > bg2.getScore()){
            bg.scoreCounter.setText("Player 1 WINS!");
        }
        else{
            bg2.scoreCounter.setText("Player 2 WINS!");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String act = e.getActionCommand();
        if (act.equals("ready")) {
            b = new ButtonFrame2(p);
            try {
                b.build(0, new String[1]);
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            // t = b.getTimer();
            b.show();
            dispose();
        }
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    private void simpleTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                second--;
                counterLabel.setText("Timer: " + second + "");
                if (second == 0) {
                    //dispose();
                    //b.show();
                    //counterLabel.setText("GAME OVER");
                    //wordPoint.setText("You got " + score + " points");
                    timer.stop();
                    finished = true;
                }
            }
        });
    }
}