package DinnerPlanner;

import javafx.scene.layout.BorderPane;
import loader.ViewLoader;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;

import java.io.IOException;

/**
 * Created by Giulio on 08/02/14.
 */
public class FullMenuView extends BorderPane {

    public FullMenuView(DinnerModel model) {
        try {
            ViewLoader.load(this, "FullMenuView.fxml", this);
        } catch (IOException e) {
            throw new RuntimeException("Can't find view " + "FullMenuView.fxml");
        }
    }
}
