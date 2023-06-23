package Control;

import javax.swing.*;
import java.io.IOException;

/**
 * A dialog window displayed when writing to a file fails.
 *
 * @author johannakrickow (ugtfp)
 * @version 23.06.2023
 */
public class WritingFailedOptionPane {

    private static final String WRITING_FAILED = "Das Schreiben der Datei ist fehlgeschlagen. Bitte Programm neu starten.";
    public WritingFailedOptionPane(JComponent component, IOException exception) {

        JOptionPane.showMessageDialog(component, WRITING_FAILED + exception.toString());
    }
}
