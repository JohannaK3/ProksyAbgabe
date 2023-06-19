package Model;

import edu.kit.aifb.atks.mensascraper.lib.MensaMeal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//creates new instance of a meal with date attribute
public class MensaMealWithDate implements Comparable<MensaMealWithDate> {

    private LocalDate date;
    private MensaMeal meal;

    private LocalDate getDate() {
        return date;
    }

    //TODO: Method needs to go somewhere in view, I dont know where
    public String getDateOfMealInString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return getDate().format(formatter);
    }

    //TODO: sort meals in history by date (newest at the top)
    @Override
    public int compareTo(MensaMealWithDate meal) {
        return getDate().compareTo(meal.getDate());
    }

    MensaMealWithDate(MensaMeal meal, LocalDate date) {
        this.date = date;
        this.meal = meal;
    }

    public MensaMeal getMeal() {
        return meal;
    }

    public String[] getMealInfo() {
        return new String[]{meal.getName(), String.valueOf(meal.getPrice()), String.valueOf(meal.getLine())};
    }

    public String[] getExtendedMealInfo() {
        return new String[] {meal.getName(), getDateOfMealInString(), String.valueOf(meal.getPrice()),
                String.valueOf(meal.getLine()), String.valueOf(meal.getKcal()), String.valueOf(meal.getProteins()),
                String.valueOf(meal.getCarbs()), String.valueOf(meal.getFat())};
    }

}
