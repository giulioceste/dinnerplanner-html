package DinnerPlanner;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * Created by Giulio on 08/02/14.
 */
public class FullMenuController implements DinnerModel.OnModelChangedListener {

    private final DinnerModel model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea dessertArea;

    @FXML
    private Label dessertLabel;

    @FXML
    private TextArea mainArea;

    @FXML
    private Label mainLabel;

    @FXML
    private TextArea starterArea;

    @FXML
    private Label starterLabel;

    public FullMenuController(DinnerModel model) {
        this.model = model;
        model.addListener(this);
    }

    @FXML
    void initialize() {
        assert dessertArea != null : "fx:id=\"dessertArea\" was not injected: check your FXML file 'PreparationView.fxml'.";
        assert dessertLabel != null : "fx:id=\"dessertLabel\" was not injected: check your FXML file 'PreparationView.fxml'.";
        assert mainArea != null : "fx:id=\"mainArea\" was not injected: check your FXML file 'PreparationView.fxml'.";
        assert mainLabel != null : "fx:id=\"mainLabel\" was not injected: check your FXML file 'PreparationView.fxml'.";
        assert starterArea != null : "fx:id=\"starterArea\" was not injected: check your FXML file 'PreparationView.fxml'.";
        assert starterLabel != null : "fx:id=\"starterLabel\" was not injected: check your FXML file 'PreparationView.fxml'.";
        setupUI();
    }

    private void setupUI() {
        // Make sure when elements dissapears the layout resizes.
        dessertLabel.managedProperty().bind(dessertLabel.visibleProperty());
        dessertArea.managedProperty().bind(dessertArea.visibleProperty());
        mainLabel.managedProperty().bind(mainLabel.visibleProperty());
        mainArea.managedProperty().bind(mainArea.visibleProperty());
        starterLabel.managedProperty().bind(starterLabel.visibleProperty());
        starterArea.managedProperty().bind(starterArea.visibleProperty());

        // Bind the visibility of the label to the text.
        // So when the text area disappears and reappears so does the label.
        dessertLabel.visibleProperty().bind(dessertArea.visibleProperty());
        mainLabel.visibleProperty().bind(mainArea.visibleProperty());
        starterLabel.visibleProperty().bind(starterArea.visibleProperty());

        updateView();
    }

    /**
     * Updates the UI based on the model.
     */
    private void updateView() {

        starterArea.setVisible(false);
        mainArea.setVisible(false);
        dessertArea.setVisible(false);

        // iterate through all three dishes and populate the view.
        for (Dish dish: model.getFullMenu()) {
            switch (dish.getType()) {
                case Dish.STARTER:
                    starterLabel.setText("Starter: " + dish.getName());
                    starterArea.setText(dish.getDescription());
                    starterArea.setVisible(true);
                    break;
                case Dish.MAIN:
                    mainLabel.setText("Main: " + dish.getName());
                    mainArea.setText(dish.getDescription());
                    mainArea.setVisible(true);
                    break;
                case Dish.DESERT:
                    dessertLabel.setText("Dessert: " + dish.getName());
                    dessertArea.setText(dish.getDescription());
                    dessertArea.setVisible(true);
                    break;
                default:
                    Logger.getGlobal().warning("Illegal Dish type " + dish.getType());
            }
        }
    }

    @Override
    public void onDishAdded(Dish added) {
        updateView();
    }

    @Override
    public void onDishRemoved(Dish removed) {
        updateView();
    }

    @Override
    public void onNumberOfGuestChanged(int newAmount, int oldAmount) {
        // Nothing to do for this view.
    }
}
