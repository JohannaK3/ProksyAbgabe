package Main;
import Control.Controller;
import Model.MealHistoryModel;
import Model.Meals;
import Model.MensaMealWithDate;
import View.MealHistoryView;
import View.MealOverview;
import View.NutritientOverview;
import View.StartPageFrame;


public class Main {
    public static void main(String[] args) {
        //final KITMensaScraper mensa = new KITMensaScraper();
        //final List<MensaMeal> meals = mensa.fetchMeals(MensaLocation.ADENAUERRING, LocalDate.of(2023, 06, 12));
        //System.out.println(meals);
        //meals.forEach(System.out::println);

        //TODO: wtf
        StartPageFrame  startPageFrame = new StartPageFrame();
        MealHistoryView mealHistory = new MealHistoryView();
        MealOverview mealOverview = new MealOverview();
        NutritientOverview nutritientOverview = new NutritientOverview();

        Controller controller = new Controller();

        MealHistoryModel mealHistoryModel = new MealHistoryModel();
        //Meals meals = new Meals();
        //MensaMealWithDate mensaMealWithDate = new MensaMealWithDate();


        //startPageFrame.setController(controller);
    }

}