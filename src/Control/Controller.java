package Control;

import View.MainView;

import javax.swing.*;

/**
 * Controller listens to user actions and changes model and view according to this
 *
 * @author johannakrickow (ugtfp)
 * @version 22.06.2023
 */
public class Controller {

    private final MainView view;

    public Controller(MainView view) {
        this.view = view;
    }

    public SelectedDayButtonActionListener createSelectedDayButtonActionListener() {
        return new SelectedDayButtonActionListener(view);
    }

    public ShowHistoryButtonActionListener createShowHistoryButtonActionListener() {
        return new ShowHistoryButtonActionListener(view);
    }

    public ShowNutrientsButtonActionListener createShowNutrientsButtonActionListener() {
        return new ShowNutrientsButtonActionListener(view);
    }

    public AddMealMouseAdapter createMealMouseAdapter() {
        return new AddMealMouseAdapter(view);
    }

    public RemoveFromHistoryMouseAdapter createHistoryMouseAdapter() {
        return new RemoveFromHistoryMouseAdapter(view);
    }

    public WindowActionListener createWindowActionListener() {
        return new WindowActionListener(view);
    }

}
