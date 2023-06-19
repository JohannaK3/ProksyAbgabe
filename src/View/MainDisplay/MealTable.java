package View;

import Model.Meals;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
    private final DefaultTableModel defaultTableModel;

    private final JLabel todaysMenuLabel, selectedDateLabel;
    private String[][] dataArray;


    public MealTable() {
        meals = new Meals(currentLocalDate);

        this.updateDataArray();

        //TODO: disable editing rows for all rows
        mealTableBackgroundPanel = new JPanel(new BorderLayout());
        dateOverviewPanel = new JPanel(new GridLayout(0, 2));

        String[] columnsArray = {"Name", "Preis", "Linie"};
        defaultTableModel = new DefaultTableModel(dataArray, columnsArray) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        mealsJTable = new JTable(defaultTableModel);

        todaysMenuLabel = new JLabel("Tagesmenu, vom: " , SwingConstants.RIGHT);
        selectedDateLabel = new JLabel(currentLocalDate.format(formatter), SwingConstants.LEFT);

        mealTableBackgroundPanel.add(dateOverviewPanel, BorderLayout.NORTH);
        dateOverviewPanel.add(todaysMenuLabel);
        dateOverviewPanel.add(selectedDateLabel);

        scrollBarPane = new JScrollPane(mealsJTable);
        scrollBarPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollBarPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        mealTableBackgroundPanel.add(scrollBarPane, BorderLayout.CENTER);
    }

    public void updateDataArray() {
        dataArray = new String[meals.getCurrentMeals().size()][3];

        for(int i = 0; i < meals.getCurrentMeals().size(); i++) {
            dataArray[i] = meals.getCurrentMeals().get(i).getMealInfo();
        }
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

    public JTable getMealsJTable() {
        return mealsJTable;
    }

    public Object[][] getDataArray() {
        return dataArray.clone();
    }
}
