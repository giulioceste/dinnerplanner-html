package DinnerPlanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import loader.ImageLoader;
import loader.ViewLoader;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by halitanil on 08.02.2014.
 */
public class AddedDishItemView extends HBox {

    private final DinnerModel model;
    private final Dish dish;

    private static final String STARTER = "Starter";
    private static final String MAIN = "Main";
    private static final String DESSERT = "Dessert";

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView addedDishImage;

    @FXML
    private Button cancelDish;

    @FXML
    private Label costAddedDishLabel, dishLabel;

    @FXML
    void initialize() {
        assert addedDishImage != null : "fx:id=\"addedDishImage\" was not injected: check your FXML file 'addedDishItem.fxml'.";
        assert cancelDish != null : "fx:id=\"cancelDish\" was not injected: check your FXML file 'addedDishItem.fxml'.";
        assert costAddedDishLabel != null : "fx:id=\"costAddedDishLabel\" was not injected: check your FXML file 'addedDishItem.fxml'.";
        assert dishLabel != null : "fx:id=\"dishLabel\" was not injected: check your FXML file 'addedDishItem.fxml'.";

    }

    public AddedDishItemView(DinnerModel model, Dish dish)
    {
        // Argument check.
        if (model == null)
            throw new IllegalArgumentException("Model cannot be null");
        if (dish == null)
            throw new IllegalArgumentException("Dish cannot be null");
        this.model = model;
        this.dish = dish;
        try{
            ViewLoader.load(this,"addedDishItem.fxml", this);
        }catch(Exception ex){
            throw new RuntimeException("Error loading the view DishPortfolioView.fxml: " + ex.getMessage());
        }
        try {
            addedDishImage.setImage(ImageLoader.load(dish.getImage()));
        } catch (FileNotFoundException e) {
            addedDishImage.managedProperty().bind(addedDishImage.visibleProperty());
            addedDishImage.setVisible(false);
        }

        String label = "";
        switch (dish.getType()) {
            case Dish.STARTER:
                label += STARTER;
                break;
            case Dish.MAIN:
                label += MAIN;
                break;
            default:
                label += DESSERT;
        }
        label += ": " + dish.getName();
        dishLabel.setText(label);
        costAddedDishLabel.setText("$3.02");
    }

    @FXML
    void onRemoveDish(ActionEvent event) {
        model.removeFromMenu(dish);
    }
}
