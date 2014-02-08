package DinnerPlanner;

import javafx.scene.layout.BorderPane;
import loader.ViewLoader;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;

import java.io.IOException;

/**
 * Created by halitanil on 07.02.2014.
 */
public class MainView extends BorderPane {

    public MainView(MainController controller) {
        try {
            ViewLoader.load(this, "StartView.fxml", controller);
        } catch (IOException e) {
            throw new RuntimeException("Can't find view " + "StartView.fxml");
        }


    }

}
