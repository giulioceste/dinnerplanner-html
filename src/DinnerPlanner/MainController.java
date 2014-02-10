package DinnerPlanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

import java.net.URL;
import java.util.ResourceBundle;


public class MainController {

    private final DinnerModel model;

    private final SearchController starterDishController, mainDishController, dessertController;

    public MainController(DinnerModel model) {
        this.model = model;
        starterDishController = new SearchController(model.getDishesOfType(1));
        mainDishController = new SearchController(model.getDishesOfType(2));
        dessertController = new SearchController(model.getDishesOfType(3));
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button decrPeopleButton;

    @FXML
    private Tab dessertTab;

    @FXML
    private Pane dishPane;

    @FXML
    private Label dragDishLabel;

    @FXML
    private Button incPeopleButton;

    @FXML
    private Button ingButton;

    @FXML
    private Tab mainTab;

    @FXML
    private TextField numPeopleInputField;

    @FXML
    private Button prepButton;

    @FXML
    private Tab starterTab;

    @FXML
    private Label totalCostLabel, dishNameLabel;

    @FXML
    private ImageView dishImage;

    @FXML
    private Pane dishListPane;

    @FXML
    private GridPane gridPane;

    @FXML
    private VBox dropDishBox;

    @FXML
    private FlowPane mainFlowPane;

    @FXML
    private HBox selectedDessertView, selectedMainView, selectedStarterVıew;

    @FXML
    void onDecrementPeopleClicked(ActionEvent event) {
    }

    @FXML
    void onIncrementPeopleClicked(ActionEvent event) {
    }

    @FXML
    void onIngredientsClicked(ActionEvent event) {
    }

    @FXML
    void onNumPeopleChanged(ActionEvent event) {
    }

    @FXML
    void onPreparationClicked(ActionEvent event) {
    }

    @FXML
    void initialize() {
        assert decrPeopleButton != null : "fx:id=\"decrPeopleButton\" was not injected: check your FXML file 'MainView.fxml'.";
        assert dessertTab != null : "fx:id=\"dessertTab\" was not injected: check your FXML file 'MainView.fxml'.";
        assert dishListPane != null : "fx:id=\"dishListPane\" was not injected: check your FXML file 'MainView.fxml'.";
        assert dragDishLabel != null : "fx:id=\"dragDishLabel\" was not injected: check your FXML file 'MainView.fxml'.";
        assert dropDishBox != null : "fx:id=\"dropDishBox\" was not injected: check your FXML file 'MainView.fxml'.";
        assert gridPane != null : "fx:id=\"gridPane\" was not injected: check your FXML file 'MainView.fxml'.";
        assert incPeopleButton != null : "fx:id=\"incPeopleButton\" was not injected: check your FXML file 'MainView.fxml'.";
        assert ingButton != null : "fx:id=\"ingButton\" was not injected: check your FXML file 'MainView.fxml'.";
        assert mainFlowPane != null : "fx:id=\"mainFlowPane\" was not injected: check your FXML file 'MainView.fxml'.";
        assert mainTab != null : "fx:id=\"mainTab\" was not injected: check your FXML file 'MainView.fxml'.";
        assert numPeopleInputField != null : "fx:id=\"numPeopleInputField\" was not injected: check your FXML file 'MainView.fxml'.";
        assert prepButton != null : "fx:id=\"prepButton\" was not injected: check your FXML file 'MainView.fxml'.";
        assert selectedDessertView != null : "fx:id=\"selectedDessertView\" was not injected: check your FXML file 'MainView.fxml'.";
        assert selectedMainView != null : "fx:id=\"selectedMainView\" was not injected: check your FXML file 'MainView.fxml'.";
        assert selectedStarterVıew != null : "fx:id=\"selectedStarterVıew\" was not injected: check your FXML file 'MainView.fxml'.";
        assert starterTab != null : "fx:id=\"starterTab\" was not injected: check your FXML file 'MainView.fxml'.";
        assert totalCostLabel != null : "fx:id=\"totalCostLabel\" was not injected: check your FXML file 'MainView.fxml'.";

        selectedDessertView.managedProperty().bind(selectedDessertView.visibleProperty());
        selectedMainView.managedProperty().bind(selectedMainView.visibleProperty());
        selectedStarterVıew.managedProperty().bind(selectedStarterVıew.visibleProperty());

        starterTab.setContent(new SearchView(starterDishController));
        mainTab.setContent(new SearchView(mainDishController));
        dessertTab.setContent(new SearchView(dessertController));

        // Register that drop dish box can accept drag and drop items
        dropDishBox.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                /*
                Only accept drops from nodes that are not thıs dısh box
                and provıde a string with the same name as an existing dish
                 */
                if (dragEvent.getGestureSource() != dropDishBox &&
                        dragEvent.getDragboard().hasString() && hasDishByName(dragEvent.getDragboard().getString())) {
                     dragEvent.acceptTransferModes(TransferMode.ANY);
                }
                dragEvent.consume();
            }
        });

        dropDishBox.setOnDragEntered(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* show to the user that it is an actual gesture target */
                if (event.getGestureSource() != dropDishBox &&
                        event.getDragboard().hasString() && hasDishByName(event.getDragboard().getString())) {
                    dragDishLabel.setTextFill(Color.YELLOW);
                }
                event.consume();
            }
        });

        dropDishBox.setOnDragExited(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* mouse moved away, remove the graphical cues */
                dragDishLabel.setTextFill(Color.BLACK);
                event.consume();
            }
        });

        dropDishBox.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                /*
                Check if we have a Dish in the dragboard
                 */
                Dragboard db = dragEvent.getDragboard();
                boolean success = false;
                if (db.hasString() && hasDishByName(dragEvent.getDragboard().getString())) {
                    // TODO add dish to drop Dish Box
                    Dish dish = getDishByName(db.getString());
                    addDishToSelected(dish);
                    success = true;
                }

                dragEvent.setDropCompleted(success);
                dragEvent.consume();
            }
        });
    }

    private void addDishToSelected(Dish dish) {
        switch (dish.getType()){
            case 1:
                selectedStarterVıew.getChildren().clear();
                selectedStarterVıew.getChildren().add(new AddedDishItemView(dish));
                return;
            case 2:
                selectedMainView.getChildren().clear();
                selectedMainView.getChildren().add(new AddedDishItemView(dish));
                return;
            default:
                selectedDessertView.getChildren().clear();
                selectedDessertView.getChildren().add(new AddedDishItemView(dish));
        }

    }

    private Dish getDishByName(String name)
    {
        for (Dish dish: model.getDishes())
        {
            if(dish.getName().equals(name))
                return dish;
        }
        return null;
    }

    private boolean hasDishByName(String name)
    {
        return getDishByName(name) != null;
    }

}
