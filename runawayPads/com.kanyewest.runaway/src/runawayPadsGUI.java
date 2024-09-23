import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * The runawayPadsGUI class creates the GUI interface for the Runaway sound pads, featuring custom colors, borders, and button press effects.
 * 
 * @author rujulw
 */
public class runawayPadsGUI {

    // Define custom color constants
    private static final Color MATTE_BLACK = new Color(17, 18, 18); // Custom black color for grid borders
    private static final Color GRAY_BUTTON = new Color(25, 27, 28); // Custom gray color for buttons
    private static final Color PRESSED_COLOR = new Color(64, 67, 68); // Darker gray when button is pressed
    private static final Color PINK_BORDER = new Color(255, 105, 180); // Custom pink for even grids
    private static final Color BLUE_BORDER = new Color(173, 216, 230); // Custom light blue for odd grids

    /**
     * Main method to initialize the GUI window and add sound pads to a 4x4 grid layout with custom borders and colors.
     * 
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        // Create the frame and increase its size
        JFrame frame = new JFrame("Runaway");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 750); // Increased window size

        // Create the main panel with a 4x4 grid layout and 25px gaps for the matte black background to show
        JPanel gridPanel = new JPanel(new GridLayout(4, 4, 25, 25)); // 25px gap between buttons

        // Set a thick black border around the grid and between the buttons
        gridPanel.setBorder(BorderFactory.createLineBorder(MATTE_BLACK, 30)); // Outer border
        gridPanel.setBackground(MATTE_BLACK); // Background of gridPanel to matte black

        // Create and add RunawayPads buttons to the grid
        RunawayPads pads = new RunawayPads();
        for (int i = 0; i < 16; i++) {
            JButton button = pads.createPad(i); // Create buttons/pads
            button.setText(""); // Remove the Pad number display
            button.setBackground(GRAY_BUTTON); // Set background color to custom gray
            button.setOpaque(true); // Make sure the background is painted
            button.setContentAreaFilled(true); // Allow background to be filled

            // Calculate row and column based on index
            int row = i / 4; // Row number (0 to 3)
            int col = i % 4; // Column number (0 to 3)

            // Apply border based on row and column rules
            if (row % 2 == 0) {
                // Odd rows (0 and 2): Blue for odd columns, Pink for even columns
                if (col % 2 == 0) {
                    button.setBorder(BorderFactory.createLineBorder(BLUE_BORDER, 3)); // Blue border for odd grid
                } else {
                    button.setBorder(BorderFactory.createLineBorder(PINK_BORDER, 3)); // Pink border for even grid
                }
            } else {
                // Even rows (1 and 3): Pink for odd columns, Blue for even columns
                if (col % 2 == 0) {
                    button.setBorder(BorderFactory.createLineBorder(PINK_BORDER, 3)); // Pink border for odd grid
                } else {
                    button.setBorder(BorderFactory.createLineBorder(BLUE_BORDER, 3)); // Blue border for even grid
                }
            }

            // Add mouse listener for pressed effect
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    button.setBackground(PRESSED_COLOR); // Darker gray when pressed
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    button.setBackground(GRAY_BUTTON); // Revert to original gray color after release
                }
            });

            // Simulate pressed effect when button is programmatically clicked via doClick()
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Trigger visual pressed effect
                    button.setBackground(PRESSED_COLOR);

                    // Create a small delay before reverting back to the original color
                    Timer timer = new Timer(100, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            button.setBackground(GRAY_BUTTON); // Revert to gray
                        }
                    });
                    timer.setRepeats(false); // Run only once
                    timer.start(); // Start the timer
                }
            });

            gridPanel.add(button);
        }

        // Set the black matte border between buttons by setting grid panel background
        gridPanel.setBackground(MATTE_BLACK); // Matte black between the buttons (gaps)

        // Add the grid panel to the frame
        frame.add(gridPanel);
        frame.setVisible(true);
    }
}