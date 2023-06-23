package Control;

import javax.swing.*;
import java.io.IOException;

public class WritingFailedOptionPane {

    private final String writingFailed = "Das Schreiben der Datei ist fehlgeschlagen. Bitte Programm neu starten."
    public WritingFailedOptionPane(JComponent component, IOException exception) {

        JOptionPane.showMessageDialog(component, writingFailed + exception.toString());
    }
}
