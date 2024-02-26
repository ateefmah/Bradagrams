import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.*;

public class SwingDemo {
    public static final void main(final String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        Scanner scanner = new Scanner(System.in);
        
        File file = new File("audio_wav/a.wav");
        AudioInputStream aStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();

        clip.open(aStream);

        clip.start();

        String response = scanner.next();
    }
}