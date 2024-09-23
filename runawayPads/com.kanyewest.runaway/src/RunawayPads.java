import javax.sound.sampled.*;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;

public class RunawayPads {

    // Array of file paths for the .wav files corresponding to each button
    private String[] soundFiles = {
        "/lib/soundFiles/piano1.wav", 
        "/lib/soundFiles/piano2.wav", 
        "/lib/soundFiles/piano3.wav", 
        "/lib/soundFiles/piano4.wav", 
        "/lib/soundFiles/piano5.wav", 
        "/lib/soundFiles/piano6.wav", 
        "/lib/soundFiles/runawayInstrumental.wav"
    };

    public JButton createPad(int index) {
        JButton button = new JButton("Pad " + (index + 1));
        button.setFocusable(false); // Remove focus to avoid conflicting with keyboard events
        
        // Add ActionListener to play the sound when clicked
        button.addActionListener(e -> playSound(index));

        // Add key binding for the button press (keyboard shortcuts)
        String key = "key" + index;
        button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_1 + index, 0), key);
        button.getActionMap().put(key, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound(index);
            }
        });

        return button;
    }

    // Method to play the sound
    private void playSound(int index) {
        try {
            // Load the sound file using getResource()
            URL soundURL = getClass().getResource(soundFiles[index]);
            if (soundURL == null) {
                System.out.println("Sound file not found: " + soundFiles[index]);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Play the sound
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
