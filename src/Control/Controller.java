package Control;

import Model.MealHistory;
import Model.Meals;

import java.time.LocalDate;

public class Controller {

    private LocalDate selectedDate;

    public static final LocalDate DEFAULT_DATE = LocalDate.now();

    private final Meals meals;

    private final MealHistory mealHistory;

    public Controller() {
        this.selectedDate = DEFAULT_DATE;
        this.meals = new Meals(selectedDate);
        this.mealHistory = new MealHistory();
    }

}
