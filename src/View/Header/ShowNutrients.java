package View.Header;

import javax.swing.*;
import java.awt.*;

/**
 * Builds section in programm's GUI to show button to switch to the nutrients overview.
 * Is built into the GUI's header
 *
 * @author johannakrickow (ugtfp)
 * @version 22.06.2023
 */
public class ShowNutrients {

    private final JPanel showNutrientsPanel;

    private final JButton showNutrientsButton;

    /**
     * Constructs a ShowNutrients object.
     * It consists of a button for displaying the accumulated nutrient information.
     * This constructor initializes the necessary components and adds them to the show nutrients panel.
     */
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
