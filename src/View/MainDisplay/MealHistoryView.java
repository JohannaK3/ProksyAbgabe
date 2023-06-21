package View;

import Model.Meals;
import Model.MensaMealWithDate;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Creates the table where all meals that were added to the history are shown with some of their attributes.
 *
 * @author johannakrickow (ugtfp)
 * @version 22.06.2023
 */
public class MealHistoryView {

    private final Meals meals;
    //private final Meals historyMeals;
    private final View.MealTable mealTable;

    private final JScrollPane historyScrollPane;

    private JPanel historyBackgroundPanel;
    private JPanel historyHeaderPanel;

    private JLabel historyHeaderLabel;

    private JTable historyTable;
    private DefaultTableModel defaultTableModel;

    private int initialRowNum;

    /**
     * Constructs a MealHistoryView object.
     * It contains a table to display the meal history.
     * This constructor initializes the necessary components and sets up the layout of the meal history view.
     */
    public MealHistoryView() {
        mealTable = new View.MealTable();
        meals = mealTable.getMeals();

        createHistoryView();
        createHistoryTabel();

        historyScrollPane = new JScrollPane(historyTable);

        historyHeaderPanel.add(historyHeaderLabel);
        historyBackgroundPanel.add(historyHeaderPanel, BorderLayout.NORTH);
        historyBackgroundPanel.add(historyScrollPane, BorderLayout.CENTER);
    }

    private void createHistoryView() {
        historyBackgroundPanel = new JPanel(new BorderLayout());
        historyHeaderPanel = new JPanel();
        historyHeaderLabel = new JLabel("Essenshistorie (zum Entfernen: Doppelklick auf das Gericht)");
    }

    /**
     * Creates the history table for displaying the meal history.
     * Initializes the table model with column names and sets up the table to be non-editable.
     * Resulting table is stored in 'historyTable' instance variable.
     */
    private void createHistoryTabel() {
        String[] columnsArray = {"Name", "Datum", "Preis in €", "Linie", "KCal", "Proteine (in g)", "Kohlenhydrate (in g)",
                "Fett (in g)"};
        initialRowNum = 0;
        defaultTableModel = new DefaultTableModel(initialRowNum, columnsArray.length) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        defaultTableModel.setColumnIdentifiers(columnsArray);

        historyTable = new JTable(defaultTableModel);
    }

    public JPanel getHistoryBackgroundPanel() {
        return historyBackgroundPanel;
    }

    public void updateHistoryTable(MensaMealWithDate meal) {
        defaultTableModel.addRow(meal.getExtendedMealInfo());
    }

    public void removeRowFromHistory(int rowIndex) {
        defaultTableModel.removeRow(rowIndex);
    }

    public JTable getHistoryTable() {
        return historyTable;
    }

    public Meals getMeals() {
        return meals;
    }

    public MensaMealWithDate getMealOfRow(int row) {
        //historyMeals = new Meals()
        return meals.getCurrentMeals().get(row);
        //return defaultTableModel.getValueAt(row, 0);
    }

}
