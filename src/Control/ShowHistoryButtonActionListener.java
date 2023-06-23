package Control;

import View.MainView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ButtonListener for showHistory Button in ShowHistory.
 * Shows MealHistoryView on MainDisplay.
 *
 * {@link ShowHistory#showHistoryButton}
 *
 * @author johannakrickow (ugtfp)
 * @version 22.06.2023
 */
public class ShowHistoryButtonActionListener implements ActionListener {

    private final MainView view;

    public ShowHistoryButtonActionListener(MainView view) {
        this.view = view;
    }

    /**
     * Is called when showHistoryButton is pushed.
     * Shows meal history on display panel.
     * @param e ActionEvent object that triggered the action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        view.getMainDisplay().getCardLayout().show(view.getMainDisplay().getMainDisplayPanel(), "2");
    }
}
