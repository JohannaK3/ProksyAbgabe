package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class StartPageFrame extends JFrame  {//implements ActionListener

    private final int centerJLabel = SwingConstants.CENTER;
    private final Insets basicInsets = new Insets(5, 5, 5, 5);

    private Container backgroundContainer;
    private Font menuFont;
    private Font headLineFont;
    private Font tableContentFont;
    private Font bottomFont;


    private JPanel menuPanel;
    private JPanel mealTablePanel;
    private JPanel bottomPanel;

    //private JComboBox<LocalDate> chooseDate;
    //private LocalDate[] chooseDateArray; //TODO: get future dates from Controller/Model

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
        //getContentPane().setBackground(Color.red);
    }

    private void setFont() {

        menuFont = new Font("Arial", Font.PLAIN, 20);
        headLineFont = new Font("Arial", Font.PLAIN, 30);
        tableContentFont = new Font("Arial", Font.PLAIN, 15);
        bottomFont = new Font("Arial", Font.PLAIN, 10);
    }

    //private void setFontOf(Font font) {

   // }

    public StartPageFrame() {

        backgroundContainer = getContentPane();

        this.setTitle("MENSA FOOD TRACKER");
        this.initialize();
        this.setFont();
        this.getContentPane().setBackground(new Color(0xf5efdc));

        backgroundContainer.setLayout(new GridBagLayout());

        GridBagConstraints backgroundConstraints = new GridBagConstraints();
        backgroundConstraints.insets = basicInsets;

        menuPanel = new JPanel(new GridBagLayout());
        GridBagConstraints menuPanelConstraints = new GridBagConstraints();
        menuPanelConstraints.insets = basicInsets;

        mealTablePanel = new JPanel(new GridBagLayout());
        GridBagConstraints meaLTablePanelConstraints = new GridBagConstraints();
        meaLTablePanelConstraints.insets = basicInsets;

        bottomPanel = new JPanel(new GridBagLayout());
        GridBagConstraints bottomPanelConstraints = new GridBagConstraints();
        bottomPanelConstraints.insets = basicInsets;

        //chooseDate = new JComboBox<>(chooseDateArray);
        showHistory = new JButton("Essenshistorie");
        showNutrients = new JButton("Kumulierte Nährwertangabe");

        todaysMenu = new JLabel("Tagesmenu", centerJLabel);
        mealName = new JLabel("Name", centerJLabel);
        mealPrice = new JLabel("Preis", centerJLabel);
        mealLine = new JLabel("Linie", centerJLabel);
        lastUpdate = new JLabel("Letztes Update: ", SwingConstants.LEFT);
        dateAndTimeOfLastUpdate = new JLabel("", SwingConstants.RIGHT); //TODO: get last update from controller
        author = new JLabel("Autor: Johanna Krickow", centerJLabel);

        //menuPanel.add(chooseDate, 1, 1);
        menuPanel.add(showHistory);
        menuPanel.add(showNutrients);
        backgroundContainer.add(menuPanel);

        mealTablePanel.add(todaysMenu);
        mealTablePanel.add(mealName);
        backgroundContainer.add(mealTablePanel);

    }
}
