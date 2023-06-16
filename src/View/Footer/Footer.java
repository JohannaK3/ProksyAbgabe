package View.Footer;

import javax.swing.*;
import java.awt.*;

public class Footer {

    private final JPanel footerPanel;
    private final JPanel lastUpdatePanel;

    private JLabel lastUpdateLabel, placeHolder2, dateAndTimeOfLastUpdateLabel, authorLabel;

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
