package Control;

import Model.MensaMealWithDate;
import Model.Nutrients;
import View.MainView;
import View.NutritientOverview;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * MouseAdapter to add meal to history and add nutritions
 *
 * @author johannakrickow (ugtfp)
 * @version 22.06.2023
 */
public class AddMealMouseAdapter extends MouseAdapter {
    private final MainView view;
    private MensaMealWithDate selectedMeal;

    public AddMealMouseAdapter(MainView view) {
        this.view = view;
    }


    /**
     * Is called when a mouse click event occurs on meal table.
     * Handles a double-click event on the meal table.
     * Retrieves the selected meal, adds it to the history, updates the nutrients display.
     * @param event representing the mouse click event.
     */
    @Override
    public void mouseClicked(MouseEvent event) {

        if(event.getClickCount() == 2) {
            int rowIndex = view.getMainDisplay().getMealTable().getMealsJTable().getSelectedRow();
            addMealToHistory(rowIndex);

            selectedMeal = getMealFromTable(rowIndex);
            updateNutrients(selectedMeal);
            updateTable(view.getMainDisplay().getNutritientOverview());
        }
    }

    /**
     * Adds new meal to history and history list, calls to update the table.
     * @param rowIndex representing the row containing the MensaMealWithDate object to be added to history.
     */
    private void addMealToHistory(int rowIndex) {
        String[] columnsArray = {"Name", "Datum", "Preis in €", "Linie", "KCal", "Proteine (in g)", "Kohlenhydrate (in g)",
                "Fett (in g)", "Vegetarisch?"};
        Object[][] mealHistory = view.getMainDisplay().getMealHistoryView().updateHistoryTable(getMealFromTable(rowIndex));


        DefaultTableModel updatedTabelModel = new DefaultTableModel(
                mealHistory, columnsArray) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        view.getMainDisplay().getMealHistoryView().getHistoryTable().setModel(updatedTabelModel);


    }

    private MensaMealWithDate getMealFromTable(int rowIndex) {
        return view.getMainDisplay().getMealTable().getMealOfRow(rowIndex);
    }

    /**
     * Updates the nutrients display by adding the nutrients of the selected meal.
     * @param selectedMeal MensaMealWithDate object representing the selected meal.
     */
    private void updateNutrients(MensaMealWithDate selectedMeal) {
        Nutrients nutrients = view.getMainDisplay().getNutritientOverview().getNutrients();
        nutrients.addNutrients(selectedMeal);
    }

    /**
     * Updates nutrients table in the nutrient overview with the updated data.
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

