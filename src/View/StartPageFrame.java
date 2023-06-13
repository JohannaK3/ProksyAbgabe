package View;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class StartPageFrame extends JFrame{

    private final int centerJLabel = SwingConstants.CENTER;

    private Container backgroundContainer;
    private Font menuFont;
    private Font headLineFont;
    private Font tableContentFont;
    private Font bottomFont;


    private JPanel menuPanel;
    private JPanel mealTablePanel;
    private JPanel bottomPanel;

    private JComboBox<LocalDate> chooseDate; //TODO: instantiate
    private LocalDate[] chooseDateArray; //TODO: get future dates from Controller/Model

    private JButton showHistory;
    private JButton showNutrients;

    private JLabel todaysMenu;
    private JLabel mealName;
    private JLabel mealPrice;
    private JLabel mealLine;
    private JLabel lastUpdate;
    private JLabel dateAndTimeOfLastUpdate;
    private JLabel author;

    private void initialize() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setExtendedState(MAXIMIZED_BOTH); //TODO: really the way to maximize to full screen?
        setResizable(true);
        setLocationRelativeTo(null);
    }

    private void setFont() {

        menuFont = new Font("Arial", Font.PLAIN, 20);
        headLineFont = new Font("Arial", Font.PLAIN, 30);
        tableContentFont = new Font("Arial", Font.PLAIN, 15);
        bottomFont = new Font("Arial", Font.PLAIN, 10);
    }

    public StartPageFrame() {

        this.setTitle("MENSA FOOD TRACKER");
        this.initialize();

        backgroundContainer = getContentPane();
        backgroundContainer.setLayout(new GridLayout(6, 1, 1, 0));

        this.setFont();

        menuPanel = new JPanel(new GridLayout(1, 3));
        mealTablePanel = new JPanel(new GridLayout(10, 3));
        bottomPanel = new JPanel(new GridLayout(1, 2));

        showHistory = new JButton("Essenshistorie");
        showNutrients = new JButton("Kumulierte Nährwertangabe");

        todaysMenu = new JLabel("Tagesmenu", centerJLabel);
        mealName = new JLabel("Name", centerJLabel);
        mealPrice = new JLabel("Preis", centerJLabel);
        mealLine = new JLabel("Linie", centerJLabel);
        lastUpdate = new JLabel("Letztes Update: ", SwingConstants.LEFT);
        dateAndTimeOfLastUpdate = new JLabel("", SwingConstants.RIGHT); //TODO: get last update from controller
        author = new JLabel("Autor: Johanna Krickow", centerJLabel);
    }
}
