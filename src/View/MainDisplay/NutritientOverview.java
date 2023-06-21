package View;

import Model.Nutrients;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Creates the table where the accumulated nutrients of all meals that are currently in the history are shown
 *
 * @author johannakrickow (ugtfp)
 * @version 22.06.2023
 */
public class NutritientOverview {

    private final Nutrients nutrients;

    private JPanel nutrientsBackgroundPanel;
    private JPanel nutrientsHeaderPanel;
    private JPanel paddingBorderPanel;
    private final JScrollPane nutrientsScrollPane;
    private JLabel nutrientsHeaderLabel;

    private JTable nutrientsTable;
    private DefaultTableModel defaultTableModel;

    private Object[][] accumulatedNutrientArray;


    public NutritientOverview() {
        nutrients = new Nutrients();

        createNutrientsView();
        createNutrientsTable();

        nutrientsScrollPane = new JScrollPane(nutrientsTable);

        nutrientsBackgroundPanel.add(paddingBorderPanel, BorderLayout.CENTER);
        nutrientsBackgroundPanel.add(nutrientsHeaderPanel, BorderLayout.NORTH);
        paddingBorderPanel.add(nutrientsScrollPane, BorderLayout.CENTER);
        nutrientsHeaderPanel.add(nutrientsHeaderLabel);

    }

    private void createNutrientsView() {
        nutrientsBackgroundPanel = new JPanel(new BorderLayout());
        nutrientsHeaderPanel = new JPanel();
        paddingBorderPanel = new JPanel(new BorderLayout());
        paddingBorderPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 80, 100));
        nutrientsHeaderLabel = new JLabel("Kumulierte Nährwerte");
    }

    /**
     * Create nutrients table.
     */
    private void createNutrientsTable() {
        String[] colsArray = {"Attribut", "Kummulierter Wert"};
        accumulatedNutrientArray = nutrients.getAccumulatedNutrientsArray();
        defaultTableModel = new DefaultTableModel(accumulatedNutrientArray, colsArray) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        defaultTableModel.setColumnIdentifiers(colsArray);
        nutrientsTable = new JTable(defaultTableModel);

        nutrientsTable.setRowHeight(70);
    }

    public JPanel getNutrientsBackgroundPanel() {
        return nutrientsBackgroundPanel;
    }

    public JTable getNutrientsTable() {
        return nutrientsTable;
    }

    public Object[][] getAccumulatedNutrientArray() {
        return accumulatedNutrientArray.clone();
    }

    public Nutrients getNutrients() {
        return this.nutrients;
    }
}
