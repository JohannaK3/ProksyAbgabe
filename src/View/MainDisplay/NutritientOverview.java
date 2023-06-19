package View;

import javax.swing.*;
import java.awt.*;

public class NutritientOverview {

    private JPanel nutrientsBackgroundPanel;

    public NutritientOverview() {

        nutrientsBackgroundPanel = new JPanel(new BorderLayout());

    }

    public JPanel getNutrientsBackgroundPanel() {
        return nutrientsBackgroundPanel;
    }

}
