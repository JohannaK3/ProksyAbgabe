package Control;

import Model.Meals;
import Model.MensaMealWithDate;
import Model.Nutrients;
import View.MainView;
import View.MealTable;
import View.NutritientOverview;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

    public AddMealMouseAdapter createMealMouseAdapter() {
        return new AddMealMouseAdapter();
    }

    public RemoveFromHistoryMouseAdapter createHistoryMouseAdapter() {
        return new RemoveFromHistoryMouseAdapter();
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

    public class AddMealMouseAdapter extends MouseAdapter {

        private MensaMealWithDate selectedMeal;
        @Override
        public void mouseClicked(MouseEvent event) {

            if(event.getClickCount() == 2) {
                int rowIndex = view.getMainDisplay().getMealTable().getMealsJTable().getSelectedRow();
                addMealToHistory(rowIndex);

                selectedMeal = getMealFromTable(rowIndex);
                updateNutrients(selectedMeal);
                updateTable(view.getMainDisplay().getNutritientOverview());
                // töalksdjf
            }
        }

        private void addMealToHistory(int rowIndex) {
            view.getMainDisplay().getMealHistoryView().updateHistoryTable(getMealFromTable(rowIndex));
        }

        private MensaMealWithDate getMealFromTable(int rowIndex) {
            return view.getMainDisplay().getMealTable().getMealOfRow(rowIndex);
        }

        private void updateNutrients(MensaMealWithDate selectedMeal) {
            Nutrients nutrients = view.getMainDisplay().getNutritientOverview().getNutrients();
            nutrients.addNutrients(selectedMeal);
        }
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

    public class ShowHistoryButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.getMainDisplay().getCardLayout().show(view.getMainDisplay().getMainDisplayPanel(), "2");
        }
    }

    public class RemoveFromHistoryMouseAdapter extends MouseAdapter {

        MensaMealWithDate selectedMeal;
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

        private MensaMealWithDate getMealFromTable(int rowIndex) {
            return view.getMainDisplay().getMealHistoryView().getMealOfRow(rowIndex);
        }

        private void updateNutrients(MensaMealWithDate selectedMeal) {
            Nutrients nutrients = view.getMainDisplay().getNutritientOverview().getNutrients();
            nutrients.removeNutrients(selectedMeal);
        }

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

    public class ShowNutrientsButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.getMainDisplay().getCardLayout().show(view.getMainDisplay().getMainDisplayPanel(), "3");
        }
    }
}
