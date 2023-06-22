package Model;

import java.util.ArrayList;
import java.util.List;

public class History {

    private final List<MensaMealWithDate> mealHistory;

    public History() {
        mealHistory = new ArrayList<>();
    }

    public void addMealToHistory(MensaMealWithDate meal) {
        mealHistory.add(meal);
    }


}
