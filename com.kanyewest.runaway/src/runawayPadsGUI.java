import javax.swing.*;
import java.awt.*;

public class runawayPadsGUI {

    public static void main(String[] args) {
        // Create a frame for the GUI
        JFrame frame = new JFrame("Runaway - Kanye West");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        
        // Create a main panel with a 4x4 grid layout
        JPanel gridPanel = new JPanel(new GridLayout(4, 4, 10, 10)); // 10px gaps between components

        // Set border for the grid panel
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // 20px outer border

        // Add buttons or labels to each grid cell
        for (int i = 1; i <= 16; i++) {
            JButton button = new JButton();
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Border for each cell
            gridPanel.add(button);
        }

        // Add the grid panel to the frame
        frame.add(gridPanel);
        
        // Make the frame visible
        frame.setVisible(true);
    }
}
