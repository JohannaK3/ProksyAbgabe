package View.Header;

import javax.swing.*;
import java.awt.*;

public class ShowNutrients {

    private final JPanel showNutrientsPanel;

    private final JButton showNutrientsButton;

    public ShowNutrients() {
        showNutrientsPanel = new JPanel(new GridLayout(1, 1));
        showNutrientsButton = new JButton("Kumulierte Nährwertangabe");

        showNutrientsPanel.add(showNutrientsButton);
    }

    public JPanel getShowNutrientsPanel() {
        return showNutrientsPanel;
    }

    public JButton getShowNutrientsButton() {
        return showNutrientsButton;
    }
}
