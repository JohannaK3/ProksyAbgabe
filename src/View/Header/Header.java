package View.Header;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class Header {

    private final JPanel headerPanel;

    public Header() {

        headerPanel = new JPanel(new GridLayout(0, 3, 30, 0));

        headerPanel.add(SelectDate.createSelectedDatePanel());
        headerPanel.add(ShowHistory.createShowHistoryPanel());
        headerPanel.add(ShowNutrients.createShowNutrientsPanel());
    }

    public static JPanel createHeaderPanel() {
        Header header = new Header();
        return header.headerPanel;
    }



}
