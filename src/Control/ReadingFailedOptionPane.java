package Control;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * ReadingFailedOptionPane provides a dialog box to display an error message when reading a file fails.
 *
 * @author johannakrickow (ugtfp)
 * @version 23.06.2023
 */
public class ReadingFailedOptionPane {

    private static final String READING_FAILED = "Das Auslesen der Datei ist fehlgeschlagen. Bitte Programm neu starten.";

    /**
     * Constructs a new ReadingFailedOptionPane object and displays an error message dialog box with the provided exception.
     *
     * @param component to which the dialog box is attached.
     * @param exception that occurred during file reading.
     */
    public ReadingFailedOptionPane(Component component, IOException exception) {

        JOptionPane.showMessageDialog(component, READING_FAILED + exception.toString());
    }
}
