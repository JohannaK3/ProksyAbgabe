package View.Header;

import javax.swing.*;;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

/**
 * Builds section in programm's GUI to select date for meal list to be shown.
 * Is built into the GUI's header
 *
 * @author johannakrickow (ugtfp)
 * @version 22.06.2023
 */
public class SelectDate {

    private final LocalDate currentLocalDate = LocalDate.now();
    private final LocalDate spinnerEndDate = currentLocalDate.plusDays(7);

    private final JPanel dateSpinnerPanel;

    private final JLabel selectDateInSpinnerLabel;
    private JSpinner dateSpinner;
    private JLabel emptyLabel;
    private final JButton selectedDateButton;

    private SpinnerDateModel dateSpinnerModel;
    private JSpinner.DateEditor dateSpinnerEditor;

    /**
     * Constructs a SelectDate object.
     * It consists of a date spinner and a button for selecting a date.
     * This constructor initializes the necessary components and adds them to the date spinner panel.
     */
    public SelectDate() {
        dateSpinnerPanel = new JPanel(new GridLayout(2, 1));

        selectDateInSpinnerLabel = new JLabel("Datum wählen: ", SwingConstants.CENTER);

        createWholeDateSpinner();

        emptyLabel = new JLabel("");
        selectedDateButton = new JButton("Menu anzeigen");


        dateSpinnerPanel.add(selectDateInSpinnerLabel);
        dateSpinnerPanel.add(dateSpinner);
        dateSpinnerPanel.add(emptyLabel);
        dateSpinnerPanel.add(selectedDateButton);
    }

    /**
     * Creates the JSPinner to select a date.
     */
    private void createWholeDateSpinner() {
        dateSpinnerModel = new SpinnerDateModel(Date.from(currentLocalDate.atStartOfDay(
                ZoneId.systemDefault()).toInstant()), null, null, Calendar.DAY_OF_MONTH);
        dateSpinner = new JSpinner(dateSpinnerModel);
        dateSpinnerEditor = new JSpinner.DateEditor(dateSpinner, "dd.MM.yyyy");
        dateSpinner.setEditor(dateSpinnerEditor);
        dateSpinnerModel.setEnd(Date.from(spinnerEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        dateSpinnerModel.setStart(Date.from(currentLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }

    public JPanel getDateSpinnerPanel() {
        return dateSpinnerPanel;
    }

    public JButton getSelectedDateButton() {
        return selectedDateButton;
    }

    public LocalDate getSelectedDateFromSpinner() {
        Date date = (Date) dateSpinner.getValue();
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public String getDateInStringFormat() {
        LocalDate localDate = getSelectedDateFromSpinner();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return localDate.format(formatter);
    }
}
