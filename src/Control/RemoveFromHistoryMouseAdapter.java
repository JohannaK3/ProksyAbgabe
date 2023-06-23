package Control;


import Model.MensaMealWithDate;
import Model.Nutrients;
import View.MainView;
import View.NutritientOverview;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * MouseAdapter to remove meal from history and remove its nutrients.
 *
 * @author johannakrickow (ugtfp)
 * @version 22.06.2023
 */
public class RemoveFromHistoryMouseAdapter extends MouseAdapter {

    private final MainView view;

    public RemoveFromHistoryMouseAdapter(MainView view) {
        this.view = view;
    }

    /**
     * Is called when a mouse click event occurs on history table.
     * Handles double-click events on the history table.
     * Removes the selected row from the history table, updates the nutrients display,
     * and updates the nutrients table.
     *
     * @param event MouseEvent object representing the mouse click event.
     */
    @Override
    public void mouseClicked(MouseEvent event) {

        if(event.getClickCount() == 2) {
            int rowIndex = view.getMainDisplay().getMealHistoryView().getHistoryTable().getSelectedRow();
            updateNutrients(rowIndex);
            view.getMainDisplay().getMealHistoryView().removeRowFromHistory(rowIndex);

            updateTable(view.getMainDisplay().getNutritientOverview());
        }
    }

    /**
     * Retrieves the MensaMealWithDate object from the history table at the specified row index.
     * @param rowIndex index of the row in the history table.
     * @return MensaMealWithDate object representing the selected meal.
     */
    private MensaMealWithDate getMealFromTable(int rowIndex) {
        return view.getMainDisplay().getMealHistoryView().getMealOfRow(rowIndex);
    }

    /**
     * Updates the nutrients display by removing the nutrients of the selected meal.
     * @param row containing MensaMealWithDate object representing the selected meal.
     */
    private void updateNutrients(int rowIndex) {
        TableModel tableModel = view.getMainDisplay().getMealHistoryView().getHistoryTable().getModel();
        double price = Double.parseDouble((String) tableModel.getValueAt(rowIndex, 2));
        double kCal = Double.parseDouble((String) tableModel.getValueAt(rowIndex, 4));
        double proteins = Double.parseDouble((String) tableModel.getValueAt(rowIndex, 5));
        double carbs = Double.parseDouble((String) tableModel.getValueAt(rowIndex, 6));
        double fat = Double.parseDouble((String) tableModel.getValueAt(rowIndex, 7));
        String isVeggie = (String) tableModel.getValueAt(rowIndex, 8);

        Nutrients nutrients = view.getMainDisplay().getNutritientOverview().getNutrients();
        nutrients.removeNutrients(kCal, proteins, carbs, fat, isVeggie, price);
    }

    /**
     * Updates the nutrients table in the nutrient overview with the updated data.
     * @param nutritientOverview NutrientOverview object representing the view containing the nutrients table.
     */
    private void updateTable(NutritientOverview nutritientOverview) {
        //TODO: colArr is same in MealTable, how can I use the same one?
        String[] colArr = {"Attribut", "Kummulierter Wert"};
        DefaultTableModel updatedTabelModel = new DefaultTableModel(
                nutritientOverview.getAccumulatedNutrientArray(), colArr) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        nutritientOverview.getNutrientsTable().setModel(updatedTabelModel);
    }
}

