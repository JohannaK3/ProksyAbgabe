package Control;

import javax.swing.*;
import java.io.IOException;

public class ReadingFailedOptionPane {

    private final String readingFailed = "Das Auslesen der Datei ist fehlgeschlagen. Bitte Programm neu starten."
    public ReadingFailedOptionPane(JComponent component, IOException exception) {

        JOptionPane.showMessageDialog(component, readingFailed + exception.toString());
    }
}
