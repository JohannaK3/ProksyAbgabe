package View;

import Model.Meals;
import Model.MensaMealWithDate;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * The MealHistoryView class represents the view for displaying the meal history.
 * It contains a table to show the history of meals.
 *
 * @author johannakrickow (ugtfp)
 * @version 23.06.2023
 */
public class MealHistoryView {

    private final Meals meals;
    private final View.MealTable mealTable;

    private final JScrollPane historyScrollPane;

    private JPanel historyBackgroundPanel;
    private JPanel historyHeaderPanel;
    private JPanel historyFooterPanel;

    private JLabel historyHeaderLabel;
    private JLabel historyFooterLabel;

    private JTable historyTable;

    private int initialRowNum;

    public MealHistoryView() {
        mealTable = new View.MealTable();
        meals = mealTable.getMeals();

        createHistoryView();
        createHistoryTabel();

        historyScrollPane = new JScrollPane(historyTable);

        historyHeaderPanel.add(historyHeaderLabel);
        historyFooterPanel.add(historyFooterLabel);
        historyBackgroundPanel.add(historyHeaderPanel, BorderLayout.NORTH);
        historyBackgroundPanel.add(historyScrollPane, BorderLayout.CENTER);
        historyBackgroundPanel.add(historyFooterPanel, BorderLayout.SOUTH);
    }

    private void createHistoryView() {
        historyBackgroundPanel = new JPanel(new BorderLayout());
        historyHeaderPanel = new JPanel();
        historyHeaderPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        historyHeaderPanel.setBackground(new Color(0xeff5dc));
        historyFooterPanel = new JPanel();
        historyFooterPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        historyFooterPanel.setBackground(new Color(0xeff5dc));
        historyFooterLabel = new JLabel("Zum Entfernen: Doppelklick auf das Gericht");
        historyHeaderLabel = new JLabel("Essenshistorie");
    }

    private void createHistoryTabel() {
        String[] columnsArray = {"Name", "Datum", "Preis in €", "Linie", "KCal", "Proteine (in g)", "Kohlenhydrate (in g)",
                "Fett (in g)", "Vegetarisch?"};
        initialRowNum = 0;
        DefaultTableModel historyTableModel = new DefaultTableModel(initialRowNum, columnsArray.length) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        historyTableModel.setColumnIdentifiers(columnsArray);

        historyTable = new JTable(historyTableModel);
    }

    /**
     * Sorts the table data in descending order based on the meal's date.
     * @param tableData data to be sorted.
     */
    private void sortMealsDesc(Object[][] tableData) {
        Arrays.sort(tableData, (entity1, entity2) -> {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            Date d1 = null;
            Date d2 = null;

            try {
                d1 = sdf.parse((String) entity1[1]);
                d2 = sdf.parse((String) entity2[1]);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return d2.compareTo(d1);
        });
    }

    /**
     * Retrieves the data from the history table.
     * @return The table data as a two-dimensional array of objects.
     */
    private Object[][] getTableData() {
        DefaultTableModel dtm = (DefaultTableModel) historyTable.getModel();
        int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
        Object[][] tableData = new Object[nRow][nCol];

        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                tableData[i][j] = dtm.getValueAt(i, j);
            }
        }

        sortMealsDesc(tableData);
        return tableData;
    }

    /**
     * Adds a meal to the table model.
     * Updates table model with a new data array of meals.
     * @param meal
     * @return calls method get a 2D array with the meals and attributs.
     */
    public Object[][] updateHistoryTable(MensaMealWithDate meal) {
        ((DefaultTableModel) historyTable.getModel()).addRow(meal.getExtendedMealInfo());
        return getTableData();
    }

    /**
     * Retrieves the data from the history table.
     * Doesn't sort them.
     * @return table data as a two-dimensional array of objects.
     */
    public Object[][] getTableData2() {
        DefaultTableModel dtm = (DefaultTableModel) historyTable.getModel();
        int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
        Object[][] tableData = new Object[nRow][nCol];

        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                tableData[i][j] = dtm.getValueAt(i, j);
            }
        }
        return tableData;
    }

    public void removeRowFromHistory(int rowIndex) {
        DefaultTableModel model = (DefaultTableModel) historyTable.getModel();
        model.removeRow(rowIndex);
    }

    public JTable getHistoryTable() {
        return historyTable;
    }

    public JPanel getHistoryBackgroundPanel() {
        return historyBackgroundPanel;
    }
}
