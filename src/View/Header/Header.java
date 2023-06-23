package View.Header;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the header section of the GUI.
 * Contains components related to selecting a date, showing meal history, and displaying nutrient information.
 * The header panel is a container that holds these components in a grid layout.
 *
 * @author johannakrickow (ugtfp)
 * @version 23.06.2023
 */
public class Header {

    private SelectDate selectDate;
    private ShowHistory showHistory;
    private ShowNutrients showNutrients;
    private final JPanel headerPanel;

    /**
     * Constructs a new Header object and initializes the components.
     * The header panel is created with a grid layout to hold the components.
     */
    public Header() {

        selectDate = new SelectDate();
        showHistory = new ShowHistory();
        showNutrients = new ShowNutrients();

        headerPanel = new JPanel(new GridLayout(0, 3, 30, 0));

        headerPanel.add(selectDate.getDateSpinnerPanel());
        headerPanel.add(showHistory.getShowHistoryPanel());
        headerPanel.add(showNutrients.getShowNutrientsPanel());
    }

    public JPanel getHeaderPanel() {
        return headerPanel;
    }

    public SelectDate getSelectDate() {
        return selectDate;
    }

    public ShowHistory getShowHistory() {
        return showHistory;
    }

    public ShowNutrients getShowNutrients() {
        return showNutrients;
    }
}
