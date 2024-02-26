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

public class ButtonFrame2 extends JFrame implements ActionListener {
    LetterPool pool;
    LetterPool originalPool;
    LetterBoard board;
    String[] letters;
    String[] originalLetters;
    JButton[] poolButtons;
    JButton[] boardButtons;
    Stack<String> reverse;
    private final int[] possibleScores = new int[] { 100, 500, 1000, 2000 };
    public int score =0 ;
    public boolean gameOver = false;
    ArrayList<String> submittedWords;
    JTextField field;
    JButton buttonR1;
    JButton buttonR2;
    JButton buttonR3;
    JButton buttonR4;
    JButton buttonR5;
    JButton buttonR6;
    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    JButton button5;
    JButton button6;
    JButton delete;
    JButton submit;
    JLabel wordPoint;
    JLabel counterLabel;
    JLabel scoreCounter;
    Timer timer;
    int second;
    Image image;
    int player;
    public ButtonFrame2(){

    }
    public ButtonFrame2(int p) {
        // Create JFrame
        pool = new LetterPool();
        letters = pool.getLetters();
        player = p;
        
    }
    public String[] getLetters(){
        return this.letters;
    }
    /**
     * recreates the GUI with a different image in the background
     * @param s current score
     * @param p current LetterPool
     * @param b current LetterBoard
     * @param image new image
     * @throws InterruptedException
     */
    public void rebuild(int s, LetterPool p, LetterBoard b, String image ) throws InterruptedException{
        setTitle("Bradagrams");
        setSize(750, 996);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        if (player == 1)
            setLocation(0, (int)height/15);
        if (player == 2)
            setLocation((int) (width/1.5), (int)height/5);
        if (player == 3)
            setLocationRelativeTo(null);

 
        try {
            final Image backgroundImage = javax.imageio.ImageIO.read(new File(image + ".jpg"));
            setContentPane(new JPanel(new BorderLayout()) {
                @Override public void paintComponent(Graphics g) {
                    g.drawImage(backgroundImage, 0, 0, null);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // ImageIcon image = new ImageIcon("fulklogo.png");

        // getContentPane().setBackground(Color.green);

        // Initalize variables
        
        pool = p;
        letters = pool.getLetters();
        board = b;
        originalLetters = letters.clone();
    
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(counterLabel);
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        buttonPanel.add(button5);
        buttonPanel.add(button6);
        buttonPanel.add(submit);

        // Add pool button panel to the frame
        add(buttonPanel, BorderLayout.SOUTH);

        // Create panel for board buttons
        JPanel buttonPanelR = new JPanel();
        buttonPanelR.setLayout(new FlowLayout());
        buttonPanelR.add(scoreCounter);
        buttonPanelR.add(buttonR1);
        buttonPanelR.add(buttonR2);
        buttonPanelR.add(buttonR3);
        buttonPanelR.add(buttonR4);
        buttonPanelR.add(buttonR5);
        buttonPanelR.add(buttonR6);
        buttonPanelR.add(delete);

        // Add board button panel to the frame
        add(buttonPanelR, BorderLayout.NORTH);
        add(wordPoint);
        wordPoint.setLocation(0,0);

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button6.addActionListener(this);
        delete.addActionListener(this);
        submit.addActionListener(this);

        setVisible(true);

    }
    /**
     * creates a GUI interface for the Bradagrams game. Sets the background image to the intro image. Sets positions for LetterPool, LetterBoard, submit, and delete JButtons.
     * Sets point per word, timer, and score counter JLabels. Starts the timer and adds ActionListeners to each button.
     * @param inputLetter int that chooses whether to make a new LetterPool or an already existing one (testing)
     * @param inputLetterArray already existing LetterPool (testing)
     * @throws InterruptedException
     */
    public void build(int inputLetter, String[] inputLetterArray ) throws InterruptedException{
        setTitle("Bradagrams");
        setSize(750, 996);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        if (player == 1)
            setLocation(0, (int)height/5);
        if (player == 2)
            setLocation((int) (width/1.5), (int)height/5);
        if (player == 3)
            setLocationRelativeTo(null);

        try {
            final Image backgroundImage = javax.imageio.ImageIO.read(new File("intro.jpg"));
            setContentPane(new JPanel(new BorderLayout()) {
                @Override public void paintComponent(Graphics g) {
                    g.drawImage(backgroundImage, 0, 0, null);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // ImageIcon image = new ImageIcon("fulklogo.png");

        // getContentPane().setBackground(Color.green);

        // Initalize variables
        
        if (inputLetter == 0) {
            pool = new LetterPool();
            letters = pool.getLetters();
        } else {
            pool = new LetterPool(inputLetterArray);
            letters = inputLetterArray;
        }

        originalLetters = letters.clone();
        board = new LetterBoard();
        score = 0;
        second = 46;
        submittedWords = new ArrayList<String>();
        // Create pool buttons
        button1 = new JButton(letters[0]);
        button1.setActionCommand("button1");
        button2 = new JButton(letters[1]);
        button2.setActionCommand("button2");
        button3 = new JButton(letters[2]);
        button3.setActionCommand("button3");
        button4 = new JButton(letters[3]);
        button4.setActionCommand("button4");
        button5 = new JButton(letters[4]);
        button5.setActionCommand("button5");
        button6 = new JButton(letters[5]);
        button6.setActionCommand("button6");
        // Create delete button
        delete = new JButton("DELETE");
        delete.setActionCommand("delete");
        // Create submit button
        submit = new JButton("SUBMIT");
        submit.setActionCommand("submit");
        // Create point / word button
        wordPoint = new JLabel();
        wordPoint.setForeground(Color.WHITE);
        wordPoint.setFont(new Font("DialogInput", Font.BOLD, 50));
        wordPoint.setHorizontalAlignment(JLabel.CENTER);
        // Create timer button
        counterLabel = new JLabel("GO");
        counterLabel.setBounds(300, 230, 200, 100);
        counterLabel.setHorizontalAlignment(JLabel.LEFT);
        // Create score counter
        scoreCounter = new JLabel("SCORE: 0");

        // Create board buttons
        buttonR1 = new JButton("");
        buttonR2 = new JButton("");
        buttonR3 = new JButton("");
        buttonR4 = new JButton("");
        buttonR5 = new JButton("");
        buttonR6 = new JButton("");
        // Creates array of pool buttons
        poolButtons = new JButton[6];
        poolButtons[0] = button1;
        poolButtons[1] = button2;
        poolButtons[2] = button3;
        poolButtons[3] = button4;
        poolButtons[4] = button5;
        poolButtons[5] = button6;
        // Creates array of board buttons
        boardButtons = new JButton[6];
        boardButtons[0] = buttonR1;
        boardButtons[1] = buttonR2;
        boardButtons[2] = buttonR3;
        boardButtons[3] = buttonR4;
        boardButtons[4] = buttonR5;
        boardButtons[5] = buttonR6;

        // Create panel for pool buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(counterLabel);
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        buttonPanel.add(button5);
        buttonPanel.add(button6);
        buttonPanel.add(submit);

        // Add pool button panel to the frame
        add(buttonPanel, BorderLayout.SOUTH);

        // Create panel for board buttons
        JPanel buttonPanelR = new JPanel();
        buttonPanelR.setLayout(new FlowLayout());
        buttonPanelR.add(scoreCounter);
        buttonPanelR.add(buttonR1);
        buttonPanelR.add(buttonR2);
        buttonPanelR.add(buttonR3);
        buttonPanelR.add(buttonR4);
        buttonPanelR.add(buttonR5);
        buttonPanelR.add(buttonR6);
        buttonPanelR.add(delete);

        // Add board button panel to the frame
        add(buttonPanelR, BorderLayout.NORTH);
        add(wordPoint);
        wordPoint.setLocation(0,0);

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button6.addActionListener(this);
        delete.addActionListener(this);
        submit.addActionListener(this);

        simpleTimer();
        timer.start();
        setVisible(true);

        
    }


    public static void main(String[] args) throws InterruptedException {
        String[] str = {"a", "d", "w", "g", "o", "l"};
        ButtonFrame2 b = new ButtonFrame2();
        b.build(0,str);
        
    }

    //@Override
    /**
     * sets the actions that occur for each button press on the screen
     */
    public void actionPerformed(ActionEvent e) {

        String act = e.getActionCommand();
        if (act.equals("submit")) {
            int result = board.isWord();
            if (result == 1) {
                if (submittedWords.contains(board.getWord())){
                    wordPoint.setText("Already submitted");
                    clearBoard();
                    reset(board);

                    try {
                        rebuild(getScore(), pool, board, "nofulk");
                    } catch (InterruptedException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    try {
                        playAudio("wrong");
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
                else{
                wordPoint.setText("+" + possibleScores[(board.getWord().length()) - 3]);
                score += possibleScores[(board.getWord().length()) - 3];
                try {
                    rebuild(getScore(), pool, board, "yesfulk");
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                try {
                    playAudio("right");
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                scoreCounter.setText("SCORE: " + score);
                submittedWords.add(board.getWord());
                clearBoard();
                reset(board);

                }
            } else if (result == 2) {
                wordPoint.setText("Input at least 3 chars!");
                clearBoard();
                reset(board);

                try {
                    rebuild(getScore(), pool, board, "nofulk");
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                try {
                    playAudio("wrong");
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            } else if (result == 3) {
                wordPoint.setText("Word is incorrect!");
                clearBoard();
                reset(board);

                try {
                    rebuild(getScore(), pool, board, "nofulk");
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                try {
                    playAudio("wrong");
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            } else {
                wordPoint.setText("Word found already");
                clearBoard();
                reset(board);

                try {
                    rebuild(getScore(), pool, board, "nofulk");
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                try {
                    playAudio("wrong");
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            int temp = board.getLength();
            for (int i = 0; i < temp; i++) {
                pool.add(board.delete());
                reset(board);
            }
        }
        if (act.equals("delete")) {
            String ltr = board.delete();
            // System.out.println("LETTER:" + ltr);
            // for (String i : board.getBoard()){
            // System.out.println("board text: " + i);
            // }
            if (ltr != null) {
                int x = pool.add(ltr);
                poolButtons[x].setText(ltr);
                // for (JButton b : poolButtons) {
                // if (b.getText().equals("")) {
                // b.setText(ltr);
                // break;
                // }
                // }
                clearBoard();
                deleteMover(board);
                deleteMover(board);
            }
        }
        if (act.equals("button1")) {
            if (button1.getText().equals("")) {
                return;
            }
            board.put(pool.remove(0));
            try {
                playAudio(button1.getText());
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            try {
                rebuild(getScore(), pool, board, "thinkfulk");
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            button1.setText("");
            wordMover(board);

            
        } else if (act.equals("button2")) {
            if (button2.getText().equals("")) {
                return;
            }
            board.put(pool.remove(1));
            try {
                playAudio(button2.getText());
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            try {
                rebuild(getScore(), pool, board, "thinkfulk");
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            button2.setText("");
            wordMover(board);
        } else if (act.equals("button3")) {
            if (button3.getText().equals("")) {
                return;
            }
            board.put(pool.remove(2));
            try {
                playAudio(button3.getText());
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            try {
                rebuild(getScore(), pool, board, "thinkfulk");
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            button3.setText("");
            wordMover(board);
        } else if (act.equals("button4")) {
            if (button4.getText().equals("")) {
                return;
            }
            board.put(pool.remove(3));
            try {
                playAudio(button4.getText());
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            try {
                rebuild(getScore(), pool, board, "thinkfulk");
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            button4.setText("");
            wordMover(board);
        } else if (act.equals("button5")) {
            if (button5.getText().equals("")) {
                return;
            }
            board.put(pool.remove(4));
            try {
                playAudio(button5.getText());
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            try {
                rebuild(getScore(), pool, board, "thinkfulk");
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            button5.setText("");
            wordMover(board);
        } else if (act.equals("button6")) {
            if (button6.getText().equals("")) {
                return;
            }
            board.put(pool.remove(5));
            try {
                playAudio(button6.getText());
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            try {
                rebuild(getScore(), pool, board, "thinkfulk");
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            button6.setText("");
            wordMover(board);
        }
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
    /**
     * moves letter from LetterPool buttons to the LetterBoard buttons. Also removes it from the ArrayList and adds it to the stack.
     * @param brd LetterBoard to change
     */
    private void wordMover(LetterBoard brd) {
        Stack<String> temp = new Stack<String>();
        for (String i : brd.getBoard()) {
            temp.push(i);
            System.out.println("button text: " + i);
        }
        for (JButton b : boardButtons) {
            if (b.getText().equals("")) {
                b.setText(temp.pop());
                return;
            }
        }
    }
    /**
     * moves letter from LetterBoard buttons to the LetterPool buttons. Also removes it from the stack and adds it back to the ArrayList.
     * @param brd LetterBoard to change
     */
    private void deleteMover(LetterBoard brd) {
        Stack<String> temp = new Stack<String>();
        for (String i : brd.getBoard()) {
            temp.push(i);
            System.out.println("button text: " + i);
        }
        int s = temp.size();
        for (int i = s - 1; i >= 0; i--) {
            JButton b = boardButtons[i];
            if (!temp.isEmpty()) {
                b.setText(temp.pop());
            } else {
                b.setText(""); // Clear the remaining buttons if there are no more letters in the stack
            }
        }
    }
    /**
     * resets the board on screen as well as in the LetterPool and LetterBoard
     * @param b LetterBoard to change
     */
    private void reset(LetterBoard b) {
        for (int i = 0; i < b.getLength();) {
            String s = b.delete();
            int k = pool.add(s);
            poolButtons[k].setText(s);
        }
    }
    /**
     * clears the LetterBoard buttons
     */
    private void clearBoard() {
        for (JButton b : boardButtons) {
            b.setText("");
        }
    }
    /**
     * clears the LetterPool buttons
     */
    private void clearPool() {
        for (JButton b : poolButtons) {
            b.setText("");
        }
    }
    /**
     * creates a countdown timer. When it hits zero, it stops the player from continuing.
     */
    private void simpleTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                second--;
                counterLabel.setText("Timer: " + second + "");
                if (second == 0) {
                    counterLabel.setText("GAME OVER");
                    try {
                        playAudio("game over");
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    gameOver = true;
                    clearBoard();
                    clearPool();
                    timer.stop();
                    clearBoard();
                    clearPool();

                    
                   // System.exit(ABORT);
                }
            }
        });
    }

    private void changeImage(String filename){
        try {
            final Image backgroundImage = javax.imageio.ImageIO.read(new File(filename));
            setContentPane(new JPanel(new BorderLayout()) {
                @Override public void paintComponent(Graphics g) {
                    g.drawImage(backgroundImage, 0, 0, null);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * plays a .wav audio file
     * @param letter the name of the audio file
     * @throws UnsupportedAudioFileException
     * @throws IOException
     * @throws LineUnavailableException
     */
    private void playAudio(String letter) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        File file = new File("audio_wav/" + letter + ".wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);

        clip.start();
    }   

    public int getScore(){
        return score;
    }

    public Timer getTimer(){
        return timer;
    }
    /**
     * makes a winner screen and plays the winner audio
     * @throws InterruptedException
     */
    public void getWin() throws InterruptedException{
        rebuild(getScore(), pool, board, "yesfulk");
        counterLabel.setText("YOU WIN!");
        try {
            playAudio("win");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * makes a tie screen and plays the tie audio
     * @throws InterruptedException
     */
    public void getTie() throws InterruptedException{
        rebuild(getScore(), pool, board, "thinkfulk");
        counterLabel.setText("YOU TIED!");
        try {
            playAudio("tie");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * makes a loser screen and plays the loser audio
     * @throws InterruptedException
     */
    public void getLoss() throws InterruptedException{
        rebuild(getScore(), pool, board, "nofulk");
        counterLabel.setText("YOU LOSE!");
        try {
            playAudio("loss");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
