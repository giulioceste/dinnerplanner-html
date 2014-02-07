package DinnerPlanner;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button decrPeopleButton;

    @FXML
    private Tab dessertTab;

    @FXML
    private Pane dishPane;

    @FXML
    private Label dragDishLabel;

    @FXML
    private Button incPeopleButton;

    @FXML
    private Button ingButton;

    @FXML
    private Tab mainTab;

    @FXML
    private TextField numPeopleInputField;

    @FXML
    private Button prepButton;

    @FXML
    private Tab starterTab;

  @FXML
    private Label totalCostLabel;


    @FXML
    void onDecrementPeopleClicked(ActionEvent event) {
    }

    @FXML
    void onIncrementPeopleClicked(ActionEvent event) {
    }

    @FXML
    void onIngredientsClicked(ActionEvent event) {
    }

    @FXML
    void onNumPeopleChanged(ActionEvent event) {
    }

    @FXML
    void onPreparationClicked(ActionEvent event) {
    }

    @FXML
    void initialize() {
        assert decrPeopleButton != null : "fx:id=\"decrPeopleButton\" was not injected: check your FXML file 'StartView.fxml'.";
        assert dessertTab != null : "fx:id=\"dessertTab\" was not injected: check your FXML file 'StartView.fxml'.";
        assert dishPane != null : "fx:id=\"dishPane\" was not injected: check your FXML file 'StartView.fxml'.";
        assert dragDishLabel != null : "fx:id=\"dragDishLabel\" was not injected: check your FXML file 'StartView.fxml'.";
        assert incPeopleButton != null : "fx:id=\"incPeopleButton\" was not injected: check your FXML file 'StartView.fxml'.";
        assert ingButton != null : "fx:id=\"ingButton\" was not injected: check your FXML file 'StartView.fxml'.";
        assert mainTab != null : "fx:id=\"mainTab\" was not injected: check your FXML file 'StartView.fxml'.";
        assert numPeopleInputField != null : "fx:id=\"numPeopleInputField\" was not injected: check your FXML file 'StartView.fxml'.";
        assert prepButton != null : "fx:id=\"prepButton\" was not injected: check your FXML file 'StartView.fxml'.";
        assert starterTab != null : "fx:id=\"starterTab\" was not injected: check your FXML file 'StartView.fxml'.";
        assert totalCostLabel != null : "fx:id=\"totalCostLabel\" was not injected: check your FXML file 'StartView.fxml'.";


    }

}