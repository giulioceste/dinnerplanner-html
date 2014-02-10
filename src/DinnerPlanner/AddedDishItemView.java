package DinnerPlanner;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import loader.ImageLoader;
import loader.ViewLoader;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by halitanil on 08.02.2014.
 */
public class AddedDishItemView extends HBox {

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

    public AddedDishItemView(Dish dish)
    {
        try{
            ViewLoader.load(this,"addedDishItem.fxml", this);
        }catch(Exception ex){
            throw new RuntimeException("Error loading the view DishPortfolioView.fxml");
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
}
