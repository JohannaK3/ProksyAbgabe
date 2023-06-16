package View;

import javax.swing.*;
import java.awt.*;

public class MealTable {

    private final JPanel mealTableBackgroundPanel, mealTablePanel, dateOverviewPanel;

    private JLabel todaysMenuLabel, selectedDateLabel, mealNameLabel, mealPriceLabel, mealLineLabel;

    //TODO: create add & remove button to add/remove meals to/from history

    public MealTable() {

        mealTableBackgroundPanel = new JPanel(new BorderLayout());
        dateOverviewPanel = new JPanel(new GridLayout(0, 2));
        mealTablePanel = new JPanel(new GridLayout(0, 3, 30, 10));

        todaysMenuLabel = new JLabel("Tagesmenu, vom: " , SwingConstants.CENTER);
        selectedDateLabel = new JLabel("Hier muss noch das Selected Date hin", SwingConstants.CENTER);
        mealNameLabel = new JLabel("Name", SwingConstants.CENTER);
        mealPriceLabel = new JLabel("Preis", SwingConstants.CENTER);
        mealLineLabel = new JLabel("Linie", SwingConstants.CENTER);

        mealTableBackgroundPanel.add(dateOverviewPanel, BorderLayout.NORTH);
        dateOverviewPanel.add(todaysMenuLabel);
        dateOverviewPanel.add(selectedDateLabel);
        mealTableBackgroundPanel.add(mealTablePanel, BorderLayout.CENTER);
        mealTablePanel.add(mealNameLabel);
        mealNameLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        mealTablePanel.add(mealPriceLabel);
        mealPriceLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        mealTablePanel.add(mealLineLabel);
        mealLineLabel.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public static JPanel createMealTablePanel() {
        MealTable mealTable = new MealTable();
        return mealTable.mealTableBackgroundPanel;
    }
}
