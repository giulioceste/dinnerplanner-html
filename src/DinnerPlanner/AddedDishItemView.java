package DinnerPlanner;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import loader.ImageLoader;
import loader.ViewLoader;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by halitanil on 08.02.2014.
 */
public class AddedDishItemView extends BorderPane{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView addedDishImage;

    @FXML
    private Button cancelDish;

    @FXML
    private Label costAddedDishLabel;

    @FXML
    private Label nameOfDish;

    @FXML
    private Label starterLabel;

    @FXML
    void initialize() {
        assert addedDishImage != null : "fx:id=\"addedDishImage\" was not injected: check your FXML file 'addedDishItem.fxml'.";
        assert cancelDish != null : "fx:id=\"cancelDish\" was not injected: check your FXML file 'addedDishItem.fxml'.";
        assert costAddedDishLabel != null : "fx:id=\"costAddedDishLabel\" was not injected: check your FXML file 'addedDishItem.fxml'.";
        assert nameOfDish != null : "fx:id=\"nameOfDish\" was not injected: check your FXML file 'addedDishItem.fxml'.";
        assert starterLabel != null : "fx:id=\"starterLabel\" was not injected: check your FXML file 'addedDishItem.fxml'.";


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

        starterLabel.setText(dish.getName());
        costAddedDishLabel.setText("$3.02");
    }
}
