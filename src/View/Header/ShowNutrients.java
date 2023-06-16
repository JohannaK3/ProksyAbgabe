package View.Header;

import javax.swing.*;
import javax.swing.plaf.SpinnerUI;
import java.awt.*;
import java.security.PublicKey;

public class ShowNutrients {

    private final JPanel showNutrientsPanel;

    private final JButton showNutrientsButton;

    public ShowNutrients() {

        showNutrientsPanel = new JPanel(new GridLayout(1, 1));

        showNutrientsButton = new JButton("Kumulierte Nährwertangabe");
    }

    public static JPanel createShowNutrientsPanel() {
        ShowNutrients showNutrients = new ShowNutrients();
        return showNutrients.showNutrientsPanel;
    }
}
