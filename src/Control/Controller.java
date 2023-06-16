package Control;

import Model.MealHistoryModel;
import Model.Meals;
import View.Header.ShowHistory;
import View.MainView;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;
import java.time.LocalDate;

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

    public SelectedDayButtonActionListener createSelectedDayButtonActionListener() {
        return new SelectedDayButtonActionListener();
    }

    public ShowHistoryButtonActionListener createShowHistoryButtonActionListener() {
        return new ShowHistoryButtonActionListener();
    }

    public ShowNutrientsButtonActionListener createShowNutrientsButtonActionListener() {
        return new ShowNutrientsButtonActionListener();
    }

    public class SelectedDayButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("i run");
            JLabel test = new JLabel("Test");
            view.getMainDisplay().getMealTable().getMealTablePanel().add(test);
            //TODO: why doesnt add work multiple times if i push button multiple times
        }
    }

    public class ShowHistoryButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public class ShowNutrientsButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
