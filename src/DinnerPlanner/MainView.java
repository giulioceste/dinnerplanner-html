package DinnerPlanner;

import javafx.scene.layout.BorderPane;
import loader.ViewLoader;

import java.io.IOException;

/**
 * Created by halitanil on 07.02.2014.
 */
public class MainView extends BorderPane {

    public MainView(MainController controller) {
        try {
            ViewLoader.load(this, "MainView.fxml", controller);
        } catch (IOException e) {
            throw new RuntimeException("Can't find view " + "FullMenuView.fxml");
        }


    }

}
