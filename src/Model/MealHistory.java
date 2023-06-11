package Model;

import edu.kit.aifb.atks.mensascraper.lib.MensaMeal;

import java.util.ArrayList;
import java.util.List;

public class MealHistory {
    private List<MensaMeal> mealHistory = new ArrayList<>();

    public void addMealToHistory(MensaMeal meal) {
        this.mealHistory.add(meal);
    }

    public void removeMealFromHistory(MensaMeal meal) {
        this.mealHistory.remove(meal);
    }
}
