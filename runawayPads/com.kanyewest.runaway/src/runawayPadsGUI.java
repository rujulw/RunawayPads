import javax.swing.*;
import java.awt.*;


/**
 * The runawayPadsGUI class creates the GUI interface for the Runaway sound pads, featuring custom colors, borders, and button press effects.
 * 
 * @author rujulw
 */
public class runawayPadsGUI {

    /**
     * Main method to initialize the GUI window and add sound pads to a 4x4 grid layout with custom borders and colors.
     * 
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        // Create a frame for the GUI
        JFrame frame = new JFrame("4x4 Grid with Borders");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        
        // Create a main panel with a 4x4 grid layout
        JPanel gridPanel = new JPanel(new GridLayout(4, 4, 10, 10)); // 10px gaps between components

        // Set border for the grid panel
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // 20px outer border

        // Add buttons or labels to each grid cell
        for (int i = 1; i <= 16; i++) {
            JButton button = new JButton("Cell " + i);
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Border for each cell
            gridPanel.add(button);
        }

        // Add the grid panel to the frame
        frame.add(gridPanel);
        
        // Make the frame visible
        frame.setVisible(true);
    }
}

