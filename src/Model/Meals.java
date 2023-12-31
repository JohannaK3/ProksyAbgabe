package Model;

import edu.kit.aifb.atks.mensascraper.lib.KITMensaScraper;
import edu.kit.aifb.atks.mensascraper.lib.MensaLocation;
import edu.kit.aifb.atks.mensascraper.lib.MensaMeal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Uses MensaMeal Class of Library to create meal instances for meals of specific day when program starts
 *
 * @see MensaMeal
 *
 * @author johannakrickow (ugtfp)
 * @version 22.06.2023
 */
//start page, instance is created, when program starts
public class Meals {

    private final KITMensaScraper mensa = new KITMensaScraper();
    private List<MensaMealWithDate> currentMeals;


    public Meals(LocalDate selectedDate) {

        this.currentMeals = this.getMeals(selectedDate);
    }

    /**
     * Retrieves a list of MensaMealWithDate objects for the selected date from library.
     * @param selectedDate LocalDate object representing the selected date.
     * @return List of MensaMealWithDate objects for the selected date.
     */
    private List<MensaMealWithDate> getMeals(LocalDate selectedDate) {
        List<MensaMeal> mensaMeals = mensa.fetchMeals(MensaLocation.ADENAUERRING, selectedDate);
        List<MensaMealWithDate> mensaMealsWithDate = new ArrayList<>();

        for(MensaMeal mensaMeal : mensaMeals) {
            MensaMealWithDate mensaMealWithDate = new MensaMealWithDate(mensaMeal, selectedDate);
            mensaMealsWithDate.add(mensaMealWithDate);
        }

        return mensaMealsWithDate;
    }

    /**
     * Updates the currentMeals list with the meals for the selected date.
     * @param selectedDate LocalDate object representing the selected date.
     */
    public void updateCurrentMeals(LocalDate selectedDate) {
        this.currentMeals = getMeals(selectedDate);
    }

    public List<MensaMealWithDate> getCurrentMeals() {
        return Collections.unmodifiableList(currentMeals);
    }



}
