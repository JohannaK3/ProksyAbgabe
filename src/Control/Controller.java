package Control;

import Model.MealHistoryModel;
import Model.Meals;
import View.MainView;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.time.LocalDate;
import java.util.Date;

public class Controller {

    private final LocalDate selectedLocalDate;
    public static final LocalDate DEFAULT_DATE = LocalDate.now();

    private MainView view;



    public Controller() {
        this.selectedLocalDate = DEFAULT_DATE;

    }

    public void setView(MainView view) {
        this.view = view;
    }

}
