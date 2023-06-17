package Model;

import edu.kit.aifb.atks.mensascraper.lib.KITMensaScraper;
import edu.kit.aifb.atks.mensascraper.lib.MensaLocation;
import edu.kit.aifb.atks.mensascraper.lib.MensaMeal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//start page, instance is created, when program starts
public class Meals {

    private final KITMensaScraper mensa = new KITMensaScraper();
    private List<MensaMealWithDate> currentMeals;


    public Meals(LocalDate selectedDate) {

        this.currentMeals = this.getMeals(selectedDate);

    }

    //method gets meals for any future day from library
    private List<MensaMealWithDate> getMeals(LocalDate selectedDate) {
        List<MensaMeal> mensaMeals = mensa.fetchMeals(MensaLocation.ADENAUERRING, selectedDate);
        List<MensaMealWithDate> mensaMealsWithDates = new ArrayList<>();

        for(MensaMeal mensaMeal : mensaMeals) {
            MensaMealWithDate mensaMealWithDate = new MensaMealWithDate(mensaMeal, selectedDate);
            mensaMealsWithDates.add(mensaMealWithDate);
        }

        return mensaMealsWithDates;
    }

    public void updateCurrentMeals(LocalDate selectedDate) {
        this.currentMeals = getMeals(selectedDate);
    }

    public List<MensaMealWithDate> getCurrentMeals() {
        return Collections.unmodifiableList(currentMeals);
    }


}
