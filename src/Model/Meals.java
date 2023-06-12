package Model;

import Control.Controller;
import edu.kit.aifb.atks.mensascraper.lib.KITMensaScraper;
import edu.kit.aifb.atks.mensascraper.lib.MensaLocation;
import edu.kit.aifb.atks.mensascraper.lib.MensaMeal;
import java.time.LocalDate;
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

}
