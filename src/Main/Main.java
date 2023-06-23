package Main;

import Control.Controller;
import View.MainView;

/**
 * Creates instances and starts program.
 *
 * @author johannakrickow (ugtfp)
 * @version 23.06.2023
 */
public class Main {

    public static void main(String[] args) {

        MainView view = new MainView();
        Controller controller = new Controller(view);


        view.setController(controller);
    }

}