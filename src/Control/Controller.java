package Control;

import Model.MensaMealWithDate;
import Model.Nutrients;
import View.MainView;
import View.NutritientOverview;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Controller listens to user actions and changes model and view according to this
 *
 * @author johannakrickow (ugtfp)
 * @version 22.06.2023
 */
public class Controller {

    private final MainView view;

    public Controller(MainView view) {
        this.view = view;
    }

    public SelectedDayButtonActionListener createSelectedDayButtonActionListener() {
        return new SelectedDayButtonActionListener(view);
    }

    public ShowHistoryButtonActionListener createShowHistoryButtonActionListener() {
        return new ShowHistoryButtonActionListener(view);
    }

    public ShowNutrientsButtonActionListener createShowNutrientsButtonActionListener() {
        return new ShowNutrientsButtonActionListener(view);
    }

    public AddMealMouseAdapter createMealMouseAdapter() {
        return new AddMealMouseAdapter(view);
    }

    public RemoveFromHistoryMouseAdapter createHistoryMouseAdapter() {
        return new RemoveFromHistoryMouseAdapter(view);
    }

    public WindowActionListener createWindowActionListener() {
        return new WindowActionListener(view);
    }

}
