package View;

import Control.Controller;
import View.Footer.Footer;
import View.Header.Header;
import View.MainDisplay.MainDisplay;
import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame  {

    private Controller controller;
    private Header header;
    private MainDisplay mainDisplay;
    private Footer footer;

    //private final Container contentPainContainer;

    //TODO: handle fonts

    private JFrame application;
    private JPanel backgroundPanel;
    private JPanel backgroundBorderPanel;





    public MainView() {
        application = new JFrame();
        application.getContentPane();

        createMainViewPanel();
        generalSettings();

        this.header = new Header();
        this.mainDisplay = new MainDisplay();
        this.footer = new Footer();

        backgroundBorderPanel.add(header.getHeaderPanel(), BorderLayout.NORTH);
        backgroundBorderPanel.add(mainDisplay.getMainDisplayPanel(), BorderLayout.CENTER);
        backgroundBorderPanel.add(footer.getFooterPanel(), BorderLayout.SOUTH);

        application.pack();
        application.setVisible(true);
    }

    private void  createMainViewPanel() {
        backgroundPanel = new JPanel(new BorderLayout());
        backgroundBorderPanel = new JPanel(new BorderLayout(50, 20));
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(20, 20 , 20 ,20));

        application.add(backgroundPanel);
        backgroundPanel.add(backgroundBorderPanel, BorderLayout.CENTER);
    }

    private void generalSettings() {
        this.setTitle("MENSA FOOD TRACKER");
        this.initialize();
        this.getContentPane().setBackground(new Color(0xeff5dc));
        setLocationRelativeTo(null);
    }

    private void initialize() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
    }

    public void setController(Controller controller) {
        this.controller = controller;
        this.header.getSelectDate().getGetMenuForSelectedDayButton().addActionListener(
                controller.createSelectedDayButtonActionListener());
        this.header.getShowHistory().getShowHistoryButton().addActionListener(
                controller.createShowHistoryButtonActionListener());
        this.header.getShowNutrients().getShowNutrientsButton().addActionListener(
                controller.createShowNutrientsButtonActionListener());
        this.mainDisplay.getMealTable().getMealsJTable().addMouseListener(
                controller.createMealMouseAdapter());
        this.mainDisplay.getMealHistoryView().getHistoryTable().addMouseListener(
                controller.createHistoryMouseAdapter());
        application.addWindowListener(controller.createWindowActionListener());
    }

    public Header getHeader() {
        return header;
    }

    public MainDisplay getMainDisplay() {
        return mainDisplay;
    }

    public Footer getFooter() {
        return footer;
    }
}
