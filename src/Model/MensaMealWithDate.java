package Model;

import edu.kit.aifb.atks.mensascraper.lib.MensaMeal;

import java.time.LocalDate;

    //creates new instance of a meal with date attribute
public class MensaMealWithDate implements Comparable<MensaMealWithDate> {
    //TODO: Datum für Instanzen

    private LocalDate date;
    private MensaMeal meal;

    public LocalDate getDate() {
        return date;
    }

    @Override
    public int compareTo(MensaMealWithDate meal) {
        return getDate().compareTo(meal.getDate());
    }

    MensaMealWithDate(MensaMeal meal, LocalDate date) {
        this.date = date;
        this.meal = meal;
    }




}
