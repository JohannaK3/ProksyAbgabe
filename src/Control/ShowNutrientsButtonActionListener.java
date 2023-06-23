package Control;

import View.MainView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ButtonListener for showNutrients Button in showNutrients.
 * Shows NutrientOverview on MainDisplay.
 *
 * {@link ShowNutrients#showNutrientsButton}
 *
 * @author johannakrickow (ugtfp)
 * @version 23.06.2023
 */
public class ShowNutrientsButtonActionListener implements ActionListener {

    private final MainView view;

    public ShowNutrientsButtonActionListener(MainView view) {
        this.view = view;
    }

    /**
     * Is called when showNutrientsButton is pushed.
     * Shows nutrients overview on display panel.
     * @param e ActionEvent object that triggered the action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        view.getMainDisplay().getCardLayout().show(view.getMainDisplay().getMainDisplayPanel(), "3");
    }
}
