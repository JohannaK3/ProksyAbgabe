package View;

import javax.swing.*;
import java.awt.*;

public class StartPageFrame extends JFrame{

    private Container backgroundContainer;
    private Font menuFont;
    private Font headLineFont;
    private Font tableContentFont;
    private Font bottomFont;


    private JPanel menuPanel;
    private JPanel mealTablePanel;
    private JPanel bottomPanel;

    private JMenuBar menuBar;
    private JMenu dateMenu;
    private JMenuItem todayMenuItem;

    private JButton showHistory;
    private JButton showNutrients;

    private JLabel date;
    private JLabel lastUpdatedText;
    private JLabel dateAndTimeOfLastUpdate;
    private JLabel author;


    private void initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setExtendedState(MAXIMIZED_BOTH); //TODO: really the way to maximize to full screen?
        setResizable(true);
        setLocationRelativeTo(null);
    }
    public StartPageFrame() {

        this.initialize();
        backgroundContainer.add(menuPanel);
    }
}
