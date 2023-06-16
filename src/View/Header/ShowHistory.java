package View.Header;

import javax.swing.*;
import java.awt.*;

public class ShowHistory {

    private final JPanel showHistoryPanel;

    private final JButton showHistoryButton;


    public ShowHistory() {
        showHistoryPanel = new JPanel(new GridLayout(1, 1));
        showHistoryButton = new JButton("Essenshistorie");

        showHistoryPanel.add(showHistoryButton);
    }

    public JPanel getShowHistoryPanel() {
        return showHistoryPanel;
    }

    public JButton getShowHistoryButton() {
        return showHistoryButton;
    }
}
