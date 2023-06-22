package View;

import Model.Meals;
import Model.MensaMealWithDate;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class MealHistoryView {

    private final Meals meals;
    private final View.MealTable mealTable;

    private final JScrollPane historyScrollPane;

    private JPanel historyBackgroundPanel;
    private JPanel historyHeaderPanel;

    private JLabel historyHeaderLabel;

    private JTable historyTable;

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

    public JPanel getHistoryBackgroundPanel() {
        return historyBackgroundPanel;
    }

    public Object[][] updateHistoryTable(MensaMealWithDate meal) {
        DefaultTableModel model = (DefaultTableModel) historyTable.getModel();
        model.addRow(meal.getExtendedMealInfo());
        return getTableData(historyTable);
    }

    private Object[][] getTableData(JTable historyTable) {
        TableModel dtm = historyTable.getModel();
        int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
        Object[][] tableData = new Object[nRow][nCol];
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                tableData[i][j] = dtm.getValueAt(i, j);
            }
        }

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

        return tableData;
    }

    public void removeRowFromHistory(int rowIndex) {
        DefaultTableModel model = (DefaultTableModel) historyTable.getModel();
        model.removeRow(rowIndex);
    }

    public JTable getHistoryTable() {
        return historyTable;
    }

    public MensaMealWithDate getMealOfRow(int row) {
        return meals.getCurrentMeals().get(row);
    }

}
