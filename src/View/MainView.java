package View;

import Control.Controller;
import View.Footer.Footer;
import View.Header.Header;
import View.MainDisplay.MainDisplay;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainView extends JFrame  {

    private Controller controller;
    private Header header;
    private MainDisplay mainDisplay;
    private Footer footer;

    private final Container contentPainContainer;

    //TODO: handle fonts

    private final JPanel backgroundPanel;
    private final JPanel backgroundBorderPanel;



    private void initialize() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setExtendedState(MAXIMIZED_BOTH); //TODO: decide pack() or this method
        setResizable(true);
        setLocationRelativeTo(null);
    }

    public void setController(Controller controller) {
        this.controller = controller;
        this.header.getSelectDate().getGetMenuForSelectedDayButton().addActionListener(controller.createSelectedDayButtonActionListener());
        this.header.getShowHistory().getShowHistoryButton().addActionListener(controller.createShowHistoryButtonActionListener());
        this.header.getShowNutrients().getShowNutrientsButton().addActionListener(controller.createShowNutrientsButtonActionListener());
        this.mainDisplay.getMealTable().getMealsJTable().addMouseListener(controller.createMealMouseAdapter());
        this.mainDisplay.getMealHistoryView().getHistoryTable().addMouseListener(controller.createHistoryMouseAdapter());
    }

    public MainView() {

        contentPainContainer = getContentPane();

        backgroundPanel = new JPanel(new BorderLayout());
        backgroundBorderPanel = new JPanel(new BorderLayout(50, 10));
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(10, 10 , 10 ,10));

        contentPainContainer.add(backgroundPanel);
        backgroundPanel.add(backgroundBorderPanel, BorderLayout.CENTER);

        //general settings
        this.setTitle("MENSA FOOD TRACKER");
        this.initialize();
        this.getContentPane().setBackground(new Color(0xeff5dc));

        this.header = new Header();
        this.mainDisplay = new MainDisplay();
        this.footer = new Footer();

        backgroundBorderPanel.add(header.getHeaderPanel(), BorderLayout.NORTH);
        backgroundBorderPanel.add(mainDisplay.getMainDisplayPanel(), BorderLayout.CENTER);
        backgroundBorderPanel.add(footer.getFooterPanel(), BorderLayout.SOUTH);

        pack();
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
