package se.kth.csc.iprog.dinnerplanner.view;

import se.kth.csc.iprog.dinnerplanner.controller.FullMenuController;
import javafx.scene.layout.BorderPane;
import loader.ViewLoader;

import java.io.IOException;

/**
 * Created by Giulio on 08/02/14.
 */
public class FullMenuView extends BorderPane {

    public FullMenuView(FullMenuController controller) {
        if (controller == null)
            throw new IllegalArgumentException("Can't have null controller");
        try {
            ViewLoader.load(this, "PreparationView.fxml", controller);
        } catch (IOException e) {
            throw new RuntimeException("Can't find view " + "PreparationView.fxml");
        }
    }
}
