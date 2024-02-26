import javax.swing.*;
import java.awt.*;

public class GameGUI{
// TODO: add variables
//LetterRow row;
//Timer time;


public GameGUI(){
    // TODO: Finish contstructor
}

public static void main(String args[]){
    //Creating the Frame
    JFrame frame = new JFrame("Bradagrams");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1000, 900);
    //frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
    //Adding Components to the frame.
    Letter l = new Letter(new LetterPool(), 0);
    frame.add(l);
    frame.setVisible(true);
 }
    
}