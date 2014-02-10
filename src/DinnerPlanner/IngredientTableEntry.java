package DinnerPlanner;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import se.kth.csc.iprog.dinnerplanner.model.Ingredient;

/**
 * Created by sandstroh on 2/8/14.
 */
public class IngredientTableEntry {

    private SimpleStringProperty name;
    private SimpleDoubleProperty quantity;
    private SimpleDoubleProperty price;

    public IngredientTableEntry(Ingredient ingredient) {
        this.name = new SimpleStringProperty(ingredient.getName());
        this.quantity = new SimpleDoubleProperty(ingredient.getQuantity());
        this.price = new SimpleDoubleProperty(ingredient.getPrice());
    }

    public String getName() {
        return name.get();
    }

    public Double getQuantity() {
        return quantity.get();
    }

    public Double getPrice() {
        return price.get();
    }

}
