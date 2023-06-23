package Model;

import edu.kit.aifb.atks.mensascraper.lib.MensaMealType;

/**
 * Contains nutritional information about meals.
 * Contains methods to accumulate and update nutrients of all meals in history.
 *
 * @author johannakrickow (ugtfp)
 * @version 23.06.2023
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

    /**
     * Overwrites the array with each accumulated attribute.
     * Rounds all numbers.
     */
    private void populateTotalAmountArr() {
        totalAmountArr = new double[] {totalKCal, totalProteins, totalCarbs, totalFat, relativeVeggieAmount, totalCosts};

        for (int i = 0; i < totalAmountArr.length; i++) {
            totalAmountArr[i] = Math.round(totalAmountArr[i] * 100) / 100;
        }
    }

    private void updateRelativeVeggieAmount() {
        relativeVeggieAmount = totalMealCounter == 0 ? 0 : (totalVeggieAmount / totalMealCounter) * 100;
    }

    private void updateAccumulatedNutrientsArray() {
        for (int rowIndex = 0; rowIndex < attributesStringArr.length; rowIndex++) {
            int leftCol = 0;
            int rightCol = 1;
            accumulatedNutrientsArray[rowIndex][leftCol] = attributesStringArr[rowIndex];
            accumulatedNutrientsArray[rowIndex][rightCol] = String.valueOf(totalAmountArr[rowIndex]);

        }
    }

    /**
     * Adds nutrients of a meal to the accumulated nutrients.
     * @param meal that is being added.
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
        updateRelativeVeggieAmount();

        totalCosts += meal.getMeal().getPrice();
        populateTotalAmountArr();
        updateAccumulatedNutrientsArray();
    }

    /**
     * Updates accumulated nutrients array with Strings from meals in cache.
     * Strings are being converted to doubles to calculate with them.
     * @param cachedMeals array with all meals from cache.
     */
    public void updateNutrientsFromCache(Object[][] cachedMeals) {
        for (Object[] meal : cachedMeals) {
            totalMealCounter += 1;
            totalCosts += Double.parseDouble((String) meal[2]);
            totalKCal += Double.parseDouble((String) meal[4]);
            totalProteins += Double.parseDouble((String) meal[5]);
            totalCarbs += Double.parseDouble((String) meal[6]);
            totalFat += Double.parseDouble((String) meal[7]);
            if (meal[8].equals("vegetarisch")) {
                totalVeggieAmount += 1;
            }
            updateRelativeVeggieAmount();

            populateTotalAmountArr();
            updateAccumulatedNutrientsArray();
        }
    }

    /**
     * Reduces accumulated amount of each attribute by the meal's amount, that is being removed.
     * @param kCal
     * @param proteins
     * @param carbs
     * @param fat
     * @param mealType
     * @param price
     */
    public void removeNutrients(double kCal, double proteins, double carbs, double fat, String mealType, double price) {
        totalMealCounter -= 1;
        totalKCal -= kCal;
        totalProteins -= proteins;
        totalCarbs -= carbs;
        totalFat -= fat;
        if (mealType.equals("vegetarisch") || mealType.equals("vegan")) {
            totalVeggieAmount -= 1;
        }
        updateRelativeVeggieAmount();

        totalCosts -= price;
        populateTotalAmountArr();
        updateAccumulatedNutrientsArray();
    }

    public Object[][] getAccumulatedNutrientsArray() {
        return accumulatedNutrientsArray.clone();
    }

}
