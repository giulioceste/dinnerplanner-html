package se.kth.csc.iprog.dinnerplanner.view;

import se.kth.csc.iprog.dinnerplanner.controller.SearchController;
import javafx.scene.layout.BorderPane;
import loader.ViewLoader;

import java.io.IOException;

/**
 * Created by halitanil on 08.02.2014.
 */
public class SearchView extends BorderPane {

    public SearchView(SearchController controller) {
        try {
            ViewLoader.load(this, "SearchView.fxml", controller);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load SearchView due to " + e.getMessage());
        }
    }
}
