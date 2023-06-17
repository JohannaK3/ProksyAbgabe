package View;

import Model.Meals;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class MealTable {

    private final LocalDate currentLocalDate = LocalDate.now();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private final Meals meals;

    private final JScrollPane scrollBarPane;

    private final JPanel mealTableBackgroundPanel, dateOverviewPanel;

    //TODO: create Table Model -> possibly Meals modeln in different class
    private final JTable mealsJTable;

    private final JLabel todaysMenuLabel, selectedDateLabel;
    private final String[][] dataArray;

    //TODO: create add & remove button to add/remove meals to/from history

    public MealTable() {
        String[] columnsArray = {"Name", "Preis", "Linie"};
        //TODO: change date
        meals = new Meals(LocalDate.of(2023, 06, 19));

        dataArray = new String[meals.getCurrentMeals().size()][3];

        for(int i = 0; i < meals.getCurrentMeals().size(); i++) {
            dataArray[i] = meals.getCurrentMeals().get(i).getMealInfo();
        }

        mealTableBackgroundPanel = new JPanel(new BorderLayout());
        dateOverviewPanel = new JPanel(new GridLayout(0, 2));
        mealsJTable = new JTable(dataArray, columnsArray);

        todaysMenuLabel = new JLabel("Tagesmenu, vom: " , SwingConstants.RIGHT);
        selectedDateLabel = new JLabel(currentLocalDate.format(formatter), SwingConstants.LEFT);

        mealTableBackgroundPanel.add(dateOverviewPanel, BorderLayout.NORTH);
        dateOverviewPanel.add(todaysMenuLabel);
        dateOverviewPanel.add(selectedDateLabel);

        scrollBarPane = new JScrollPane(mealsJTable);
        scrollBarPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollBarPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        mealTableBackgroundPanel.add(scrollBarPane, BorderLayout.CENTER);
    }

    public JPanel getMealTableBackgroundPanel() {
        return mealTableBackgroundPanel;
    }

    public JLabel getSelectedDateLabel() {
        return selectedDateLabel;
    }

    public Meals getMeals() {
        return meals;
    }
}
