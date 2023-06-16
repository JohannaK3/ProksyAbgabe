package View.Header;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class Header {

    private SelectDate selectDate;
    private ShowHistory showHistory;
    private ShowNutrients showNutrients;
    private final JPanel headerPanel;

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
