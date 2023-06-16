package Model;

import Control.Controller;
import View.StartPageFrame;
import edu.kit.aifb.atks.mensascraper.lib.KITMensaScraper;
import edu.kit.aifb.atks.mensascraper.lib.MensaLocation;
import edu.kit.aifb.atks.mensascraper.lib.MensaMeal;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;

    // start page, instance is created, when program starts
public class Meals {




    private final KITMensaScraper mensa = new KITMensaScraper();
    private List<MensaMeal> currentMeals;


    public Meals(LocalDate selectedDate) {

        this.currentMeals = this.getMeals(selectedDate);


    }

    //method gets meals for any future day from library
    private List<MensaMeal> getMeals(LocalDate selectedDate) {
        return mensa.fetchMeals(MensaLocation.ADENAUERRING, selectedDate);
    }




    //TODO: müssen die methoden hier rein??
    public LocalDate convertToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public LocalDate getSpinnerDate(JSpinner spinner) {
        Date selectedDate = (Date) spinner.getValue();
        return convertToLocalDate(selectedDate);
    }

    /*
    //TODO: hilfe
    getDateSpinner().addChangeListener(new ChangeListener() {
        @Override
            public void stateChanged(ChangeEvent event) {
                JSpinner spinner = (JSpinner) event.getSource();
                getSpinnerDate(spinner);
            }
        }
        );


    */
}
