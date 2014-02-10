package DinnerPlanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

/**
 * Created by halitanil on 08.02.2014.
 */
public class SearchController {

    private final Collection<Dish> dishes;

    public SearchController(Collection<Dish> dishes) {
        if(dishes == null)
            throw new IllegalArgumentException("The dishes cannot be null!");
        this.dishes = dishes;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private FlowPane dishFlowPane;

    @FXML
    private TextField searchBox;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    void onSearch(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert dishFlowPane != null : "fx:id=\"dishFlowPane\" was not injected: check your FXML file 'SearchView.fxml'.";
        assert scrollPane != null : "fx:id=\"scrollPane\" was not injected: check your FXML file 'SearchView.fxml'.";
        assert searchBox != null : "fx:id=\"searchBox\" was not injected: check your FXML file 'SearchView.fxml'.";
        setupUI();
    }

    private void setupUI() {
        //make the flow pane strech in width with the scroll pane
        scrollPane.fitToWidthProperty().set(true);
        //adding every dish to the portfolio view.
        dishFlowPane.getStyleClass().add("/layouts/Main.css");
        scrollPane.getStyleClass().add("/layouts/Main.css");
        for (final Dish dish: dishes)
        {
            DishPortfolioView view = new DishPortfolioView(dish);
            dishFlowPane.getChildren().add(view);
            //we are enabling the drag and drop event.
            // Every dish portfolio view will be treated as a source for a drag and drop event
            view.setOnDragDetected(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Dragboard db = dishFlowPane.startDragAndDrop(TransferMode.ANY);
                    // temporarily placıng the dish name in the clipboard
                    ClipboardContent content = new ClipboardContent();
                    content.putString(dish.getName());
                    db.setContent(content);
                    mouseEvent.consume();
                }
            });

            view.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    // On a double click of the primary mouse button open up a dish specific page.
                    if(mouseEvent.getButton().equals(MouseButton.PRIMARY) && mouseEvent.getClickCount() == 2){
                        // TODO Open up DishView about this particular dish.
                        Stage stage = new Stage();
                        stage.setTitle("Dinner Planner - " + dish.getName());
                        stage.setScene(new Scene(new DishView(DinnerModel.getInstance(),
                                dish), 600, 400));
                        stage.show();
                    }
                }
            });
        }
    }

}
