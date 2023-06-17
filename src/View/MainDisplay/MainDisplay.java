package View.MainDisplay;

import View.MealTable;
import javax.swing.*;
import java.awt.*;

public class MainDisplay {

    private MealTable mealTable;

    private final JPanel mainDisplayPanel;


    public MainDisplay() {

        mealTable = new MealTable();

        mainDisplayPanel = new JPanel(new BorderLayout(50, 10));

        mainDisplayPanel.add(mealTable.getMealTableBackgroundPanel());
    }

    public JPanel getMainDisplayPanel() {
        return mainDisplayPanel;
    }

    public MealTable getMealTable() {
        return mealTable;
    }
}
