package se.kth.csc.iprog.dinnerplanner.view;

import se.kth.csc.iprog.dinnerplanner.model.IngredientTableEntry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import loader.ImageLoader;
import loader.ViewLoader;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import se.kth.csc.iprog.dinnerplanner.model.Ingredient;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Set;

/**
 * Created by sandstroh on 2/8/14.
 */
public class DishView extends BorderPane {

    private final DinnerModel model;
    private final Dish selectedDish;

    @FXML
    private Label labelDishName;

    @FXML
    private Label labelCostsPersons;

    @FXML
    private ImageView imageDish;

    @FXML
    private TextArea textPreparation;

    @FXML
    private TableView tableIngredients;

    @FXML
    private TableColumn columnIngredient;

    @FXML
    private TableColumn columnQuantity;

    @FXML
    private TableColumn columnCosts;

    public DishView(DinnerModel model, Dish selectedDish) {

        try {
            ViewLoader.load(this, "DishView.fxml", this);
        } catch (IOException e) {
            throw new RuntimeException("Can't find view " + "DishView.fxml");
        }

        this.model = model;
        this.selectedDish = selectedDish;

        labelDishName.setText(selectedDish.getName());
        labelCostsPersons.setText(String.format("$ %s for %d persons",
                new DecimalFormat("###.##").format(
                        selectedDish.getPrice() * model.getNumberOfGuests()),
                        model.getNumberOfGuests()));
        textPreparation.setText("Preparation:\n\n" + selectedDish.getDescription());
        try {
        imageDish.setImage(ImageLoader.load(selectedDish.getImage()));
        } catch (IOException e) {
            ;
        }

        displayTableData(selectedDish.getIngredients());

    }

    public void displayTableData(Set<Ingredient> ingrs) {

        ObservableList<IngredientTableEntry> ingredients = FXCollections.observableArrayList();
        for (Ingredient ingr : ingrs) {
            ingredients.add(new IngredientTableEntry(ingr));
        }
        tableIngredients.setItems(ingredients);

        columnIngredient.setCellValueFactory(new PropertyValueFactory<IngredientTableEntry, String>("name"));
        columnQuantity.setCellValueFactory(new PropertyValueFactory<IngredientTableEntry, Double>("quantity"));
        columnCosts.setCellValueFactory(new PropertyValueFactory<IngredientTableEntry, Double>("price"));

    }

    @FXML
    void initialize() {

        assert labelDishName != null : "fx:id=\"labelDishName\" was not injected: check your FXML file 'DishView.fxml'.";
        assert labelCostsPersons != null : "fx:id=\"labelCostsPersons\" was not injected: check your FXML file 'DishView.fxml'.";
        assert imageDish != null : "fx:id=\"imageDish\" was not injected: check your FXML file 'DishView.fxml'.";
        assert textPreparation != null : "fx:id=\"textPreparation\" was not injected: check your FXML file 'DishView.fxml'.";

        assert tableIngredients != null : "fx:id=\"tableIngredients\" was not injected: check your FXML file 'DishView.fxml'.";
        assert columnIngredient != null : "fx:id=\"columnIngredient\" was not injected: check your FXML file 'DishView.fxml'.";
        assert columnQuantity != null : "fx:id=\"columnQuantity\" was not injected: check your FXML file 'DishView.fxml'.";
        assert columnCosts != null : "fx:id=\"columnCosts\" was not injected: check your FXML file 'DishView.fxml'.";

    }

}
