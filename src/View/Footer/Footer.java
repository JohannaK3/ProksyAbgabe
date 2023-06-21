package View.Footer;

import javax.swing.*;
import java.awt.*;

/**
 * Builds footer section of programm's GUI
 *
 * @author johannakrickow 8ugtfp)
 * @version 22.06.2023
 */
public class Footer {

    private final JPanel footerPanel;
    private final JPanel lastUpdatePanel;

    private JLabel lastUpdateLabel, placeHolder2, dateAndTimeOfLastUpdateLabel, authorLabel;

    /**
     * Constructs a Footer object.
     * The footer is displayed using a JPanel with a GridLayout.
     * This constructor initializes the necessary components and adds them to the footer panel.
     */
    public Footer() {

        footerPanel = new JPanel(new GridLayout(0, 2));
        lastUpdatePanel = new JPanel(new GridLayout(0, 2));

        lastUpdateLabel = new JLabel("Letztes Update: ", SwingConstants.RIGHT);
        placeHolder2 = new JLabel("Platzhalter2", SwingConstants.LEFT);
        dateAndTimeOfLastUpdateLabel = new JLabel("", SwingConstants.RIGHT); //TODO: get last update from controller
        authorLabel = new JLabel("Autor: Johanna Krickow", SwingConstants.CENTER);

        footerPanel.add(lastUpdatePanel);
        lastUpdatePanel.add(lastUpdateLabel);
        lastUpdatePanel.add(placeHolder2);
        footerPanel.add(authorLabel);
    }

    public  JPanel getFooterPanel() {
        return footerPanel;
    }
}
