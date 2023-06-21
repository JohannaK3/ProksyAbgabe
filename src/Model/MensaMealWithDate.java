package Model;

import edu.kit.aifb.atks.mensascraper.lib.MensaMeal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Extension of MensaMeal Class to add a specific date attribute to a meal.
 * Contains methods to get meal information in array form.
 *
 * @author johannakrickow (ugtfp)
 * @version 22.06.2023
 */
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

    /**
     * Retrieves the date of the meal in string format.
     * @return date of the meal in the format "dd.MM.yyyy".
     */
    private String getDateOfMealInString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return getDate().format(formatter);
    }

    public MensaMeal getMeal() {
        return meal;
    }

    /**
     * Returns attibutes of meals needed for meal table in String format.
     * @return array of attributes.
     */
    public String[] getMealInfo() {
        return new String[]{meal.getName(), String.valueOf(meal.getPrice()), String.valueOf(meal.getLine())};
    }

    /**
     * Returns all attributes for a meal needed for the accumulated nutrition in String format.
     * @return array of attributes.
     */
    public String[] getExtendedMealInfo() {
        return new String[] {meal.getName(), getDateOfMealInString(), String.valueOf(meal.getPrice()),
                String.valueOf(meal.getLine()), String.valueOf(meal.getKcal()), String.valueOf(meal.getProteins()),
                String.valueOf(meal.getCarbs()), String.valueOf(meal.getFat())};
    }

}
