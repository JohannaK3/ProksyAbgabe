package View.Footer;

import javax.swing.*;

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
