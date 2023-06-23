package Control;

import javax.swing.*;

public class DateOptionPane {

    private final String wrongDate = "Für das gewählte Datum liegen keine Daten vor. Bitte" +
            "ein anderes Datum wählen.";
    public DateOptionPane(JComponent component) {
       JOptionPane.showMessageDialog(component, wrongDate);
    }
}
