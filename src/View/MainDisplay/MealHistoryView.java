package View;

import Model.Meals;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MealHistoryView {

    private final Meals meals;
    private final View.MealTable mealTable;

    private final JScrollPane historyScrollPane;

    private final JPanel historyBackgroundPanel;
    private final JPanel historyHeaderPanel;

    private final JLabel historyHeaderLabel;

    private final JTable historyTable;
    private final DefaultTableModel defaultTableModel;

    private String[][] extendedDataArray;
    private final int initialRowNum;

    public MealHistoryView() {
        mealTable = new View.MealTable();
        meals = mealTable.getMeals();

        historyBackgroundPanel = new JPanel(new BorderLayout());
        historyHeaderPanel = new JPanel();
        historyHeaderLabel = new JLabel("Essenshistorie (zum Entfernen: Doppelklick auf das Gericht)");
        historyHeaderPanel.add(historyHeaderLabel);

        String[] columnsArray = {"Name", "Datum", "Preis", "Linie", "KCal", "Proteine (in g)", "Kohlenhydrate (in g)",
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

        historyScrollPane = new JScrollPane(historyTable);

        historyBackgroundPanel.add(historyHeaderPanel, BorderLayout.NORTH);
        historyBackgroundPanel.add(historyScrollPane, BorderLayout.CENTER);

    }

    public JPanel getHistoryBackgroundPanel() {
        return historyBackgroundPanel;
    }

    public void updateExtendedDateArray() {
        extendedDataArray = new String[meals.getCurrentMeals().size()][3];
    }

}
