package bookstore.homepage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GridLayoutManager {

    public static void setupGridLayout(JFrame frame) {
        // Set layout to a BorderLayout
        frame.setLayout(new BorderLayout());

        // Create a panel for the title
        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding to the panel
        JLabel titleLabel = new JLabel("<html><b><i style='font-size:20px;'>Exploring World: Discover the features of Our Bookstore!</i></b></html>");
        titlePanel.add(titleLabel);

        // Add the title panel to the frame at the top
        frame.add(titlePanel, BorderLayout.NORTH);

        // Create a panel for the grid layout
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(3, 3)); // Adjusted to 3 rows and 3 columns for demonstration

        // Image paths for demonstration
        String[] imagePaths = {
                "resources/images/chemistry.png", "resources/images/dog.png", "resources/images/facts.png", "resources/images/Liane.png", "resources/images/person.png",
                "resources/images/show.png", "resources/images/Bugs.png"
        };

        // Static text for each grid cell
        String[] staticTexts = {
                "A BEST BOOK OF THE YEAR: The New York Times,\r\n"
                + "Chemist Elizabeth Zott is not your average woman.\r\n"
                + "In fact, Elizabeth Zott would be the first to point out\r\n"
                + "that there is no such thing as an average woman.",
                
                "Beginner Books are fun, funny, and easy to read!\r\n"
                + "Launched by Dr. Seuss in 1957 with the publication\r\n"
                + "of The Cat in the Hat, this beloved early reader series\r\n"
                + "motivates children to read on their own by using simple\r\r"
                +"words with illustrations",
                
                "This fascinating book brings you a total of 1272 facts\r\n"
                + "covering every subject from science to sport, sea creatures\r\n"
                + "to stars, and kings and queens to reptiles and amphibians\r\n"
                + "in a wild ride of extraordinary information, guaranteed to\r\n"
                + "surprise and amaze even the smartest kids!",
                
                "From Liane Moriarty, the number one New York Times\r\n"
                + "best-selling author of Big Little Lies and Nine Perfect\r\n"
                + "Strangers, comes Apples Never Fall, an audiobook that\r\n"
                + "looks at marriage, siblings, and how the people we love\r\n"
                + "the most can hurt us the deepest.",
                
                "In Person of Interest, cold-case detective and bestselling\r\n"
                + "author J. Warner Wallace describes his own personal investigative\r\n"
                + "journey from atheism to Christianity as he carefully sifts through\r\n"
                + "the evidence from history alone, without relying on the New Testament.",
                
                "Great Experience. Great Value.\r\n"
                + "Enjoy a great reading experience\r\n"
                + "when you buy the Kindle edition of\r\n"
                + "this book. Learn more about Great on\r\n"
                + "Kindle, available in select categories.",
                
                "Grab your magnifying glass!\r\n"
                + "Find your field guide!\r\n"
                + "And come hop, hide, swim, and glide\r\n"
                + "through this buggy backyard world!"
        };

        // Create panels for each grid cell
        int numOfCells = Math.min(imagePaths.length, staticTexts.length); // Ensure to iterate until the minimum of both arrays
        for (int i = 0; i < numOfCells; i++) {
            GridCellPanel panel = new GridCellPanel(imagePaths[i], staticTexts[i]);
            gridPanel.add(panel);
        }

        // Add the eighth grid cell with a JTextArea
        JTextArea textArea = new JTextArea("Enter your text here");
        textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Set border
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        gridPanel.add(new JScrollPane(textArea)); // Add JTextArea inside a JScrollPane

        // Add the grid panel to the frame
        frame.add(gridPanel, BorderLayout.CENTER);

        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
