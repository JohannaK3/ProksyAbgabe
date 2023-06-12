package Model;

import Control.Controller;
import edu.kit.aifb.atks.mensascraper.lib.MensaMeal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MealHistory {

    private List<MensaMealWithDate> mealHistory;

    public MealHistory() {
        this.mealHistory = new ArrayList<>();
    }

    //adds meal of selected day to history
    public void addMealOfSelectedDayToHistory(MensaMeal meal, LocalDate selectedDate) {
        this.mealHistory.add(new MensaMealWithDate(meal, selectedDate));
    }

    public void removeMealFromHistory(MensaMeal meal) {
        this.mealHistory.remove(meal);
    }
}
