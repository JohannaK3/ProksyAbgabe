package Control;

import View.MainView;

/**
 * Controller contains methods to create listeners/adapters.
 * Passes view to new listener/adapter objects.
 *
 * @author johannakrickow (ugtfp)
 * @version 23.06.2023
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
