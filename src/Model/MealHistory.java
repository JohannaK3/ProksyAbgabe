package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MealHistory {

    private final ArrayList<MensaMealWithDate> historyList;
    private MensaMealWithDate[] historyArray;
    private Object[][] historyArray2D;

    public MealHistory() {
        historyList = new ArrayList<>();

    }

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
