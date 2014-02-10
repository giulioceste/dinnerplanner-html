package DinnerPlanner;

import javafx.fxml.FXML;
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
public class DishPortfolioView extends BorderPane {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView dishImage;

    @FXML
    private Label dishNameLabel;

    @FXML
    void initialize() {
        assert dishImage != null : "fx:id=\"dishImage\" was not injected: check your FXML file 'DishPortfolioView.fxml'.";
        assert dishNameLabel != null : "fx:id=\"dishNameLabel\" was not injected: check your FXML file 'DishPortfolioView.fxml'.";
    }

    public DishPortfolioView(Dish dish)
    {
        try{
            ViewLoader.load(this,"DishPortfolioView.fxml",this);
        }catch(Exception ex){
            throw new RuntimeException("Error loading the view DishPortfolioView.fxml");
        }
        try {
            dishImage.setImage(ImageLoader.load(dish.getImage()));
        } catch (FileNotFoundException e) {
            dishImage.managedProperty().bind(dishImage.visibleProperty());
            dishImage.setVisible(false);
        }
            dishNameLabel.setText(dish.getName());
    }
}
