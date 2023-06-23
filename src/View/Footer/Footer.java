package View.Footer;

import javax.swing.*;

/**
 * Footer section of the GUI.
 *
 * @author johannakrickow (ugtfp)
 * @version 23.06.2023
 */
public class Footer {

    private final JPanel footerPanel;
    private JLabel authorLabel;

    public Footer() {

        footerPanel = new JPanel();
        authorLabel = new JLabel("Autor:    Johanna Krickow", SwingConstants.CENTER);

        footerPanel.add(authorLabel);
    }

    public  JPanel getFooterPanel() {
        return footerPanel;
    }
}
