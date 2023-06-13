package View;

import javax.swing.*;
import java.awt.*;

public class StartPageFrame extends JFrame  {//implements ActionListener

    private final int centerJLabel = SwingConstants.CENTER;

    private final Container backgroundContainer;
    private Font menuFont;
    private Font headLineFont;
    private Font tableContentFont;
    private Font bottomFont;


    private final JPanel menuPanel;
    private final JPanel mainDisplayPanel;
    private final JPanel mealTablePanel;
    private final JPanel bottomPanel;

    //private JComboBox<LocalDate> chooseDate;
    //private LocalDate[] chooseDateArray; //TODO: get future dates from Controller/Model

    private JButton showHistory;
    private JButton showNutrients;
    private JButton placeHolder;

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

    private void setFontOf(JComponent component, Font font) {
        component.setFont(font);
    }

    public StartPageFrame() {

        backgroundContainer = getContentPane();

        this.setTitle("MENSA FOOD TRACKER");
        this.initialize();
        this.setFont();
        this.getContentPane().setBackground(new Color(0xf5efdc));

        backgroundContainer.setLayout(new BorderLayout(10, 0));


        menuPanel = new JPanel(new GridLayout(0, 3));
        setFontOf(menuPanel, menuFont);

        mainDisplayPanel = new JPanel(new BorderLayout());
        setFontOf(mainDisplayPanel, tableContentFont);

        mealTablePanel = new JPanel(new GridLayout(15, 3));
        setFontOf(mealTablePanel, tableContentFont);

        bottomPanel = new JPanel(new GridLayout(0, 2));
        setFontOf(bottomPanel, bottomFont);

        //chooseDate = new JComboBox<>(chooseDateArray);
        showHistory = new JButton("Essenshistorie");
        showNutrients = new JButton("Kumulierte Nährwertangabe");
        placeHolder = new JButton("Platzhalter");

        todaysMenu = new JLabel("Tagesmenu", centerJLabel);
        setFontOf(todaysMenu, headLineFont);
        mealName = new JLabel("Name");
        mealPrice = new JLabel("Preis");
        mealLine = new JLabel("Linie");
        lastUpdate = new JLabel("Letztes Update: ", SwingConstants.LEFT);
        dateAndTimeOfLastUpdate = new JLabel("", SwingConstants.RIGHT); //TODO: get last update from controller
        author = new JLabel("Autor: Johanna Krickow", centerJLabel);


        backgroundContainer.add(menuPanel, BorderLayout.NORTH);
        menuPanel.add(placeHolder);
        menuPanel.add(showHistory);
        menuPanel.add(showNutrients);


        backgroundContainer.add(mainDisplayPanel, BorderLayout.CENTER);

        mainDisplayPanel.add(todaysMenu, BorderLayout.NORTH);
        mainDisplayPanel.add(mealTablePanel, BorderLayout.CENTER);

        //TODO: why displayed row after row not col after col
        mealTablePanel.add(mealName);
        mealTablePanel.add(mealPrice);
        mealTablePanel.add(mealLine);








        backgroundContainer.add(bottomPanel, BorderLayout.SOUTH);



    }
}
