package Control;

import Model.Meals;
import Model.MensaMealWithDate;
import Model.Nutrients;
import View.MainView;
import View.MealTable;
import View.NutritientOverview;
import edu.kit.aifb.atks.mensascraper.lib.MensaMeal;

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

    public AddHistoryMouseAdapter createHistoryMouseAdapter() {
        return new AddHistoryMouseAdapter();
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

        private Nutrients nutrients = new Nutrients();
        private double[] amountArr;
        private MensaMealWithDate selectedMeal;
        @Override
        public void mouseClicked(MouseEvent event) {

            if(event.getClickCount() == 2) {
                int rowIndex = view.getMainDisplay().getMealTable().getMealsJTable().getSelectedRow();
                view.getMainDisplay().getMealHistoryView().updateHistoryTable(getMealFromTable(rowIndex));

                selectedMeal = getMealFromTable(rowIndex);
                nutrients.updateAccumulatedNutrientsArray(nutrients.updateNutrients(selectedMeal));
                System.out.println(nutrients.getAccumulatedNutrientsArray().toString());

                //funktioniert
                updateTable(view.getMainDisplay().getNutritientOverview());
            }
        }

        //sehr wahrscheinlich unnötig
        private double[] getNutrientsOfMeal(MensaMealWithDate selectedMeal) {
            this.selectedMeal = selectedMeal;
            double mealKCal = selectedMeal.getMeal().getKcal();
            double mealProteins =  selectedMeal.getMeal().getProteins();
            double mealCarbs = selectedMeal.getMeal().getCarbs();
            double mealFat = selectedMeal.getMeal().getFat();
            //mealType = selectedMeal.getMeal().getType();
            double mealPrice = selectedMeal.getMeal().getPrice();

            amountArr = new double[] {mealKCal, mealProteins, mealCarbs,
                                        mealFat, mealPrice};
            return amountArr;
        }
        private MensaMealWithDate getMealFromTable(int rowIndex) {
            return view.getMainDisplay().getMealTable().getMealOfRow(rowIndex);
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
            nutritientOverview.getNutrientstable().setModel(updatedTabelModel);
        }
    }

    public class ShowHistoryButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.getMainDisplay().getCardLayout().show(view.getMainDisplay().getMainDisplayPanel(), "2");
        }
    }

    public class AddHistoryMouseAdapter extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent event) {

            if(event.getClickCount() == 2) {
                int rowIndex = view.getMainDisplay().getMealHistoryView().getHistoryTable().getSelectedRow();
                view.getMainDisplay().getMealHistoryView().removeRowFromHistory(rowIndex);
            }
        }

        private MensaMealWithDate getMealFromTable(int rowIndex) {
            return view.getMainDisplay().getMealTable().getMealOfRow(rowIndex);
        }
    }

    public class ShowNutrientsButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.getMainDisplay().getCardLayout().show(view.getMainDisplay().getMainDisplayPanel(), "3");
        }
    }
}
