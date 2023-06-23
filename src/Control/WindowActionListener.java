package Control;

import Model.Nutrients;
import View.MainView;
import View.NutritientOverview;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * WindowActionListener is an implementation of the WindowAdapter class. It handles window-related events for MainView.
 * It performs actions when the window is opened or closed, such as reading from and writing to a file.
 *
 * @author johannakrickow (ugtfp)
 * @version 23.06.2023
 */
public class WindowActionListener extends WindowAdapter {

    private final MainView view;
    private final String[] colArr = {"Attribut", "Kummulierter Wert"};
    private final String[] columnsArray = {"Name", "Datum", "Preis in €", "Linie", "KCal", "Proteine (in g)",
            "Kohlenhydrate (in g)", "Fett (in g)", "Vegetarisch?"};
    private final Path path = Paths.get("cache" + File.separator + "cache.csv");
    private static final String DELIMITER = ";";

    public WindowActionListener(MainView view) {
        this.view = view;
    }

    /**
     * Called when the window is opened. Reads data from the cache file and updates the view accordingly.
     * @param e WindowEvent that occurred.
     */
    @Override
    public void windowOpened(WindowEvent e) {
        readFromFile();
    }

    /**
     * Called when the window is closing. Writes data to the cache file.
     * @param e The WindowEvent that occurred.
     */
    @Override
    public void windowClosing(WindowEvent e) {
        writeToFile();
    }

    /**
     * Writes meal history data to the cache file.
     * Catches exceptions when file cannot be written.
     */
    private void writeToFile() {
        Object[][] mealHistory = view.getMainDisplay().getMealHistoryView().getTableData2();

        try(BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (Object[] meal : mealHistory) {
                String[] stringMeal = Arrays.stream(meal).toArray(String[]::new);
                String mealString = String.join(DELIMITER, stringMeal);
                mealString += System.lineSeparator();
                writer.write(mealString);
            }

        } catch (IOException ioException) {
            System.err.println(ioException);
            new WritingFailedOptionPane(view.getMainDisplay().getMainDisplayPanel(), ioException);
        }
    }

    /**
     * Reads meal history data from the cache file and updates the view.
     */
    private void readFromFile() {
        Object[][] historyMeals = getMealsFromCache();
        setHistoryWithCachedMeals(historyMeals);
        updateNutrientsFromCache(historyMeals);
    }

    /**
     * Retrieves meal history data from the cache file.
     * Catches exception when file cannot be read.
     * @return array of objects representing the meal history data.
     */
    private Object[][] getMealsFromCache() {
        List<List<Object>> mealList = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                List<Object> columns = List.of(line.split(DELIMITER));
                mealList.add(columns);
            }
            return mealList.stream().map(meal -> meal.toArray(new Object[0])).toArray(Object[][]::new);

        } catch (IOException ioException) {
            ioException.printStackTrace();
            new ReadingFailedOptionPane(view.getMainDisplay().getMainDisplayPanel(), ioException);
            return new Object[0][0];
        }
    }

    /**
     * Sets the meal history table model with the cached meals.
     * @param historyMeals array containing cached meal history data.
     */
    private void setHistoryWithCachedMeals(Object[][] historyMeals) {
        DefaultTableModel updatedTabelModel = new DefaultTableModel(
                historyMeals, columnsArray) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        view.getMainDisplay().getMealHistoryView().getHistoryTable().setModel(updatedTabelModel);
    }

    /**
     * Updates the nutrients view table model with the cached data.
     * @param historyMeals array containing cached meal history data.
     */
    private void updateNutrientsFromCache(Object[][] historyMeals) {
        NutritientOverview nutritientOverview = view.getMainDisplay().getNutritientOverview();
        Nutrients nutrients = nutritientOverview.getNutrients();

        nutrients.updateNutrientsFromCache(historyMeals);

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
