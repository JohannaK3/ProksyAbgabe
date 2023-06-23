package View;

import Model.Meals;
import Model.MensaMealWithDate;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class MealTable {

    private final Meals meals;

    private final LocalDate currentLocalDate = LocalDate.now();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private JScrollPane scrollBarPane;

    private JPanel mealTableBackgroundPanel;
    private JPanel dateOverviewPanel;
    private JPanel footerPanel;

    //TODO: create Table Model -> possibly Meals modeln in different class
    private JTable mealsJTable;
    private DefaultTableModel defaultTableModel;

    private final JLabel todaysMenuLabel, selectedDateLabel, dobbleclickLabel;
    private String[][] dataArray;


    public MealTable() {
        meals = new Meals(currentLocalDate);
        this.updateDataArray();

        createMealTablePanel();
        createTable();

        dobbleclickLabel = new JLabel("Gericht hinzufügen: Doppelklick auf das Gericht", SwingConstants.LEFT);
        todaysMenuLabel = new JLabel("Tagesmenu, vom: " , SwingConstants.RIGHT);
        selectedDateLabel = new JLabel(currentLocalDate.format(formatter), SwingConstants.LEFT);

        addComponentsToPanel();

    }

    private void createMealTablePanel() {
        mealTableBackgroundPanel = new JPanel(new BorderLayout());
        dateOverviewPanel = new JPanel();
        footerPanel = new JPanel();
    }

    private void addComponentsToPanel() {
        mealTableBackgroundPanel.add(dateOverviewPanel, BorderLayout.NORTH);
        mealTableBackgroundPanel.add(footerPanel, BorderLayout.SOUTH);

        dateOverviewPanel.add(todaysMenuLabel);
        dateOverviewPanel.add(selectedDateLabel);
        dateOverviewPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        dateOverviewPanel.setBackground(new Color(0xeff5dc));

        footerPanel.add(dobbleclickLabel);
        footerPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        footerPanel.setBackground(new Color(0xeff5dc));

        scrollBarPane = new JScrollPane(mealsJTable);
        scrollBarPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollBarPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        mealTableBackgroundPanel.add(scrollBarPane, BorderLayout.CENTER);
    }

    private void createTable() {
        String[] columnsArray = {"Name", "Preis in €", "Linie"};
        defaultTableModel = new DefaultTableModel(dataArray, columnsArray) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        mealsJTable = new JTable(defaultTableModel);
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

    public MensaMealWithDate getMealOfRow(int row) {
        return meals.getCurrentMeals().get(row);
    }
}
