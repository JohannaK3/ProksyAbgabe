package Model;

import edu.kit.aifb.atks.mensascraper.lib.KITMensaScraper;
import edu.kit.aifb.atks.mensascraper.lib.MensaLocation;
import edu.kit.aifb.atks.mensascraper.lib.MensaMeal;

import java.time.LocalDate;
import java.util.List;

// Startseite, Instanz soll immer erstellt werden, wenn Programm gestartet wird
public class Meals {

    private final KITMensaScraper mensa = new KITMensaScraper();
    private static final LocalDate DEFAULT_DATE = LocalDate.now();
    private List<MensaMeal> currentMeals;

    public Meals() {
        this.currentMeals = this.getMeals(DEFAULT_DATE);
    }

    //Methode holt Meals für beliebigen Tag aus Library
    private List<MensaMeal> getMeals(LocalDate date) {
        return mensa.fetchMeals(MensaLocation.ADENAUERRING, date);
    }

    //Speichert Meal Liste eines anderen Tages in Meal Liste, die gerade angezeigt wird -> updatet
    public void setMealsForDate(LocalDate date) {
        this.currentMeals = this.getMeals(date);
    }
}
