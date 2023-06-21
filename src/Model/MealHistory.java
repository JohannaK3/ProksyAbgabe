package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Manages meals in the meal history.
 *
 * @author johannakrickow (ugtfp)
 * @version 22.06.2023
 */
public class MealHistory {

    private final ArrayList<MensaMealWithDate> historyList;
    private MensaMealWithDate[] historyArray;
    private Object[][] historyArray2D;

    public MealHistory() {
        historyList = new ArrayList<>();

    }

    /**
     * Adds a MensaMealWithDate object to the history list and updates the list accordingly.
     * Sorts history list by date.
     * @param meal MensaMealWithDate object to add to the history list.
     */
    public void addMealToList(MensaMealWithDate meal) {
        historyList.add(meal);

        Collections.sort(historyList, new Comparator<MensaMealWithDate>() {
            @Override
            public int compare(MensaMealWithDate meal1, MensaMealWithDate meal2) {
                return meal1.getDate().compareTo(meal2.getDate());
            }
        });
        convertToArray(historyList);
    }

    /**
     * Converts the history list to arrays for better working with table components of view.
     * Creates 2D array to pass into table model.
     * @param historyList ArrayList of MensaMealWithDate objects representing the history list.
     */
    private void convertToArray(ArrayList<MensaMealWithDate> historyList) {
        historyArray = historyList.toArray(new MensaMealWithDate[historyList.size()]);
        historyArray2D = new MensaMealWithDate[historyArray.length][8];

        for (int i = 0; i < historyArray.length; i++) {
            historyArray2D[i] = historyList.get(i).getExtendedMealInfo();
        }

    }

    public Object[][] getHistoryArray() {
        return historyArray2D.clone();
    }


}
