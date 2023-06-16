package View.MainDisplay;

import View.MealTable;

import javax.swing.*;
import java.awt.*;

public class MainDisplay {

    private final JPanel mainDisplayPanel;

    public MainDisplay() {

        mainDisplayPanel = new JPanel(new BorderLayout(50, 10));

        mainDisplayPanel.add(MealTable.createMealTablePanel());
    }

    public static JPanel createMainDisplayPanel() {
        MainDisplay mainDisplay = new MainDisplay();
        return mainDisplay.mainDisplayPanel;
    }
}
