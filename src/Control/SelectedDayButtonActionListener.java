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
 * @version 22.06.2023
 */
public class SelectedDayButtonActionListener implements ActionListener {

    private final MainView view;

    public SelectedDayButtonActionListener(MainView view) {
        this.view = view;
    }

    /**
     * Is called when selectDayButton is pushed.
     * @param e representing event that triggerd the action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        LocalDate currentdate = view.getHeader().getSelectDate().getCurrentLocalDate();
        LocalDate selectedDate = view.getHeader().getSelectDate().getSelectedDateFromSpinner();
        LocalDate maxDate = view.getHeader().getSelectDate().getSpinnerEndDate();
        String date = view.getHeader().getSelectDate().getDateInStringFormat();

        if (selectedDate.isEqual(currentdate) | selectedDate.isAfter(currentdate) && selectedDate.isBefore(maxDate)) {
        //shows selected date above table
        view.getMainDisplay().getMealTable().getSelectedDateLabel().setText(date);

        MealTable mealTable = view.getMainDisplay().getMealTable();
        //shows meal table for selected date
        updateMeals(mealTable);
        updateTable(mealTable);
        } else {
           //TODO: throw something (JOptionPane)
        }
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
