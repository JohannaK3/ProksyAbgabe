package View;

import Model.Meals;
import Model.MensaMealWithDate;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

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
