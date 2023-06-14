package Control;

import Model.MealHistoryModel;
import Model.Meals;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.time.LocalDate;

public class Controller {

    private LocalDate selectedDate;

    public static final LocalDate DEFAULT_DATE = LocalDate.now();

    private final Meals meals;

    private final MealHistoryModel mealHistory;



    public Controller() {
        this.selectedDate = DEFAULT_DATE;
        this.meals = new Meals(selectedDate);
        this.mealHistory = new MealHistoryModel();
    }

    public class SpinnerListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent event) {

        }
    }

}
