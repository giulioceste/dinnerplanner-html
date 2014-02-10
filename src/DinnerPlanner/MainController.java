package DinnerPlanner;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
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
    private VBox dropDishBox, menuPanel;

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
        assert menuPanel != null : "fx:id=\"menuPanel\" was not injected: check your FXML file 'MainView.fxml'.";

        setUpUI();
    }

    /**
     * Encapsulates the User Interface setup processes.
     */
    private void setUpUI() {
        // Bind the layout property with the visibility property.
        selectedDessertView.managedProperty().bind(selectedDessertView.visibleProperty());
        selectedMainView.managedProperty().bind(selectedMainView.visibleProperty());
        selectedStarterVıew.managedProperty().bind(selectedStarterVıew.visibleProperty());

        // Add a search and portfolio view.
        starterTab.setContent(new SearchView(starterDishController));
        mainTab.setContent(new SearchView(mainDishController));
        dessertTab.setContent(new SearchView(dessertController));

        // Create a Drag event handler and add it to the correct pane for dropping menu items.
        DragEventHandler handler = new DragEventHandler();
        dropDishBox.setOnDragOver(handler);
        dropDishBox.setOnDragEntered(handler);
        dropDishBox.setOnDragExited(handler);
        dropDishBox.setOnDragDropped(handler);

        // Allow the vboc children to expand in width with the VBox.
        menuPanel.setFillWidth(true);
        dropDishBox.setFillWidth(true);
    }



    /**
     * Adds a dish to the Full Menu.
     * <b>Dish cannot be null.
     *
     * @param dish Dish to add to the menu.
     */
    private void addDishToSelected(Dish dish) {
        assert dish != null: "Dish being selected cannot be null";

        // Update the model.
        model.addToMenu(dish);

        // Update the view
        HBox view;
        switch (dish.getType()){
            case 1:
                view = selectedStarterVıew;
                break;
            case 2:
                view = selectedMainView;
                break;
            default:
                view = selectedDessertView;
        }
        view.getChildren().clear();
        view.getChildren().add(new AddedDishItemView(dish));
    }

    private Dish getDishByName(String name) {
        for (Dish dish: model.getDishes())
            if(dish.getName().equals(name))
                return dish;
        return null;
    }

    private boolean hasDishByName(String name) {
        return getDishByName(name) != null;
    }



    /**
     * Drag and drop Event Handler for the destination.
     */
    private class DragEventHandler implements EventHandler<DragEvent> {

        @Override
        public void handle(DragEvent dragEvent) {
            EventType<? extends Event> type = dragEvent.getEventType();

            // Whenever the implementing container is being dragged over.
            if (type.equals(DragEvent.DRAG_OVER)) {
                /*
                Only accept drops from nodes that are not thıs dısh box
                and provıde a string with the same name as an existing dish
                 */
                if (dragEvent.getGestureSource() != dropDishBox &&
                        dragEvent.getDragboard().hasString() && hasDishByName(dragEvent.getDragboard().getString())) {
                    dragEvent.acceptTransferModes(TransferMode.ANY);
                }
            } else if (type.equals(DragEvent.DRAG_ENTERED)) {
                /**
                 * Only allow drag events with names of dishes that we have.
                 */
                if (dragEvent.getGestureSource() != dropDishBox &&
                        dragEvent.getDragboard().hasString() && hasDishByName(
                        dragEvent.getDragboard().getString())) {
                    dragDishLabel.setTextFill(Color.YELLOW);
                }
            } else if (type.equals(DragEvent.DRAG_EXITED)) {
                /* mouse moved away, remove the graphical cues */
                dragDishLabel.setTextFill(Color.BLACK);
            } else if (type.equals(DragEvent.DRAG_DROPPED)) {

                /*
                Check if we have a Dish in the dragboard
                 */
                Dragboard db = dragEvent.getDragboard();
                boolean success = false;
                if (db.hasString() && hasDishByName(dragEvent.getDragboard().getString())) {
                    // TODO add dish to drop Dish Box
                    Dish dish = getDishByName(db.getString());
                    addDishToSelected(dish);
                    totalCostLabel.setText("" + dish.getPrice());
                    success = true;
                }
                dragEvent.setDropCompleted(success);
            } else {
                // Ignore any dragevent that we don't need to handle.
                return;
            }
            dragEvent.consume();
        }
    }
}
