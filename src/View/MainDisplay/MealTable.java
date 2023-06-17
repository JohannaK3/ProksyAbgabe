package View;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MealTable {

    private LocalDate currentLocalDate = LocalDate.now();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private final JPanel mealTableBackgroundPanel, mealTablePanel, dateOverviewPanel;

    private JLabel todaysMenuLabel, selectedDateLabel, mealNameLabel, mealPriceLabel, mealLineLabel;

    //TODO: create add & remove button to add/remove meals to/from history

    public MealTable() {

        mealTableBackgroundPanel = new JPanel(new BorderLayout());
        dateOverviewPanel = new JPanel(new GridLayout(0, 2));
        mealTablePanel = new JPanel(new GridLayout(0, 3, 30, 10));

        todaysMenuLabel = new JLabel("Tagesmenu, vom: " , SwingConstants.RIGHT);
        selectedDateLabel = new JLabel(currentLocalDate.format(formatter), SwingConstants.LEFT);
        //TODO: new GridLayout with two Lines, one with name/price/line and second one with new grid for meals
        //TODO: change GridLayout for meals to JScrollPane or something else
        mealNameLabel = new JLabel("Name", SwingConstants.CENTER);
        mealPriceLabel = new JLabel("Preis", SwingConstants.CENTER);
        mealLineLabel = new JLabel("Linie", SwingConstants.CENTER);

        mealTableBackgroundPanel.add(dateOverviewPanel, BorderLayout.NORTH);
        dateOverviewPanel.add(todaysMenuLabel);
        dateOverviewPanel.add(selectedDateLabel);

        mealTableBackgroundPanel.add(mealTablePanel, BorderLayout.CENTER);
        mealTablePanel.add(mealNameLabel);
        mealTablePanel.add(mealPriceLabel);
        mealTablePanel.add(mealLineLabel);
        mealPriceLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        mealNameLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        mealLineLabel.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public JPanel getMealTableBackgroundPanel() {
        return mealTableBackgroundPanel;
    }

    public JLabel getSelectedDateLabel() {
        return selectedDateLabel;
    }

    public JPanel getMealTablePanel() {
        return mealTablePanel;
    }
}
