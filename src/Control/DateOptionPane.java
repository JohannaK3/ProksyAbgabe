package Control;

import javax.swing.*;

/**
 * DateOptionPane provides a dialog box to display an error message for an invalid or unavailable date.
 *
 * @author johannakrickow (ugtfp)
 * @version 23.06.2023
 */
public class DateOptionPane {

    private static final String WRONG_DATE = "Für das gewählte Datum liegen keine Daten vor. Bitte " +
            "ein anderes Datum wählen.";

    /**
     * Constructs a new DateOptionPane object and displays an error message dialog box.
     *
     * @param component to which the dialog box is attached.
     */
    public DateOptionPane(JComponent component) {
       JOptionPane.showMessageDialog(component, WRONG_DATE);
    }
}
