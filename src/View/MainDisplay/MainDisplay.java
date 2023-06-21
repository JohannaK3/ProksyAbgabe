package View.MainDisplay;

import View.MealHistoryView;
import View.MealTable;
import View.NutritientOverview;
import javax.swing.*;
import java.awt.*;

/**
 * Space where meal table, meal history and nutrients overview is shown.
 * Manages which of the above is shown.
 *
 * @author johannakrickow (ugtfp)
 * @version 22.06.2023
 */
public class MainDisplay {

    private MealTable mealTable;
    private MealHistoryView mealHistoryView;
    private NutritientOverview nutritientOverview;

    private final JPanel mainDisplayPanel;

    private final CardLayout cardLayout;

    /**
     * Constructs a MainDisplay object.
     * Contains components such as the meal table, meal history view, nutrient overview.
     * This constructor initializes the necessary components and adds them to the main display panel using a card layout.
     */
    public MainDisplay() {

        mealTable = new MealTable();
        mealHistoryView = new MealHistoryView();
        nutritientOverview = new NutritientOverview();
        cardLayout = new CardLayout();

        mainDisplayPanel = new JPanel(cardLayout);

        mainDisplayPanel.add(mealTable.getMealTableBackgroundPanel(), "1");
        mainDisplayPanel.add(mealHistoryView.getHistoryBackgroundPanel(), "2");
        mainDisplayPanel.add(nutritientOverview.getNutrientsBackgroundPanel(), "3");
    }

    public JPanel getMainDisplayPanel() {
        return mainDisplayPanel;
    }

    public MealTable getMealTable() {
        return mealTable;
    }

    public MealHistoryView getMealHistoryView() {
        return mealHistoryView;
    }

    public NutritientOverview getNutritientOverview() {
        return nutritientOverview;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }
}
