import java.util.ArrayList;
import java.lang.Math;

public class LetterPool {
    private String[] letters = new String[6];
    private ArrayList<Integer> emptyIndices = new ArrayList<Integer>();

    /** randomly generates 4 consonants and 2 vowels (ordered C V C C V C)
         *  and initializes the letters array with the 6 letters generated, with 
         * the exception of some letters (banned letters: j, q, w, v, g, x, z, y) due 
         * to limited words using them 
         * the letters are: a, b, c, d, e, f, h, i, k, l, m, n, o, p, r, s, t, u
        */
    public LetterPool() {
        initPool();
    }

    public LetterPool(String[] s) {
        initPool(s);
    }

    /**
     * initializes a pool of letters
     */
    private void initPool() {
        String[] consonants = new String[] {"b", "c", "d", "f", "h", "k", "l", "m", "n", "p", "r", "s", "t"};
        String[] vowels = new String[] {"a", "e", "i", "o", "u"};
        for (int i = 0; i < 6; i++) {
            if (i == 0 || i == 2 || i == 3 || i == 5) {
                letters[i] = consonants[(int)(Math.random() * 13)];
            }
            else if (i == 1 || i == 4) {
                letters[i] = vowels[(int)(Math.random() * 5)];
            }
        }
    }
    private void initPool(String[] s) {
        letters = s;
    }

    /**
     * returns letter at the given index, while replacing the letter with an
     * empty space and adds the index to the emptyIndices ArrayList
     * @param pickedIndex index of letter to remove
     * @return letter that is removed
     */
    public String remove(int pickedIndex) {
        String temp = letters[pickedIndex];
        emptyIndices.add(pickedIndex);
        letters[pickedIndex] = "_";
        return temp;
    }

/**
     * adds the given letter to the first index available, found
     * using the emptyIndices ArrayList
     * @param givenLetter letter to add
     */
    public int add(String givenLetter) {
        int index = emptyIndices.get(0);
        letters[emptyIndices.get(0)] = givenLetter;
        emptyIndices.remove(0);
        return index;
    }

    public int has(String givenLetter) {
        for (int i = 0; i < letters.length; i++) {
            if (letters[i].equals(givenLetter)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * gets letters
     * @return letters
     */
    public String[] getLetters(){
        return letters;
    }

    /**
     * gives the values of the letters array to display on the UI
     * @return letters
     */
    public String viewPool() {
        return letters[0] + " " + letters[1] + " " + letters[2] + " " + letters[3] + " " + letters[4] + " " + letters[5] ;
    }
}