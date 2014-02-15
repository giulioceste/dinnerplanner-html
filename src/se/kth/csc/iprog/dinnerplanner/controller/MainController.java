package se.kth.csc.iprog.dinnerplanner.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.MapChangeListener;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.Scene;
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
import javafx.stage.Stage;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import se.kth.csc.iprog.dinnerplanner.view.AddedDishItemView;
import se.kth.csc.iprog.dinnerplanner.view.FullMenuView;
import se.kth.csc.iprog.dinnerplanner.view.IngredientsView;
import se.kth.csc.iprog.dinnerplanner.view.SearchView;

import java.net.URL;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.ResourceBundle;


public class MainController  {

    private final DinnerModel model;

    private final SearchController starterDishController, mainDishController, dessertController;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Tab dessertTab, mainTab, starterTab;

    @FXML
    private Pane dishPane, dishListPane;

    @FXML
    private Label dragDishLabel;

    @FXML
    private Button incPeopleButton, ingButton, prepButton, decrPeopleButton;

    @FXML
    private TextField numPeopleInputField;

    @FXML
    private Label totalCostLabel, dishNameLabel;

    @FXML
    private ImageView dishImage;

    @FXML
    private GridPane gridPane;

    @FXML
    private VBox dropDishBox, menuPanel;

    @FXML
    private FlowPane mainFlowPane;

    @FXML
    private BorderPane selectedDessertView, selectedMainView, selectedStarterVıew;

    @FXML
    private Pane dragAndDropNewPane;

    public MainController(DinnerModel model) {
        this.model = model;

        // Initialize the controllers for the three
        // different type of dishes and their respective search views.
        starterDishController = new SearchController(model.getDishesOfType(1));
        mainDishController = new SearchController(model.getDishesOfType(2));
        dessertController = new SearchController(model.getDishesOfType(3));
    }

    @FXML
    void onDecrementPeopleClicked(ActionEvent event) {
        // TODO
        int numPeople = Integer.valueOf(numPeopleInputField.getText().trim());
        if (numPeople == 1) return; // Ignore if trying to set to negative people.
        model.setNumberOfGuests(numPeople - 1);
    }

    @FXML
    void onIncrementPeopleClicked(ActionEvent event) {
        // TODO
        int numPeople = Integer.valueOf(numPeopleInputField.getText().trim());
        model.setNumberOfGuests(numPeople + 1);
    }

    @FXML
    void onIngredientsClicked(ActionEvent event) {
        Stage stage = new Stage();
        stage.setTitle("Dinner Planner - Ingredients");
        BorderPane bp = new BorderPane();
        final IngredientsView iView = new IngredientsView(model, model.getFullMenu());
        bp.setCenter(iView);
        stage.setScene(new Scene(bp, 800, 600));
        stage.show();

//        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//            @Override
//            public void handle(WindowEvent windowEvent) {
//                model.removeListener(iView);
//            }
//        });
    }

    @FXML
    void onNumPeopleChanged(ActionEvent event) {
        model.setNumberOfGuests(Integer.valueOf(numPeopleInputField.getText().trim()));
    }

    @FXML
    void onPreparationClicked(ActionEvent event) {
        // Create a new window for the preparations.
        Stage stage = new Stage();
        stage.setTitle("Dinner Planner - Preparation");

        // Create a reference to the controller.
        final FullMenuController controller = new FullMenuController(model);
        stage.setScene(new Scene(new FullMenuView(controller), 600, 400));

//        // When the new stage closes remove the controller from the model.
//        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//            @Override
//            public void handle(WindowEvent windowEvent) {
//                model.removeListener(controller);
//            }
//        });
        stage.show();
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
        assert dragAndDropNewPane != null : "fx:id=\"dragAndDropNewPane\" was not injected: check your FXML file 'MainView.fxml'.";


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
        dropDishBox.managedProperty().bind(dropDishBox.visibleProperty());

        // Add a search and portfolio view.
        starterTab.setContent(new SearchView(starterDishController));
        mainTab.setContent(new SearchView(mainDishController));
        dessertTab.setContent(new SearchView(dessertController));

        // Create a Drag event handler and add it to the correct pane for dropping menu items.
        DragEventHandler handler = new DragEventHandler();
        dragAndDropNewPane.setOnDragOver(handler);
        dragAndDropNewPane.setOnDragEntered(handler);
        dragAndDropNewPane.setOnDragExited(handler);
        dragAndDropNewPane.setOnDragDropped(handler);

        // Allow the vboc children to expand in width with the VBox.
        menuPanel.setFillWidth(true);
        dropDishBox.setFillWidth(true);

        // For simplicity reasons don't allow users to edit manually.
        numPeopleInputField.editableProperty().set(false);
        // Bind the number of guest to the textfield.
        numPeopleInputField.textProperty().bind(model.numberOfGuestsProperty().asString());

        // Set the current price of the menu.
        updatePrice();

        model.getSelectedDishes().addListener(new MapChangeListener<Integer, Dish>() {
            @Override
            public void onChanged(Change<? extends Integer, ? extends Dish> change) {
                if (change.wasAdded()) {
                    addDishToSelected(change.getValueAdded());
                } else if (change.wasRemoved()) {
                    removeDishFromSelected(change.getValueRemoved());
                }
                // Update the price
                updatePrice();

                // Update Buttons
                updateButtons();
            }
        });

        model.numberOfGuestsProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                updatePrice();
            }
        });
    }

    /**
     * Adds a dish to the Full Menu.
     * <b>Dish cannot be null.
     *
     * @param dish Dish to add to the menu.
     */
    private void addDishToSelected(Dish dish) {
        assert dish != null: "Dish being selected cannot be null";

        // Update the Selected dish view.
        BorderPane view;
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
        view.setCenter(new AddedDishItemView(model, dish));
        view.setVisible(true);
    }

    private void removeDishFromSelected(Dish dish) {
        assert dish != null: "Dish being removed cannot be null";

        BorderPane view;
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
        view.setVisible(false);
    }

    /**
     * Updates the price of the menu based off of the models current state.
     */
    private void updatePrice() {
        double cost = 0;
        for (Dish dish: model.getFullMenu()) {
            if (dish == null) continue;
            cost += dish.getPrice();
        }
        cost = cost * model.getNumberOfGuests();
        Locale locale = Locale.getDefault();
        Currency currentCurrency = Currency.getInstance(locale);
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        String costStr = currencyFormatter.format(cost) + " " + currentCurrency.getDisplayName();
        totalCostLabel.setText(costStr);

    }

    /**
     * Updates the buttons based on whether or not there are dishes in the menu.
     */
    private void updateButtons() {
        boolean disable = model.getFullMenu().isEmpty();
        prepButton.setDisable(disable);
        ingButton.setDisable(disable);
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
                if (dragEvent.getGestureSource() != dragAndDropNewPane &&
                        dragEvent.getDragboard().hasString() && hasDishByName(dragEvent.getDragboard().getString())) {
                    dragEvent.acceptTransferModes(TransferMode.ANY);
                    dragDishLabel.setTextFill(Color.ALICEBLUE);
                }
            } else if (type.equals(DragEvent.DRAG_ENTERED)) {
                /**
                 * Only allow drag events with names of dishes that we have.
                 */
                if (dragEvent.getGestureSource() != dragAndDropNewPane &&
                        dragEvent.getDragboard().hasString() && hasDishByName(
                        dragEvent.getDragboard().getString())) {
                    dragDishLabel.setTextFill(Color.GRAY);
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
                    // Update the model.
                    model.addToMenu(dish);
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
