package homework.lab12;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainForm {
    private JFrame mainFrame;
    private JPanel controlPanel;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenu submenu;
    private JMenuItem menuItem;
    private JPanel mainPanel;

    public MainForm() {
        prepateGUI();
    }

    private void prepateGUI() {
        mainFrame = new JFrame("Bills Application");
        mainFrame.setSize(640, 480);

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        menuBar = new JMenuBar();

        menu = new JMenu("File");
        menuBar.add(menu);

        menuItem = new JMenuItem("Open");
        menu.add(menuItem);
        menu.addSeparator();
        menuItem = new JMenuItem("Exit");
        menuItem.addActionListener(e -> mainFrame.dispose());
        menu.add(menuItem);

        menu = new JMenu("Commands");
        menuBar.add(menu);

        menuItem = new JMenuItem("Append data");
        menu.add(menuItem);

        menuItem = new JMenuItem("Append data, compress every record");
        menu.add(menuItem);

        menuItem = new JMenuItem("Clear all data");
        menu.add(menuItem);

        submenu = new JMenu("Clear data by key");
        menu.add(submenu);
        menuItem = new JMenuItem("numberHouse");
        submenu.add(menuItem);
        menuItem = new JMenuItem("numberApartment");
        submenu.add(menuItem);
        menuItem = new JMenuItem("owner");
        submenu.add(menuItem);
        menuItem = new JMenuItem("paymentDate");
        submenu.add(menuItem);

        submenu = new JMenu("Print data unsorted");
        menu.add(submenu);
        menuItem = new JMenuItem("numberHouse");
        submenu.add(menuItem);
        menuItem = new JMenuItem("numberApartment");
        submenu.add(menuItem);
        menuItem = new JMenuItem("owner");
        submenu.add(menuItem);
        menuItem = new JMenuItem("paymentDate");
        submenu.add(menuItem);

        submenu = new JMenu("Print data sorted (by field)");
        menu.add(submenu);
        menuItem = new JMenuItem("numberHouse");
        submenu.add(menuItem);
        menuItem = new JMenuItem("numberApartment");
        submenu.add(menuItem);
        menuItem = new JMenuItem("owner");
        submenu.add(menuItem);
        menuItem = new JMenuItem("paymentDate");
        submenu.add(menuItem);

        submenu = new JMenu("Print data reverse sorted (by field)");
        menu.add(submenu);
        menuItem = new JMenuItem("numberHouse");
        submenu.add(menuItem);
        menuItem = new JMenuItem("numberApartment");
        submenu.add(menuItem);
        menuItem = new JMenuItem("owner");
        submenu.add(menuItem);
        menuItem = new JMenuItem("paymentDate");
        submenu.add(menuItem);

        submenu = new JMenu("Find records by key");
        menu.add(submenu);
        menuItem = new JMenuItem("numberHouse");
        submenu.add(menuItem);
        menuItem = new JMenuItem("numberApartment");
        submenu.add(menuItem);
        menuItem = new JMenuItem("owner");
        submenu.add(menuItem);
        menuItem = new JMenuItem("paymentDate");
        submenu.add(menuItem);

        submenu = new JMenu("Find records > key");
        menu.add(submenu);
        menuItem = new JMenuItem("numberHouse");
        submenu.add(menuItem);
        menuItem = new JMenuItem("numberApartment");
        submenu.add(menuItem);
        menuItem = new JMenuItem("owner");
        submenu.add(menuItem);
        menuItem = new JMenuItem("paymentDate");
        submenu.add(menuItem);

        submenu = new JMenu("Find records < key");
        menu.add(submenu);
        menuItem = new JMenuItem("numberHouse");
        submenu.add(menuItem);
        menuItem = new JMenuItem("numberApartment");
        submenu.add(menuItem);
        menuItem = new JMenuItem("owner");
        submenu.add(menuItem);
        menuItem = new JMenuItem("paymentDate");
        submenu.add(menuItem);

        menu = new JMenu("Help");
        menuBar.add(menu);

        mainFrame.setJMenuBar(menuBar);
    }

    private void showForm() {
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        MainForm form = new MainForm();
        form.showForm();
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
