package View.Header;

import javax.swing.*;
import java.awt.*;

public class ShowHistory {

    private final JPanel showHistoryPanel;

    private final JButton showHistoryButton;


    public ShowHistory() {

        showHistoryPanel = new JPanel(new GridLayout(1, 1));

        showHistoryButton = new JButton("Essenshistorie");
    }

    public static JPanel createShowHistoryPanel() {
        ShowHistory showHistory = new ShowHistory();
        return showHistory.showHistoryPanel;
    }
}
