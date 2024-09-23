import javax.sound.sampled.*;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

/**
 * The RunawayPads class creates a GUI with pads that play sound files when clicked or when specific keys are pressed.
 * It allows dynamic sound playback for buttons and keyboard bindings.
 * 
 * @author rujulw
 */
public class RunawayPads {
    // Array of file paths for the .wav files corresponding to each button
    private String[] soundFiles = {
        "soundFiles/piano1.wav",
        "soundFiles/piano2.wav",
        "soundFiles/piano3.wav",
        "soundFiles/piano4.wav",
        "soundFiles/piano5.wav",
        "soundFiles/piano6.wav",
        "soundFiles/piano7.wav",
        "soundFiles/piano8.wav",
        "soundFiles/runawayInstrumental.wav",
        "soundFiles/performingInstrumental.wav",
        "soundFiles/lookatcha.wav"
    };

    // Array of key bindings corresponding to each button (A to M)
    private char[] keyBindings = {
        'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Z', 'X', 'C', 'V', 'B', 'N', 'M'
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

        // Add key binding for the button press (keyboard shortcuts using A-M)
        if (index < keyBindings.length) {
            char key = keyBindings[index];
            String actionKey = "key" + key;

            // Bind the key to the button press
            button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key), actionKey);
            button.getActionMap().put(actionKey, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    button.doClick(); // Simulate the button click
                }
            });
        }

        return button;
    }

    /**
     * Plays the sound associated with the given index by loading and playing the corresponding .wav file.
     * 
     * @param index the index of the sound file to play
     */
    // Method to play the sound
    public void playSound(int index) {
        try {
            // Load the sound file from the file system (using absolute path)
            File soundFile = new File(soundFiles[index]);
            if (!soundFile.exists()) {
                System.out.println("Sound file not found: " + soundFiles[index]);
                return;
            }

            // Create a new clip for each sound play
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

            // Add a LineListener to close the clip when it finishes
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close(); // Close the clip when done
                }
            });
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the array of file paths for the sound files.
     * 
     * @return the array of sound file paths
     */
    // Getter methods
    public String[] getSoundFiles() {
        return soundFiles;
    }

    /**
     * Gets the array of key bindings for the pads.
     * 
     * @return the array of key bindings
     */
    public char[] getKeyBindings() {
        return keyBindings;
    }
}