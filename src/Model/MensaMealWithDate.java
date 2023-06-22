package Model;

import edu.kit.aifb.atks.mensascraper.lib.MensaMeal;
import edu.kit.aifb.atks.mensascraper.lib.MensaMealType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MensaMealWithDate implements Comparable<MensaMealWithDate> {

    private LocalDate date;
    private MensaMeal meal;
    private boolean isVeggie;

    MensaMealWithDate(MensaMeal meal, LocalDate date) {
        this.date = date;
        this.meal = meal;

        checkMealType();
    }

    private LocalDate getDate() {
        return date;
    }

    private String getDateOfMealInString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return getDate().format(formatter);
    }

    //TODO: sort meals in history by date (newest at the top)
    @Override
    public int compareTo(MensaMealWithDate meal) {
        return getDate().compareTo(meal.getDate());
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
                String.valueOf(meal.getCarbs()), String.valueOf(meal.getFat()), getTypeString()};
    }

    private void checkMealType() {
        if (meal.getType() == MensaMealType.VEGETARIAN || meal.getType() == MensaMealType.VEGAN) {
            isVeggie = true;
            return;
        }
        isVeggie = false;
    }

    private String getTypeString() {
        if (isVeggie) {
            return "vegetarisch";
        }
        return "nicht vegetarisch";
    }

}
