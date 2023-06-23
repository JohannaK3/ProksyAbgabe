package View.Header;

import javax.swing.*;
import java.awt.*;

/**
 * ShowHistory represents a component in the GUI's header section that contains a button to show the meal history.
 * It consists of a panel with a button to display the meal history.
 *
 * @author johannakrickow (ugtfp)
 * @version 23.06.2023
 */
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
