package Control;

import Model.Meals;
import Model.MensaMealWithDate;
import Model.Nutrients;
import View.MainView;
import View.MealTable;
import View.NutritientOverview;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public WindowActionListener createWindowActionListener() {
        return new WindowActionListener();
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
     * MouseAdapter to remove meal from history and remove its nutrients.
     *
     * @author johannakrickow (ugtfp)
     * @version 22.06.2023
     */
    public class RemoveFromHistoryMouseAdapter extends MouseAdapter {

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

    public class WindowActionListener extends WindowAdapter {

        private final Path path = Paths.get("cache" + File.separator + "cache.csv");
        private static final String DELIMITER = ";";

        @Override
        public void windowOpened(WindowEvent e) {
            System.out.println("Opened");
            readFromFile();
        }

        @Override
        public void windowClosing(WindowEvent e) {
            System.out.println("Closing");
            writeToFile();
        }

        private void writeToFile() {
            Object[][] mealHistory = view.getMainDisplay().getMealHistoryView().getTableData2();
            String header = "Name;Datum;Preis;Linie;KCal;Proteine;Kohlenhydrate;Fett;Typ" + System.lineSeparator();

            try(BufferedWriter writer = Files.newBufferedWriter(path)) {
                //writer.write(header);
                for (Object[] meal : mealHistory) {
                    String[] stringMeal = Arrays.stream(meal).toArray(String[]::new);
                    String mealString = String.join(DELIMITER, stringMeal);
                    mealString += System.lineSeparator();
                    writer.write(mealString);
                }

            } catch (IOException ioException) {
                System.err.println(ioException);
            }
        }

        private void readFromFile() {
            Object[][] historyMeals = getMealsFromCache();
            setHistoryWithCachedMeals(historyMeals);

            updateNutrientsFromCache(historyMeals);
        }

        private Object[][] getMealsFromCache() {

            List<List<Object>> mealList = new ArrayList<>();
            try (BufferedReader reader = Files.newBufferedReader(path)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    List<Object> columns = List.of(line.split(DELIMITER));
                    mealList.add(columns);
                }

                return mealList.stream().map(meal -> meal.toArray(new Object[0])).toArray(Object[][]::new);

            } catch (IOException ex) {
                ex.printStackTrace();
                return new Object[0][0];
            }
        }

        private void setHistoryWithCachedMeals(Object[][] historyMeals) {
            String[] columnsArray = {"Name", "Datum", "Preis in €", "Linie", "KCal", "Proteine (in g)", "Kohlenhydrate (in g)",
                    "Fett (in g)", "Vegetarisch?"};

            DefaultTableModel updatedTabelModel = new DefaultTableModel(
                    historyMeals, columnsArray) {
                @Override
                public boolean isCellEditable(int row, int col) {
                    return false;
                }
            };

            view.getMainDisplay().getMealHistoryView().getHistoryTable().setModel(updatedTabelModel);
        }
        private void updateNutrientsFromCache(Object[][] historyMeals) {
            NutritientOverview nutritientOverview = view.getMainDisplay().getNutritientOverview();
            Nutrients nutrients = nutritientOverview.getNutrients();

            nutrients.updateNutrientsFromCache(historyMeals);

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
}
