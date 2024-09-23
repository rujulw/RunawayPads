
import javax.sound.sampled.*;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;

/**
 * The RunawayPads class creates a GUI with pads that plays Runaway by Kanye West specific keys are pressed.
 * It allows dynamic sound playback for buttons and keyboard bindings.
 * 
 * @author rujulw
 */
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

    /**
     * Creates a JButton that represents a pad. When clicked or its corresponding key is pressed, it plays a sound.
     * 
     * @param index the index of the sound file and key binding to associate with the button
     * @return the JButton instance representing the pad
     */
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

    /**
     * Plays the sound associated with the given index by loading and playing the corresponding .wav file.
     * 
     * @param index the index of the sound file to play
     */
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
