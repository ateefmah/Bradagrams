import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;


public class Letter extends JButton {
    JButton letter;
    int index;
    LetterPool pool;
   
    /**
    * makes letter button
    */
    public Letter(LetterPool p, int i){
        pool = p;
        letter = new JButton(pool.getLetters()[index] + "");
        index = i;
        //System.out.println("WOWOWOOW" + pool.getLetters()[index]);
    }

   /**
    *  gets letter
    * @return letter
    */
    public JButton getLetter(){
        return letter;
    }
}
