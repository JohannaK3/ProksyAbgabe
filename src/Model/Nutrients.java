package Model;

public class Nutrients {

    private double totalKCal;
    private double totalProteins;
    private double totalCarbs;
    private double totalFat;
    //private double totalVeggieAmount;
    private double totalCosts;

    private final String totalKCalString, totalProteinsString, totalCarbsString, totalFatString,
                            totalCostsString;
    //private final String totalVeggieAmountString;

    private Object[][] accumulatedNutrientsArray;
    private final String[] attributesStringArr;
    private double[] totalAmountArr;


    public Nutrients() {

        totalKCalString = "KCal";
        totalProteinsString = "Proteine";
        totalCarbsString = "Kohlenhydrate";
        totalFatString = "Fett";
        //totalVeggieAmountString = "Veggie-Anteil";
        totalCostsString = "Kosten";

        //TODO: add Veggie Amount to Array
        attributesStringArr = new String[]{totalKCalString, totalProteinsString, totalCarbsString, totalFatString,
                totalCostsString};

        //TODO: add Veggie Amount to Array
        totalAmountArr = new double[] {totalKCal, totalProteins, totalCarbs, totalFat, totalCosts};
        accumulatedNutrientsArray = new String[attributesStringArr.length][2];
        updateAccumulatedNutrientsArray(totalAmountArr);
    }


    public double[] updateNutrients(MensaMealWithDate meal) {
        totalKCal += meal.getMeal().getKcal();
        totalProteins += meal.getMeal().getProteins();
        totalCarbs += meal.getMeal().getCarbs();
        totalFat += meal.getMeal().getFat();
        //TODO: method to check what meal type and if veggie, add to totalVeggieAmount
        //totalVeggieAmount += meal.getMeal().getType();
        totalCosts += meal.getMeal().getPrice();

        return totalAmountArr.clone();
    }

    public void updateAccumulatedNutrientsArray(double[] totalAmountArr) {
        this.totalAmountArr = totalAmountArr.clone();
        for (int i = 0; i < attributesStringArr.length; i++) {
            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    accumulatedNutrientsArray[i][j] = attributesStringArr[i];
                } else {
                    accumulatedNutrientsArray[i][j] = convertToString(totalAmountArr)[i];
                }

            }

        }
    }

    public Object[][] getAccumulatedNutrientsArray() {
        return accumulatedNutrientsArray.clone();
    }

    private String[] convertToString(double[] doubleArr) {
        String[] stringArr = new String[doubleArr.length];
        for (int i = 0; i < doubleArr.length; i++) {
            stringArr[i] = String.valueOf(doubleArr[i]);
        }
        return stringArr;
    }
}
