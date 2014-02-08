package DinnerPlanner;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Giulio on 08/02/14.
 */
public class FullMenuController {

    private final DinnerModel model;

    public FullMenuController(DinnerModel model) {
        this.model = model;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea dessertText;

    @FXML
    private TextArea mainText;

    @FXML
    private TextArea starterText;


    @FXML
    void initialize() {
        assert dessertText != null : "fx:id=\"dessertText\" was not injected: check your FXML file 'FullMenuView.fxml'.";
        assert mainText != null : "fx:id=\"mainText\" was not injected: check your FXML file 'FullMenuView.fxml'.";
        assert starterText != null : "fx:id=\"starterText\" was not injected: check your FXML file 'FullMenuView.fxml'.";

    }

}
