package Main;
import Control.Controller;
import View.MainView;


public class Main {

    public static void main(String[] args) {

        MainView view = new MainView();
        Controller controller = new Controller();


        view.setController(controller);
        controller.setView(view);

        view.setVisible(true);

    }

}