package Control;

import Model.MealHistory;
import Model.Meals;
import Model.MensaMealWithDate;
import Model.Nutrients;
import View.Header.SelectDate;
import View.Header.ShowHistory;
import View.Header.ShowNutrients;
import View.MainView;
import View.MealHistoryView;
import View.MealTable;
import View.NutritientOverview;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

/**
 * Controller listens to user actions and changes model and view according to this
 *
 * @author johannakrickow (ugtfp)
 * @version 22.06.2023
 */
public class Controller {

    private final LocalDate selectedLocalDate;
    private static final LocalDate DEFAULT_DATE = LocalDate.now();

    private MainView view;



    public Controller() {
        this.selectedLocalDate = DEFAULT_DATE;
    }

    public void setView(MainView view) {
        this.view = view;
    }


    public SelectedDayButtonActionListener createSelectedDayButtonActionListener() {
        return new SelectedDayButtonActionListener();
    }

    public ShowHistoryButtonActionListener createShowHistoryButtonActionListener() {
        return new ShowHistoryButtonActionListener();
    }

    public ShowNutrientsButtonActionListener createShowNutrientsButtonActionListener() {
        return new ShowNutrientsButtonActionListener();
    }

    public AddMealMouseAdapter createMealMouseAdapter() {
        return new AddMealMouseAdapter();
    }

    public RemoveFromHistoryMouseAdapter createHistoryMouseAdapter() {
        return new RemoveFromHistoryMouseAdapter();
    }

    /**
     * ButtonListener for button to select date in SelectDate
     *
     * {@link SelectDate#selectedDateButton}
     *
     * @author johannakrickow (ugtfp)
     * @version 22.06.2023
     */
    public class SelectedDayButtonActionListener implements ActionListener {

        /**
         * Is called when selectDayButton is pushed.
         * @param e representing event that triggerd the action.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            //shows selected date above table
            String date = view.getHeader().getSelectDate().getDateInStringFormat();
            view.getMainDisplay().getMealTable().getSelectedDateLabel().setText(date);

            MealTable mealTable = view.getMainDisplay().getMealTable();
            //shows meal table for selected date
            updateMeals(mealTable);
            updateTable(mealTable);
        }

        /**
         * Updates the meals in the meal table based on the selected date.
         * @param mealTable MealTable object representing the list of meals to update.
         */
        private void updateMeals(MealTable mealTable) {
            LocalDate selectedDate = view.getHeader().getSelectDate().getSelectedDateFromSpinner();
            Meals meals = mealTable.getMeals();
            meals.updateCurrentMeals(selectedDate);
            mealTable.updateDataArray();
        }

        /**
         * Updates the table in the meal display with the updated data.
         * Shows meal table on display panel.
         * @param mealTable MealTable object representing the table to update.
         */
        private void updateTable(MealTable mealTable) {
            view.getMainDisplay().getCardLayout().show(view.getMainDisplay().getMainDisplayPanel(), "1");
            //TODO: colArr is same in MealTable, how can I use the same one?
            String[] colArr = {"Name", "Preis in €", "Linie"};
            DefaultTableModel updatedTabelModel = new DefaultTableModel(
                    mealTable.getDataArray(), colArr) {
                @Override
                public boolean isCellEditable(int row, int col) {
                    return false;
                }
            };
            mealTable.getMealsJTable().setModel(updatedTabelModel);
        }
    }

    /**
     * MouseAdapter to add meal to history and add nutritions
     *
     * @author johannakrickow (ugtfp)
     * @version 22.06.2023
     */
    public class AddMealMouseAdapter extends MouseAdapter {

        private MealHistory mealHistory;
        private MealHistoryView mealHistoryView;

        private MensaMealWithDate selectedMeal;

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
                selectedMeal = view.getMainDisplay().getMealTable().getMealOfRow(rowIndex);
                addMealToHistory(selectedMeal);

