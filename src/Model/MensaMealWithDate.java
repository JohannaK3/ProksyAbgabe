package Model;

import edu.kit.aifb.atks.mensascraper.lib.MensaMeal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MensaMealWithDate {

    private LocalDate date;
    private MensaMeal meal;

    MensaMealWithDate(MensaMeal meal, LocalDate date) {
        this.date = date;
        this.meal = meal;
    }

    public LocalDate getDate() {
        return date;
    }

    private String getDateOfMealInString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return getDate().format(formatter);
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
