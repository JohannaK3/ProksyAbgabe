package View;

import Control.Controller;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

public class StartPageFrame extends JFrame  {//implements ActionListener

    private Controller controller;

    private final int centerJLabel = SwingConstants.CENTER;

    private final Container backgroundContainer;
    private LocalDate currentLocalDate = LocalDate.now();
    private java.util.Date currentDate;
    private LocalDate spinnerEndDate = currentLocalDate.plusDays(7);

    private SpinnerDateModel dateSpinnerModel;
    private JSpinner.DateEditor dateSpinnerEditor;

    private Font menuFont;
    private Font headLineFont;
    private Font tableContentFont;
    private Font bottomFont;


    private final JPanel backgroundPanel;
    private final JPanel backgroundBorderPanel;
    private final JPanel menuPanel;
    private final JPanel dateSpinnerPanel;
    private final JPanel mainDisplayPanel;
    private final JPanel mealTablePanel;
    private final JPanel bottomPanel;
    private final JPanel lastUpdatePanel;

    private JButton showHistoryButton;
    private JButton showNutrientsButton;
    private JButton getMenuForSelectedDayButton;
    private JButton addButton;
    private JButton removeButton;

    private JSpinner dateSpinner;
    public JSpinner getDateSpinner() {
        return dateSpinner;
    }

    private JLabel emptyLabel;
    private JLabel selectDateInSpinnerLabel;
    private JLabel todaysMenuLabel;
    private JLabel selectedDateLabel;
    private JLabel mealNameLabel;
    private JLabel mealPriceLabel;
    private JLabel mealLineLabel;
    private JLabel lastUpdateLabel;
    private JLabel placeHolder2;
    private JLabel dateAndTimeOfLastUpdateLabel;
    private JLabel authorLabel;

    private void initialize() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        //setExtendedState(MAXIMIZED_BOTH); //TODO: decide pack() or this method
        setResizable(true);
        setLocationRelativeTo(null);
    }

    private void setFont() {

        menuFont = new Font("Arial", Font.PLAIN, 20);
        headLineFont = new Font("Arial", Font.PLAIN, 20);
        tableContentFont = new Font("Arial", Font.PLAIN, 15);
        bottomFont = new Font("Arial", Font.PLAIN, 10);
    }

    private void setFontOf(JComponent component, Font font) {
        component.setFont(font);
    }

    private void setBackgroundColorOfComponent(JComponent component, Color color){
        component.setBackground(color);
    }


    //TODO: wohin
    public void setController(Controller controller) {
        this.controller = controller;
        dateSpinner.addChangeListener(new ChangeListener());
        {
            @Override
            public void stateChanged(ChangeEvent e) {
                public void stateChanged(ChangeEvent event) {
                    JSpinner spinner = (JSpinner) event.getSource();
                    controller.getSpinnerDate(spinner);
                }
            }
        });
    }




    public StartPageFrame() {



        backgroundContainer = getContentPane();

        backgroundPanel = new JPanel(new BorderLayout());
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(10, 10 , 10 ,10));
        backgroundContainer.add(backgroundPanel);

        backgroundBorderPanel = new JPanel(new BorderLayout(50, 10));
        backgroundPanel.add(backgroundBorderPanel, BorderLayout.CENTER);


        //general settings
        this.setTitle("MENSA FOOD TRACKER");
        this.initialize();
        this.setFont();
        this.getContentPane().setBackground(new Color(0xeff5dc));

        //setting layouts
        menuPanel = new JPanel(new GridLayout(0, 3, 30, 0));
        setFontOf(menuPanel, menuFont);

        dateSpinnerPanel = new JPanel(new GridLayout(2, 1));
        setFontOf(dateSpinnerPanel, menuFont);

        mainDisplayPanel = new JPanel(new BorderLayout(50, 10));
        setFontOf(mainDisplayPanel, tableContentFont);

        mealTablePanel = new JPanel(new GridLayout(0, 3, 30, 10));
        setFontOf(mealTablePanel, tableContentFont);

        lastUpdatePanel = new JPanel(new GridLayout(0, 2));

        bottomPanel = new JPanel(new GridLayout(0, 2));
        setFontOf(bottomPanel, bottomFont);


        //instantiating components of menu panel
        emptyLabel = new JLabel("");
        selectDateInSpinnerLabel = new JLabel("Datum wählen: ", centerJLabel);
        setFontOf(selectDateInSpinnerLabel, tableContentFont);
        getMenuForSelectedDayButton = new JButton("Menu anzeigen");
        setFontOf(getMenuForSelectedDayButton, tableContentFont);
        showHistoryButton = new JButton("Essenshistorie");
        setFontOf(showHistoryButton, menuFont);
        showNutrientsButton = new JButton("Kumulierte Nährwertangabe");
        setFontOf(showNutrientsButton, menuFont);

        //creating JSpinner for date selection

        dateSpinnerModel = new SpinnerDateModel(Date.from(currentLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant()),
                null, null, Calendar.DAY_OF_MONTH);
        dateSpinner = new JSpinner(dateSpinnerModel);
        dateSpinnerEditor = new JSpinner.DateEditor(dateSpinner, "dd.MM.yyyy");
        dateSpinner.setEditor(dateSpinnerEditor);
        dateSpinnerModel.setEnd(Date.from(spinnerEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));



        //instantiating components of main display panel
        todaysMenuLabel = new JLabel("Tagesmenu, vom: " , centerJLabel);
        setFontOf(todaysMenuLabel, headLineFont);
        mealNameLabel = new JLabel("Name", centerJLabel);
        setFontOf(mealNameLabel, menuFont);
        mealPriceLabel = new JLabel("Preis", centerJLabel);
        setFontOf(mealPriceLabel, menuFont);
        mealLineLabel = new JLabel("Linie", centerJLabel);
        setFontOf(mealLineLabel, menuFont);

        //instantiating components of bottom panel
        lastUpdateLabel = new JLabel("Letztes Update: ", SwingConstants.RIGHT);
        placeHolder2 = new JLabel("Platzhalter2", centerJLabel);
        dateAndTimeOfLastUpdateLabel = new JLabel("", SwingConstants.RIGHT); //TODO: get last update from controller
        authorLabel = new JLabel("Autor: Johanna Krickow", centerJLabel);


        //adding components to menu panel
        backgroundBorderPanel.add(menuPanel, BorderLayout.NORTH);
        dateSpinnerPanel.add(selectDateInSpinnerLabel);
        dateSpinnerPanel.add(dateSpinner);
        dateSpinnerPanel.add(emptyLabel);
        dateSpinnerPanel.add(getMenuForSelectedDayButton);
        menuPanel.add(dateSpinnerPanel);
        menuPanel.add(showHistoryButton);
        menuPanel.add(showNutrientsButton);

        //building base for main display
        backgroundBorderPanel.add(mainDisplayPanel, BorderLayout.CENTER);
        mainDisplayPanel.add(todaysMenuLabel, BorderLayout.NORTH);
        mainDisplayPanel.add(mealTablePanel, BorderLayout.CENTER);

        //adding components to meal table
        mealTablePanel.add(mealNameLabel);
        mealNameLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        mealTablePanel.add(mealPriceLabel);
        mealPriceLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        mealTablePanel.add(mealLineLabel);
        mealLineLabel.setBorder(BorderFactory.createLineBorder(Color.black));

        //adding components to bottom panel
        backgroundBorderPanel.add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.add(lastUpdatePanel);
        lastUpdatePanel.add(lastUpdateLabel);
        lastUpdatePanel.add(placeHolder2);
        bottomPanel.add(authorLabel);

        pack();
    }
}
