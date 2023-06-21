package View.Header;

import javax.swing.*;
import java.awt.*;

/**
 * Builds section in programm's GUI to show button to switch to the meal history.
 * Is built into the GUI's header
 *
 * @author johannakrickow (ugtfp)
 * @version 22.06.2023
 */
public class ShowHistory {

    private final JPanel showHistoryPanel;

    private final JButton showHistoryButton;

    /**
     * Constructs a ShowHistory object.
     * It consists of a button for displaying the meal history.
     * This constructor initializes the necessary components and adds them to the show history panel.
     */
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
