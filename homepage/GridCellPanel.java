package bookstore.homepage;

import javax.swing.*;
import java.awt.*;

public class GridCellPanel extends JPanel {
    private JLabel imageLabel;
    private JTextPane commentPane;

    public GridCellPanel(String imagePath, String staticText) {
        setLayout(new BorderLayout());

        // Add empty panel to create space from top
        JPanel topSpacePanel = new JPanel();
        add(topSpacePanel, BorderLayout.NORTH);

        // Import image and add to the center
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
        imageLabel = new JLabel(imageIcon);
        add(imageLabel, BorderLayout.CENTER);

        // Add static text to the right with HTML formatting
        JPanel textPanel = new JPanel(new BorderLayout());
        commentPane = new JTextPane();
        commentPane.setContentType("text/html"); // Set content type to HTML
        commentPane.setEditable(false); // Make it not editable
        commentPane.setText("<html>" + staticText + "</html>");
        textPanel.add(commentPane, BorderLayout.CENTER);

        add(textPanel, BorderLayout.SOUTH);
    }
}