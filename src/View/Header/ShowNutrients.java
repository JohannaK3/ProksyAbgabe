package View.Header;

import javax.swing.*;
import java.awt.*;

/**
 * Represents a component in the GUI's header section for selecting that contains a button to show the accumulated nutrients.
 * It consists of a panel with a date spinner and a button that retrieves the menu for the selected day.
 *
 * @author johannakrickow (ugtfp)
 * @version 23.06.2023
 */
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
