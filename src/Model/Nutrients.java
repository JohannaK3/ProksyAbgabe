package Model;

import edu.kit.aifb.atks.mensascraper.lib.MensaMealType;

/**
 * Contains nutritienal information about meals.
 * Contains methods to accumulate nutrients of all meals in history.
 *
 * @author johannakrickow (ugtfp)
 * @version 22.06.2023
 */
public class Nutrients {

    private int totalMealCounter = 0;
    private double totalKCal = 0;
    private double totalProteins = 0;
    private double totalCarbs = 0;
    private double totalFat = 0;
    private double totalVeggieAmount = 0;
    private double relativeVeggieAmount = 0;
    private double totalCosts = 0;
    private static final String TOTAL_KCAL_STRING = "KCal";
    private String totalKCalString, totalProteinsString, totalCarbsString, totalFatString, totalCostsString,
            relativeVeggieAmountString;

    private Object[][] accumulatedNutrientsArray;
    private String[] attributesStringArr;
    private double[] totalAmountArr;


    public Nutrients() {
        initializeStrings();
        initializeArrays();
        updateAccumulatedNutrientsArray();
    }

    private void initializeStrings() {
        totalKCalString = "KCal";
        totalProteinsString = "Proteine";
        totalCarbsString = "Kohlenhydrate";
        totalFatString = "Fett";
        relativeVeggieAmountString = "Veggie-Anteil";
        totalCostsString = "Kosten";
    }

    private void initializeArrays() {
        attributesStringArr = new String[]{totalKCalString, totalProteinsString, totalCarbsString, totalFatString,
                relativeVeggieAmountString, totalCostsString};
        populateTotalAmountArr();
        accumulatedNutrientsArray = new String[attributesStringArr.length][2];
    }

    private void populateTotalAmountArr() {
        totalAmountArr = new double[] {totalKCal, totalProteins, totalCarbs, totalFat, relativeVeggieAmount,
                totalCosts};
    }

    /**
     * Adds nutrients of a MensaMealWithDate object to the accumulated nutrient totals.
     * Updates relevant counters and arrays accordingly.
     * @param meal MensaMealWithDate object representing the meal.
     */
    public void addNutrients(MensaMealWithDate meal) {
        totalMealCounter += 1;
        totalKCal += meal.getMeal().getKcal();
        totalProteins += meal.getMeal().getProteins();
        totalCarbs += meal.getMeal().getCarbs();
        totalFat += meal.getMeal().getFat();
        if (meal.getMeal().getType() == MensaMealType.VEGETARIAN || meal.getMeal().getType() == MensaMealType.VEGAN) {
            totalVeggieAmount += 1;
        }
        relativeVeggieAmount = (totalVeggieAmount / totalMealCounter) * 100;

        totalCosts += meal.getMeal().getPrice();
        populateTotalAmountArr();
        updateAccumulatedNutrientsArray();
    }

    /**
     * Removes nutrients of a MensaMealWithDate object from the accumulated nutrient totals.
     * Updates relevant counters and arrays accordingly.
     * @param meal MensaMealWithDate object representing the meal.
     */
    public void removeNutrients(MensaMealWithDate meal) {
        totalMealCounter -= 1;
        totalKCal -= meal.getMeal().getKcal();
        totalProteins -= meal.getMeal().getProteins();
        totalCarbs -= meal.getMeal().getCarbs();
        totalFat -= meal.getMeal().getFat();
        if (meal.getMeal().getType() == MensaMealType.VEGETARIAN || meal.getMeal().getType() == MensaMealType.VEGAN) {
            totalVeggieAmount -= 1;
        }
        relativeVeggieAmount = (totalVeggieAmount / totalMealCounter) * 100;

        totalCosts -= meal.getMeal().getPrice();
        populateTotalAmountArr();
        updateAccumulatedNutrientsArray();
    }

    /**
     * Updates accumulated nutrients array with the current totals.
     * The accumulated nutrients array: 2D array. Rows: represents a nutrient attribute,
     * left column: contains the attribute name, right column: contains total amount of attribute.
     * Method updates the right column with current total amounts.
     */
    private void updateAccumulatedNutrientsArray() {
        for (int rowIndex = 0; rowIndex < attributesStringArr.length; rowIndex++) {
            int leftCol = 0;
            int rightCol = 1;
            accumulatedNutrientsArray[rowIndex][leftCol] = attributesStringArr[rowIndex];
            accumulatedNutrientsArray[rowIndex][rightCol] = String.valueOf(totalAmountArr[rowIndex]);

        }
    }

    public Object[][] getAccumulatedNutrientsArray() {
        return accumulatedNutrientsArray.clone();
    }

}
