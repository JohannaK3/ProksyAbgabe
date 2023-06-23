package Control;

import Model.Meals;
import View.MainView;
import View.MealTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

/**
 * ButtonListener for button to select date in SelectDate
 *
 * {@link SelectDate#selectedDateButton}
 *
 * @author johannakrickow (ugtfp)
 * @version 23.06.2023
 */
public class SelectedDayButtonActionListener implements ActionListener {

    private final MainView view;
    private final String[] colArr = {"Name", "Preis in €", "Linie"};

    public SelectedDayButtonActionListener(MainView view) {
        this.view = view;
    }

    /**
     * Is called when selectDayButton is pushed.
     * Get's selected Date, shows it in the view's header.
     * Updates table with meals for the selected date.
     * JOptionPane is created an message is shown when unvalid date is selected.
     * @param e representing event that triggerd the action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        LocalDate currentdate = view.getHeader().getSelectDate().getCurrentLocalDate();
        LocalDate selectedDate = view.getHeader().getSelectDate().getSelectedDateFromSpinner();
        LocalDate maxDate = view.getHeader().getSelectDate().getSpinnerEndDate();
        String date = view.getHeader().getSelectDate().getDateInStringFormat();

        //TODO: fix this
        //if (selectedDate.isEqual(currentdate) | selectedDate.isAfter(currentdate) && selectedDate.isBefore(maxDate)) {
        if (selectedDate != currentdate) {
            new DateOptionPane(view.getMainDisplay().getMealTable().getMealTableBackgroundPanel());

        }
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
     * Updates the table model in the meal display with the updated data.
     * Shows meal table on display panel.
     * @param mealTable MealTable object representing the table to update.
     */
    private void updateTable(MealTable mealTable) {
        view.getMainDisplay().getCardLayout().show(view.getMainDisplay().getMainDisplayPanel(), "1");

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
