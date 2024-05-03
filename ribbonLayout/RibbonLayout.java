package bookstoreRound2.ribbonLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import bookstoreRound2.bookDetails.BookSearchHandler;

public class RibbonLayout implements ActionListener {
	JFrame frame;
    JMenuBar menuBar;
    JMenu home, edit, search, cart, checkout, trade, help, admin, logout;
    JMenuItem cutItem, copyItem, pasteItem, selectAllItem;

    public RibbonLayout() {
        frame = new JFrame();
        
        // Initialize search field
        search = new JMenu("Search");
        search.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                BookSearchHandler.handleSearch();
            }

            @Override
            public void menuDeselected(MenuEvent e) {}

            @Override
            public void menuCanceled(MenuEvent e) {}
        });
        
        
        cutItem = new JMenuItem("Cut");
        copyItem = new JMenuItem("Copy");
        pasteItem = new JMenuItem("Paste");
        selectAllItem = new JMenuItem("Select All");

        cutItem.addActionListener(this);
        copyItem.addActionListener(this);
        pasteItem.addActionListener(this);
        selectAllItem.addActionListener(this);

        // Menu Bar
        menuBar = new JMenuBar();

        // add menu in the menu bar
        home = new JMenu("Home");
        edit = new JMenu("Edit");
        //search = new JMenu("Search");
        cart = new JMenu("Cart");
        checkout = new JMenu("Checkout");
        trade = new JMenu("Trade");
        help = new JMenu("Help");
        admin = new JMenu("Admin");
        logout = new JMenu("Logout");

        // add items inside edit menu
        edit.add(cutItem);
        edit.add(copyItem);
        edit.add(pasteItem);
        edit.add(selectAllItem);

        // add menus to the menu bar with separators
        addMenuItemWithSeparator(home);
        addMenuItemWithSeparator(edit);
        addMenuItemWithSeparator(search);
        addMenuItemWithSeparator(cart);
        addMenuItemWithSeparator(checkout);
        addMenuItemWithSeparator(trade);
        addMenuItemWithSeparator(help);
        addMenuItemWithSeparator(admin);
        addMenuItemWithSeparator(logout);

        frame.setJMenuBar(menuBar);
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensure the application exits when the window is closed
        frame.setVisible(true);
    }

    // Method to add menu item with separator
    private void addMenuItemWithSeparator(JMenu menu) {
        menuBar.add(menu);
        menuBar.add(new JSeparator(SwingConstants.VERTICAL)); // Add a vertical separator
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

    /*
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cutItem) {
            textArea.cut();
        }
        if (e.getSource() == pasteItem) {
            textArea.paste();
        }
        if (e.getSource() == copyItem) {
            textArea.copy();
        }
        if (e.getSource() == selectAllItem) {
            textArea.selectAll();
        }
    } */
}

