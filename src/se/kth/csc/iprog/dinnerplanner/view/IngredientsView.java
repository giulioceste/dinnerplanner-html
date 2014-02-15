package se.kth.csc.iprog.dinnerplanner.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import loader.ViewLoader;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import se.kth.csc.iprog.dinnerplanner.model.Ingredient;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sandstroh on 2/8/14.
 */
public class IngredientsView extends Pane {

    private final Set<Dish> dishes;

    @FXML
    private Pane paneContainer;

    @FXML
    private TableView tableIngredients;

    @FXML
    private TableColumn columnIngredients;

    @FXML
    private TableColumn columnUnit;

    @FXML
    private TableColumn columnQuantity;

    @FXML
    private TableColumn columnPricePerUnit;

    @FXML
    private TableColumn columnPrice;

    public IngredientsView(Set<Dish> dishes) {
        try {
            ViewLoader.load(this, "IngredientsView.fxml", this);
        } catch (IOException e) {
            throw new RuntimeException("Can't find view " + "IngredientsView.fxml");
        }
        this.dishes = new HashSet<Dish>(dishes);

        // Set all the property values of the UI.
        initUI();
        displayData();
    }


    private void initUI() {

        // resize table & columns when the pane is expanded
        tableIngredients.prefWidthProperty().bind(paneContainer.widthProperty());
        tableIngredients.prefHeightProperty().bind(paneContainer.heightProperty());

        columnIngredients.prefWidthProperty().bind(tableIngredients.widthProperty().multiply(0.6));
        columnUnit.prefWidthProperty().bind(tableIngredients.widthProperty().multiply(0.1));
        columnQuantity.prefWidthProperty().bind(tableIngredients.widthProperty().multiply(0.1));
        columnPricePerUnit.prefWidthProperty().bind(tableIngredients.widthProperty().multiply(0.1));
        columnPrice.prefWidthProperty().bind(tableIngredients.widthProperty().multiply(0.1));

        // column content
        columnIngredients.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("name"));
        columnUnit.setCellValueFactory(new PropertyValueFactory<Ingredient, Double>("unit"));
        columnQuantity.setCellValueFactory(new PropertyValueFactory<Ingredient, Double>("quantity"));
        columnPricePerUnit.setCellValueFactory(new PropertyValueFactory<Ingredient, Double>("pricePerUnit"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<Ingredient, Double>("price"));

        //
        columnQuantity.setEditable(true);
        columnQuantity.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Ingredient, Double>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Ingredient, Double> cellEditEvent) {
                Double amount = cellEditEvent.getNewValue();
                Double oldAmount = cellEditEvent.getOldValue();
                // Check if the amount is valid.
                if (amount == null) {
                    // TODO figure out how to cancel.
                    return;
                }

                // Update the quantity.
                Ingredient ingredient = cellEditEvent.getRowValue();
                ingredient.setQuantity(amount);
            }
        });
    }

    /**
     * Display the ingredients of the given dishes.
     */
    private void displayData() {

        // merge ingredients
        HashMap<String, Ingredient> allIngredients = new HashMap<String, Ingredient>();
        for (Dish dish : dishes) {
            for (Ingredient ingredient : dish.getIngredients()) {
                if (allIngredients.containsKey(ingredient.getName())) {
                    Ingredient mapIngredient = allIngredients.get(ingredient.getName());
                    mapIngredient.setQuantity(mapIngredient.getQuantity() + ingredient.getQuantity());
                } else {
                    // we have to create a new ingredient object, otherwise the ingredient object
                    // in the model gets changed every time we merge another ingredient object of
                    // the same type to it
                    Ingredient newIngredient = new Ingredient(ingredient.getName(),
                            ingredient.getQuantity(), ingredient.getUnit(), ingredient.getPrice());
                    allIngredients.put(newIngredient.getName(), newIngredient);
                }
            }
        }

        ObservableList<Ingredient> ingredients = FXCollections.observableArrayList();
        for (String ingredientName : allIngredients.keySet()) {
            Ingredient ingr = allIngredients.get(ingredientName);
            ingredients.add(ingr);
        }

        tableIngredients.setItems(ingredients);
    }

    @FXML
    void initialize() {

        assert tableIngredients != null : "fx:id=\"tableIngredients\" was not injected: check your FXML file 'IngredientsView.fxml'.";
        assert columnIngredients != null : "fx:id=\"columnIngredients\" was not injected: check your FXML file 'IngredientsView.fxml'.";
        assert columnUnit != null : "fx:id=\"columnUnit\" was not injected: check your FXML file 'IngredientsView.fxml'.";
        assert columnQuantity != null : "fx:id=\"columnQuantity\" was not injected: check your FXML file 'IngredientsView.fxml'.";
        assert columnPricePerUnit != null : "fx:id=\"columnPricePerUnit\" was not injected: check your FXML file 'IngredientsView.fxml'.";
        assert columnPrice != null : "fx:id=\"columnPrice\" was not injected: check your FXML file 'IngredientsView.fxml'.";

    }

}