                updateNutrients(selectedMeal);
                updateNutrientsTable(view.getMainDisplay().getNutritientOverview());
            }
        }

        /**
         * Adds new meal to history and history list, calls to update the table.
         * @param meal representing the MensaMealWithDate object to be added to hsitory.
         */
        private void addMealToHistory(MensaMealWithDate meal) {
            mealHistory = new MealHistory();
            mealHistory.addMealToList(meal);

            //mealHistory.getHistoryArray();
            //view.getMainDisplay().getMealHistoryView().updateHistoryTable(meal);
            updateHistoryTable(mealHistoryView);
        }

        /*
        private MensaMealWithDate getMealFromTable(int rowIndex) {
            return view.getMainDisplay().getMealTable().getMealOfRow(rowIndex);
        }
        */

        /**
         * Updates the nutrients display by adding the nutrients of the selected meal.
         * @param selectedMeal MensaMealWithDate object representing the selected meal.
         */
        private void updateNutrients(MensaMealWithDate selectedMeal) {
            Nutrients nutrients = view.getMainDisplay().getNutritientOverview().getNutrients();
            nutrients.addNutrients(selectedMeal);
        }

        /**
         * Updates history table in the meal history view with the updated data.
         * @param mealHistoryView MealHistoryView object representing the view containing the history table.
         */
        private void updateHistoryTable(MealHistoryView mealHistoryView) {

            //TODO: colArr is same in MealTable, how can I use the same one?
            String[] columnsArray = {"Name", "Datum", "Preis in €", "Linie", "KCal", "Proteine (in g)",
                    "Kohlenhydrate (in g)", "Fett (in g)"};
            DefaultTableModel updatedTabelModel = new DefaultTableModel(
                    mealHistory.getHistoryArray(), columnsArray) {
                @Override
                public boolean isCellEditable(int row, int col) {
                    return false;
                }
            };
            mealHistoryView.getHistoryTable().setModel(updatedTabelModel);
        }

        /**
         * Updates nutrients table in the nutrient overview with the updated data.
         * @param nutritientOverview NutrientOverview object representing the view containing the nutrients table.
         */
        private void updateNutrientsTable(NutritientOverview nutritientOverview) {
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

    /**
     * ButtonListener for showHistory Button in ShowHistory.
     * Shows MealHistoryView on MainDisplay.
     *
     * {@link ShowHistory#showHistoryButton}
     *
     * @author johannakrickow (ugtfp)
     * @version 22.06.2023
     */
    public class ShowHistoryButtonActionListener implements ActionListener {

        /**
         * Is called when showHistoryButton is pushed.
         * Shows meal history on display panel.
         * @param e ActionEvent object that triggered the action.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getMainDisplay().getCardLayout().show(view.getMainDisplay().getMainDisplayPanel(), "2");
        }
    }

    /**
     * MouseAdapter to remove meal from history and remove its nutritions
     *
     * @author johannakrickow (ugtfp)
     * @version 22.06.2023
     */
    public class RemoveFromHistoryMouseAdapter extends MouseAdapter {

        MensaMealWithDate selectedMeal;

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
                view.getMainDisplay().getMealHistoryView().removeRowFromHistory(rowIndex);

                selectedMeal = getMealFromTable(rowIndex);
                updateNutrients(selectedMeal);
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
         * @param selectedMeal MensaMealWithDate object representing the selected meal.
         */
        private void updateNutrients(MensaMealWithDate selectedMeal) {
            Nutrients nutrients = view.getMainDisplay().getNutritientOverview().getNutrients();
            nutrients.removeNutrients(selectedMeal);
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

    /**
     * ButtonListener for showNutrients Button in showNutrients.
     * Shows NutrientOverview on MainDisplay.
     *
     * {@link ShowNutrients#showNutrientsButton}
     *
     * @author johannakrickow (ugtfp)
     * @version 22.06.2023
     */
    public class ShowNutrientsButtonActionListener implements ActionListener {

        /**
         * Is called when showNutrientsButton is pushed.
         * Shows nutrients overview on display panel.
         * @param e ActionEvent object that triggered the action.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getMainDisplay().getCardLayout().show(view.getMainDisplay().getMainDisplayPanel(), "3");
        }
    }
}
