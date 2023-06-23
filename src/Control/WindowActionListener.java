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

public class WindowActionListener extends WindowAdapter {

    private final MainView view;

    public WindowActionListener(MainView view) {
        this.view = view;
    }

    private final Path path = Paths.get("cache" + File.separator + "cache.csv");
    private static final String DELIMITER = ";";

    @Override
    public void windowOpened(WindowEvent e) {
        readFromFile();
    }

    @Override
    public void windowClosing(WindowEvent e) {
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
