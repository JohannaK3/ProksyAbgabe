package View.Header;

import javax.swing.*;;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

/**
 * Represents a component in the GUI's header section for selecting a date.
 * It consists of a panel with a date spinner and a button that retrieves the menu for the selected day.
 *
 * @author johannakrickow (ugtfp)
 * @version 23.06.2023
 */
public class SelectDate {

    private final LocalDate currentLocalDate = LocalDate.now();
    private final LocalDate spinnerEndDate = currentLocalDate.plusDays(7);

    private final JPanel dateSpinnerPanel;
    private final JLabel selectDateInSpinnerLabel;
    private JLabel emptyLabel;
    private final JButton getMenuForSelectedDayButton;

    private JSpinner dateSpinner;
    private SpinnerDateModel dateSpinnerModel;
    private JSpinner.DateEditor dateSpinnerEditor;

    /**
     * Constructs a new SelectDate object.
     * It creates a panel with a date spinner and a button.
     */
    public SelectDate() {
        dateSpinnerPanel = new JPanel(new GridLayout(2, 1));

        selectDateInSpinnerLabel = new JLabel("Datum wählen: ", SwingConstants.CENTER);

        createWholeDateSpinner();

        emptyLabel = new JLabel("");
        getMenuForSelectedDayButton = new JButton("Menu anzeigen");

        dateSpinnerPanel.add(selectDateInSpinnerLabel);
        dateSpinnerPanel.add(dateSpinner);
        dateSpinnerPanel.add(emptyLabel);
        dateSpinnerPanel.add(getMenuForSelectedDayButton);
    }

    /**
     * Creates the date spinner with the required configurations.
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

    public JButton getGetMenuForSelectedDayButton() {
        return getMenuForSelectedDayButton;
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

    public LocalDate getCurrentLocalDate() {
        return currentLocalDate;
    }

    public LocalDate getSpinnerEndDate() {
        return spinnerEndDate;
    }
}
