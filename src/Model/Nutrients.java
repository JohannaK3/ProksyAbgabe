package Model;

public class Nutrients {

    private Object[][] accumulatedNutrientsArray;
    private double totalKCal;
    private double totalProteins;
    private double totalCarbs;
    private double totalFat;
    private double totalVeggieAmount;
    private double totalCosts;

    private final String totalKCalString, totalProteinsString, totalCarbsString, totalFatString, totalVeggieAmountString,
                            totalCostsString;

    private final String[] attributesStringArr;
    private String[] totalAmountArr;


    public Nutrients() {

        totalKCalString = "KCal";
        totalProteinsString = "Proteine";
        totalCarbsString = "Kohlenhydrate";
        totalFatString = "Fett";
        totalVeggieAmountString = "Veggie-Anteil";
        totalCostsString = "Kosten";

        attributesStringArr = new String[]{totalKCalString, totalProteinsString, totalCarbsString, totalFatString,
                totalVeggieAmountString, totalCostsString};

        totalAmountArr = new String[] {String.valueOf(totalKCal), String.valueOf(totalProteins),
                String.valueOf(totalCarbs), String.valueOf(totalFat), String.valueOf(totalVeggieAmount),
                String.valueOf(totalCosts)};
        updateAccumulatedNutrientsArray(totalAmountArr);


    }


    public String[] updateNutrients(MensaMealWithDate meal) {
        totalKCal += meal.getMeal().getKcal();
        totalProteins += meal.getMeal().getProteins();
        totalCarbs += meal.getMeal().getCarbs();
        totalFat += meal.getMeal().getFat();
        //TODO: method to check what meal type and if veggie, add to totalVeggieAmount
        //totalVeggieAmount += meal.getMeal().getType();
        totalCosts += meal.getMeal().getPrice();

        return totalAmountArr.clone();
    }

    private void updateAccumulatedNutrientsArray(String[] totalAmountArr) {
        this.totalAmountArr = totalAmountArr;

        accumulatedNutrientsArray = new String[attributesStringArr.length][2];
        //TODO: lenght-1?
        for (int i = 0; i < attributesStringArr.length - 1 ; i++) {
            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    accumulatedNutrientsArray[i][j] = attributesStringArr[i];
                } else {
                    accumulatedNutrientsArray[i][j] = totalAmountArr[i];
                }

            }

        }
    }

    public Object[][] getAccumulatedNutrientsArray() {
        return accumulatedNutrientsArray.clone();
    }
}
