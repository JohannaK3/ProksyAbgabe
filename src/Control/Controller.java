package Control;

import Model.MealHistoryModel;
import Model.Meals;
import View.StartPageFrame;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.time.LocalDate;
import java.util.Date;

public class Controller {

    private LocalDate selectedLocalDate;
    public static final LocalDate DEFAULT_DATE = LocalDate.now();

    private final Meals meals;
    private final MealHistoryModel mealHistory;
    private final StartPageFrame startPageFrame;



    public Controller() {
        this.selectedLocalDate = DEFAULT_DATE;
        this.meals = new Meals(selectedLocalDate);
        this.mealHistory = new MealHistoryModel();
        this.startPageFrame = new StartPageFrame();
    }

    /*
    public class dateSpinnerListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent event) {
            Date selectedDate = (Date) startPageFrame.getDateSpinner().getValue();
            selectedLocalDate = selectedDate;
        }
    }

     */

}
