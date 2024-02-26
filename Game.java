import java.util.Scanner;
import java.io.Console;

public class Game {
    private int score;
    private LetterPool pool;
    private LetterBoard board;
    private final int[] possibleScores = new int[] {100, 500, 1000, 2000};

    /**
     * initializes and LetterPool and a LetterBoard, 
     * sets score to 0 and timer to the start time
     */
    public Game() {
        score = 0;
        pool = new LetterPool();
        board = new LetterBoard();
    }

    public void run(Scanner input) {
        int session = 0;
        while (session == 0) {
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("Score: " + score);
            System.out.println();
            System.out.println(board.viewBoard());
            System.out.println(pool.viewPool());
            String response = input.nextLine();
            if (response.length() == 1) {
                if (pool.has(response) != -1) {
                    board.put(pool.remove(pool.has(response)));
                }
            }
            else if (response.equals("end")) {
                session = 1;
            }
            else if (response.equals("submit")) {
                onClick();
            }
            else if (response.equals("del")) {
                if (board.getLength() > 0) {
                    pool.add(board.delete());
                }
            }
            /** 
            else if (input.nextLine().equals("end")) {
                session = 1;
            }
            else if (input.nextLine().equals("end")) {
                session = 1;
            }*/
        }
    }

    /** 
     * checks if word is valid using Board.isWord (if TRUE: 
     * give voice and text message for correct answer, increase 
     * score) (if FALSE: give voice and text message for
     * incorrect answer), returns letters to the LetterPool
     */
    private void onClick() {
        int result = board.isWord();
        if (result == 1) {
            System.out.println("+" + possibleScores[(board.getWord().length()) - 3]);
            score += possibleScores[(board.getWord().length()) - 3];
        }
        else if (result == 2) {
            System.out.println("Words must be 3 or more characters!");
        }
        else if (result == 3) {
            System.out.println("Word is incorrect!");
        }
        else {
            System.out.println("Error");
        }
        int temp = board.getLength();
        for (int i = 0; i < temp; i++) {
            pool.add(board.delete());
        }
    }

    public static void main(String[] args) {
        Thread inputThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                if (scanner.hasNext()) {
                    String input = scanner.next();
                    System.out.println("Input: " + input);
                }
            }
        });
        inputThread.start();

        // Perform other tasks in the main thread
        // ...

        // Wait for the input thread to finish (optional)
        try {
            inputThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
