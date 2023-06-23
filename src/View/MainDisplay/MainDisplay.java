package View.MainDisplay;

import View.MealHistoryView;
import View.MealTable;
import View.NutritientOverview;
import javax.swing.*;
import java.awt.*;

/**
 * MainDisplay represents the main display of the application.
 * It contains components for displaying the meal table, meal history, and nutrient overview.
 */
public class MainDisplay {

    private MealTable mealTable;
    private MealHistoryView mealHistoryView;
    private NutritientOverview nutritientOverview;

    private final JPanel mainDisplayPanel;

    private final CardLayout cardLayout;

    /**
     * Constructs a new MainDisplay object.
     * It initializes the meal table, meal history, and nutrient overview components.
     * The main display panel uses a card layout to switch between different views.
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
