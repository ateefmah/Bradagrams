import java.util.*;
import java.io.*;
import java.nio.*;


public class LetterBoard {
     private Stack<String> board = new Stack<String>();

     // TODO IMPLEMENT COUNTER TO DUPLICATE WORDS

     /**
      * initializes the board stack as empty
      */
     public LetterBoard() {
     }

     public int getLength() {
        return board.size();
     }

     /**
      * adds the given letter string to the end of the stack
      */
     public void put(String letter) {
        board.push(letter);
     }

     /**
      * uses java stack's pop function to remove the end of
      * the stack and return it
      * @return
      */
     public String delete() {
        return board.pop();
     }

     /**
      * creates a String with the letters in the stack and checks 
      * it against a dictionary API, returns true if it is a valid 
      * word and false if it isn’t (anything below 3 characters 
      * returns false, even if it is a word or not)
      * @return if word or not
      */
     public int isWord() {
        String testWord = getWord();
        boolean dictionaryCheck = wordCheck(testWord);
        System.out.println(testWord);
        if (dictionaryCheck == true) {
            if (testWord.length() < 3) {
                return 2;
            }
            else {
                return 1;
            }
        }
        else {
            return 3;
        }
     }

     private boolean wordCheck(String word) {
        try {
            BufferedReader in = new BufferedReader(new FileReader("words_alpha.txt"));
            String str;
            while ((str = in.readLine()) != null) {
                if (str.equals(word)) {
                    return true;
                }
            }
            in.close();
        } catch (IOException e) {
        }
        return false;
     }

    /**
     * gives the values of the board stack to display on the UI
     * @return board
     */
    public String viewBoard() {
        String result = "";
        for (int i = 0; i < board.size(); i++) {
            if (i == 0) {
                result = result + board.get(i);
            }
            else {
                result = result + " " + board.get(i);
            }
        }
        if (board.size() < 6) {
            int size = (6 - board.size());
            for (int i = 0; i < (6 - board.size()); i++) {
                if (board.size() == 0 && i == 0) {
                    result = result + "_";
                }
                else {
                    result = result + " _";
                }
            }
        }
        return result;
    }

    /**
     * gets word on board
     * @return word
     */
    public String getWord() {
        Stack<String> temp = new Stack<String>();
        String testWord = "";
        for (String i : board) {
            temp.push(i);
        }
        for (String i : temp) {
            testWord = testWord + i;
        }
        return testWord;
    }

    /**
     *  gets stack board
     * @return board
     */
    public Stack<String> getBoard(){
        return board;
    }
}
