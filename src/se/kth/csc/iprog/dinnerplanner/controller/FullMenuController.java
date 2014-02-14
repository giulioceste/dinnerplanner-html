package se.kth.csc.iprog.dinnerplanner.controller;

import javafx.collections.MapChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by Giulio on 08/02/14.
 */
public class FullMenuController {

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

    private final Map<Integer, Label> labels;
    private final Map<Integer, TextArea> areas;

    public FullMenuController(DinnerModel model) {
        this.model = model;
        labels = new HashMap<Integer, Label>();
        areas = new HashMap<Integer, TextArea>();
    }

    @FXML
    void initialize() {
        assert dessertArea != null : "fx:id=\"dessertArea\" was not injected: check your FXML file 'PreparationView.fxml'.";
        assert dessertLabel != null : "fx:id=\"dessertLabel\" was not injected: check your FXML file 'PreparationView.fxml'.";
        assert mainArea != null : "fx:id=\"mainArea\" was not injected: check your FXML file 'PreparationView.fxml'.";
        assert mainLabel != null : "fx:id=\"mainLabel\" was not injected: check your FXML file 'PreparationView.fxml'.";
        assert starterArea != null : "fx:id=\"starterArea\" was not injected: check your FXML file 'PreparationView.fxml'.";
        assert starterLabel != null : "fx:id=\"starterLabel\" was not injected: check your FXML file 'PreparationView.fxml'.";

        labels.put(Dish.STARTER, starterLabel);
        labels.put(Dish.MAIN, mainLabel);
        labels.put(Dish.DESSERT, dessertLabel);

        areas.put(Dish.STARTER, starterArea);
        areas.put(Dish.MAIN, mainArea);
        areas.put(Dish.DESSERT, dessertArea);

        setupUI();
    }

    private void setupUI() {

        // initially set up the with the dished currently in the menu.
        for (Dish dish: model.getFullMenu()) {
            if (dish == null) continue;
            addDish(dish);
        }

        // Every time a dish is added to or removed from the model
        // then update the view.
        model.getSelectedDishes().addListener(new MapChangeListener<Integer, Dish>() {
            @Override
            public void onChanged(Change<? extends Integer, ? extends Dish> change) {
                if (change.wasAdded()) {
                    Dish added = change.getValueAdded();
                    if (added == null) return;
                    addDish(added);
                } else if (change.wasRemoved()) {
                    Dish removed = change.getValueRemoved();
                    if (removed == null) return;
                    hideTextByType(removed.getType());
                }
            }
        });
    }

    private void addDish(Dish dish) {
        assert dish != null;
        setUpText(labels.get(dish.getType()), areas.get(dish.getType()), dish);
    }

    private void hideTextByType(int type) {
        areas.get(type).setVisible(false);
    }

    private static void setUpText(Label label, TextArea area, Dish d) {
        // Shouldnt have null fields
        assert label != null && area != null && d != null;

        // If any view becomes invisible then exclude it from the layout.
        label.managedProperty().bind(label.visibleProperty());
        area.managedProperty().bind(area.visibleProperty());

        // Area visibility implies label visibility.
        label.visibleProperty().bindBidirectional(area.visibleProperty());

        // Bind the actual text to the dish element.
        label.textProperty().bind(d.nameProperty());
        area.textProperty().bind(d.descriptionProperty());
    }
}
