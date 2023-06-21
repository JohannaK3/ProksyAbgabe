package View.MainDisplay;

import View.MealHistoryView;
import View.MealTable;
import View.NutritientOverview;
import javax.swing.*;
import java.awt.*;

public class MainDisplay {

    private MealTable mealTable;
    private MealHistoryView mealHistoryView;
    private NutritientOverview nutritientOverview;

    private final JPanel mainDisplayPanel;

    private final CardLayout cardLayout;


    public MainDisplay() {

        mealTable = new MealTable();
        mealHistoryView = new MealHistoryView();
        nutritientOverview = new NutritientOverview();
        cardLayout = new CardLayout();

        mainDisplayPanel = new JPanel(cardLayout);

        mainDisplayPanel.add(mealTable.getMealTableBackgroundPanel(), "1");
        mainDisplayPanel.add(mealHistoryView.getHistoryBackgroundPanel(), "2");
        mainDisplayPanel.add(nutritientOverview.getNutrientsBackgroundPanel(), "3");
        //TODO: replace mealTable with MealHistoryView when button is clicked
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
