package View;

import Control.Controller;
import View.Footer.Footer;
import View.Header.Header;
import View.MainDisplay.MainDisplay;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame  {

    private Controller controller;

    private final Container backgroundContainer;

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

    }




    public MainView() {

        backgroundContainer = getContentPane();

        backgroundPanel = new JPanel(new BorderLayout());
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(10, 10 , 10 ,10));
        backgroundContainer.add(backgroundPanel);

        backgroundBorderPanel = new JPanel(new BorderLayout(50, 10));
        backgroundPanel.add(backgroundBorderPanel, BorderLayout.CENTER);


        //general settings
        this.setTitle("MENSA FOOD TRACKER");
        this.initialize();
        this.getContentPane().setBackground(new Color(0xeff5dc));


        backgroundBorderPanel.add(Header.createHeaderPanel(), BorderLayout.NORTH);
        backgroundBorderPanel.add(MainDisplay.createMainDisplayPanel(), BorderLayout.CENTER);
        backgroundBorderPanel.add(Footer.createFooterPanel(), BorderLayout.SOUTH);


        pack();
    }
}
