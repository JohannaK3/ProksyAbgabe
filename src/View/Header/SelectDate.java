package View.Header;

import javax.swing.*;;
import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

public class SelectDate {

    private final LocalDate currentLocalDate = LocalDate.now();
    private final LocalDate spinnerEndDate = currentLocalDate.plusDays(7);

    private final JPanel dateSpinnerPanel;

    private final JLabel selectDateInSpinnerLabel;
    private final JSpinner dateSpinner;
    private JLabel emptyLabel;
    private final JButton getMenuForSelectedDayButton;

    private SpinnerDateModel dateSpinnerModel;
    private final JSpinner.DateEditor dateSpinnerEditor;



    
    public SelectDate() {
        dateSpinnerPanel = new JPanel(new GridLayout(2, 1));

        selectDateInSpinnerLabel = new JLabel("Datum wählen: ", SwingConstants.CENTER);
        dateSpinnerModel = new SpinnerDateModel(Date.from(currentLocalDate.atStartOfDay(
                ZoneId.systemDefault()).toInstant()), null, null, Calendar.DAY_OF_MONTH);
        dateSpinner = new JSpinner(dateSpinnerModel);
        dateSpinnerEditor = new JSpinner.DateEditor(dateSpinner, "dd.MM.yyyy");
        dateSpinner.setEditor(dateSpinnerEditor);
        dateSpinnerModel.setEnd(Date.from(spinnerEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));

        emptyLabel = new JLabel("");
        getMenuForSelectedDayButton = new JButton("Menu anzeigen");


        dateSpinnerPanel.add(selectDateInSpinnerLabel);
        dateSpinnerPanel.add(dateSpinner);
        dateSpinnerPanel.add(emptyLabel);
        dateSpinnerPanel.add(getMenuForSelectedDayButton);
    }

    public static JPanel createSelectedDatePanel() {
        SelectDate selectDate = new SelectDate();
        return selectDate.dateSpinnerPanel;
    }
}
