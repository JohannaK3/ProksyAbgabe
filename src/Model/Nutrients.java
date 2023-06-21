package Model;

import edu.kit.aifb.atks.mensascraper.lib.MensaMealType;

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
        //updateAccumulatedNutrientsArray(totalAmountArr);
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

        totalAmountArr = new double[] {totalKCal, totalProteins, totalCarbs, totalFat, relativeVeggieAmount,
                totalCosts};

        accumulatedNutrientsArray = new String[attributesStringArr.length][2];
    }

    private String[] convertToString(double[] doubleArr) {
        String[] stringArr = new String[doubleArr.length];
        for (int i = 0; i < doubleArr.length; i++) {
            stringArr[i] = String.valueOf(doubleArr[i]);
        }
        return stringArr;
    }

    private void veggieAmountCounter() {
        totalVeggieAmount += 1;
    }

    private boolean checkMealType(MensaMealWithDate meal){
        if (meal.getMeal().getType() == MensaMealType.VEGETARIAN || meal.getMeal().getType() == MensaMealType.VEGAN) {
            veggieAmountCounter();
            return true;
        } else {
            return false;
        }
    }

    public void updateNutrients(MensaMealWithDate meal) {
        System.out.println(totalMealCounter);
        totalMealCounter += 1;
        totalKCal += meal.getMeal().getKcal();
        totalProteins += meal.getMeal().getProteins();
        totalCarbs += meal.getMeal().getCarbs();
        totalFat += meal.getMeal().getFat();
        if (checkMealType(meal) == true) {
            relativeVeggieAmount = (totalVeggieAmount / totalMealCounter) * 100;
        } else {
            relativeVeggieAmount = (totalVeggieAmount / totalMealCounter) * 100;
        }
        totalCosts += meal.getMeal().getPrice();
        System.out.println(totalMealCounter);
        updateAccumulatedNutrientsArray(totalAmountArr);
        System.out.println(totalMealCounter);
    }

    //funktioniert nicht!
    public void updateAccumulatedNutrientsArray(double[] totalAmountArr) {
        //this.totalAmountArr = totalAmountArr.clone();
        //System.out.println(totalAmountArr[0]);
        for (int i = 0; i < attributesStringArr.length; i++) {
            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    accumulatedNutrientsArray[i][j] = attributesStringArr[i];
                } else {
                    accumulatedNutrientsArray[i][j] = convertToString(totalAmountArr)[i];
                }

            }

        }
        //System.arraycopy(totalAmountArr, 0, this.totalAmountArr, 0, totalAmountArr.length);
        //System.out.println(totalAmountArr[0]);
    }

    public Object[][] getAccumulatedNutrientsArray() {
        return accumulatedNutrientsArray.clone();
    }

}
