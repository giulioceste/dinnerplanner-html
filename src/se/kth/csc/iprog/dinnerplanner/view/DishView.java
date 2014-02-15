package se.kth.csc.iprog.dinnerplanner.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import loader.ImageLoader;
import loader.ViewLoader;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.*;

/**
 * Created by sandstroh on 2/8/14.
 */
public class DishView extends BorderPane {

    private final DinnerModel model;
    private final Dish selectedDish;
    private final StringProperty costProp;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private SplitPane dishViewSplitPane;

    @FXML
    private ImageView imageDish;

    @FXML
    private Label labelCostsPersons, labelDishName;

    @FXML
    private TextArea textPreparation;


    public DishView(DinnerModel model, Dish selectedDish) {
        this.model = model;
        this.selectedDish = selectedDish;
        try {
            ViewLoader.load(this, "DishView.fxml", this);
        } catch (IOException e) {
            throw new RuntimeException("Can't find view " + "DishView.fxml");
        }

        // Assign the name.
        labelDishName.textProperty().bind(selectedDish.nameProperty());

        // Set the cost property.
        costProp = new SimpleStringProperty();
        updatePrice();

        labelCostsPersons.textProperty().bind(costProp.concat(new SimpleStringProperty(" for ")).concat(
                model.numberOfGuestsProperty().asString()).concat(" people"));

        // Update the description of the property.
        StringProperty prepProp = new SimpleStringProperty("Preparation:\n\n");
        textPreparation.textProperty().bind(prepProp.concat(selectedDish.descriptionProperty()));

        // Load the image of the dish.
        try {
            imageDish.setImage(ImageLoader.load(selectedDish.getImage()));
        } catch (IOException e) {
            throw new RuntimeException("Can't load image " + selectedDish.getImage());
        }

    }

    /**
     *
     */
    private void updatePrice() {
        Locale locale = Locale.getDefault();
        Currency currentCurrency = Currency.getInstance(locale);
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        String costStr = currencyFormatter.format(selectedDish.getPrice()) + " " + currentCurrency.getDisplayName();
        costProp.setValue(costStr);
    }

    @FXML
    void initialize() {
        assert dishViewSplitPane != null : "fx:id=\"dishViewSplitPane\" was not injected: check your FXML file 'DishView.fxml'.";
        assert imageDish != null : "fx:id=\"imageDish\" was not injected: check your FXML file 'DishView.fxml'.";
        assert labelCostsPersons != null : "fx:id=\"labelCostsPersons\" was not injected: check your FXML file 'DishView.fxml'.";
        assert labelDishName != null : "fx:id=\"labelDishName\" was not injected: check your FXML file 'DishView.fxml'.";
        assert textPreparation != null : "fx:id=\"textPreparation\" was not injected: check your FXML file 'DishView.fxml'.";
        initUI();
    }

    /**
     * Initializes the UI after every reference has been set.
     */
    private void initUI() {
        Set<Dish> dishSet = new HashSet<Dish>();
        dishSet.add(selectedDish);
        IngredientsView iView = new IngredientsView(dishSet);
        dishViewSplitPane.getItems().add(iView);
    }
}
