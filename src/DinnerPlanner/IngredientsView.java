package DinnerPlanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import loader.ViewLoader;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import se.kth.csc.iprog.dinnerplanner.model.Ingredient;

import java.io.IOException;
import java.util.Set;

/**
 * Created by sandstroh on 2/8/14.
 */
public class IngredientsView extends Pane {

    private final DinnerModel model;
    private final Dish selectedDish;

    @FXML
    private TableView tableIngredients;

    @FXML
    private TableColumn columnIngredients;

    @FXML
    private TableColumn columnQuantity;

    @FXML
    private TableColumn columnCosts;

    public IngredientsView(DinnerModel model, Dish selectedDish) {

        try {
            ViewLoader.load(this, "IngredientsView.fxml", this);
        } catch (IOException e) {
            throw new RuntimeException("Can't find view " + "IngredientsView.fxml");
        }

        this.model = model;
        this.selectedDish = selectedDish;

        displayData(selectedDish.getIngredients());

    }

    public void displayData(Set<Ingredient> ingrs) {

        ObservableList<IngredientTableEntry> ingredients = FXCollections.observableArrayList();
        for (Ingredient ingr : ingrs) {
            ingredients.add(new IngredientTableEntry(ingr));
        }
        tableIngredients.setItems(ingredients);

        columnIngredients.setCellValueFactory(new PropertyValueFactory<IngredientTableEntry, String>("name"));
        columnQuantity.setCellValueFactory(new PropertyValueFactory<IngredientTableEntry, Double>("quantity"));
        columnCosts.setCellValueFactory(new PropertyValueFactory<IngredientTableEntry, Double>("price"));

    }

    @FXML
    void initialize() {

        assert tableIngredients != null : "fx:id=\"tableIngredients\" was not injected: check your FXML file 'IngredientsView.fxml'.";
        assert columnIngredients != null : "fx:id=\"columnIngredients\" was not injected: check your FXML file 'IngredientsView.fxml'.";
        assert columnQuantity != null : "fx:id=\"columnQuantity\" was not injected: check your FXML file 'IngredientsView.fxml'.";
        assert columnCosts != null : "fx:id=\"columnCosts\" was not injected: check your FXML file 'IngredientsView.fxml'.";

    }

}
