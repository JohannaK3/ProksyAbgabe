package Control;

import Model.Meals;
import View.MainView;
import View.MealTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

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

    public class SelectedDayButtonActionListener implements ActionListener {

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

        private void updateMeals(MealTable mealTable) {
            LocalDate selectedDate = view.getHeader().getSelectDate().getSelectedDateFromSpinner();
            Meals meals = mealTable.getMeals();
            meals.updateCurrentMeals(selectedDate);
            mealTable.updateDataArray();
        }

        private void updateTable(MealTable mealTable) {
            //TODO: colArr is same in MealTable, how can I use the same one?
            String[] colArr = {"Name", "Preis", "Linie"};
            DefaultTableModel updatedTabelModel = new DefaultTableModel(
                    mealTable.getDataArray(), colArr);
            mealTable.getMealsJTable().setModel(updatedTabelModel);
        }
    }

    public class ShowHistoryButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public class ShowNutrientsButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
