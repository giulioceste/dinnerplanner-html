package se.kth.csc.iprog.dinnerplanner.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import se.kth.csc.iprog.dinnerplanner.view.DishPortfolioView;
import se.kth.csc.iprog.dinnerplanner.view.DishView;

import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

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
        // TODO not used because.
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
//        //adding every dish to the portfolio view.
//        dishFlowPane.getStyleClass().add("/layouts/Main.css");
//        scrollPane.getStyleClass().add("/layouts/Main.css");

        // Apply filter to the search box.
        searchBox.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue,
                                String oldValue, String newValue) {
                if (newValue == null)
                    newValue = "";
                newValue = newValue.trim();
                filterDishesOfType(newValue);
            }
        });

        String initFilter = searchBox.getText().trim();
        filterDishesOfType(initFilter);
    }

    /**
     * Returns the set of dishes of specific type, that contain filter in their name
     * or name of any ingredient.
     */
    public void filterDishesOfType(String filter) {

        // Get the set of dishes.
        Set<Dish> result = new HashSet<Dish>();
        for (Dish d : dishes) {
            if (d.contains(filter))
                result.add(d);
        }

        // Show the result of the filter.
        showDishes(result);
    }

    private void showDishes(Collection<Dish> dishes) {
        assert dishes != null : "Dishes cannot be null";
        dishFlowPane.getChildren().clear();

        // For every dish to show.
        for (final Dish dish : dishes) {
            if (dish == null) continue;

            // Create a view for every dish.
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

            // Make sure we handle the double click appropiately.
            view.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    // On a double click of the primary mouse button open up a dish specific page.
                    if(mouseEvent.getButton().equals(MouseButton.PRIMARY) && mouseEvent.getClickCount() == 2){
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
