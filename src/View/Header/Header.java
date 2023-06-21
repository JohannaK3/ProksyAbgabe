package View.Header;

import javax.swing.*;
import java.awt.*;

/**
 * Builds header section for programm's GUI
 *
 * @author johannakrickow (ugtfp)
 * @version 22.06.2023
 */
public class Header {

    private SelectDate selectDate;
    private ShowHistory showHistory;
    private ShowNutrients showNutrients;
    private final JPanel headerPanel;

    /**
     * Constructs a Header object.
     * It contains buttons to select date, show history and show nutrients.
     * The header is displayed using a JPanel with a GridLayout.
     * This constructor initializes the necessary components and adds them to the header panel.
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
