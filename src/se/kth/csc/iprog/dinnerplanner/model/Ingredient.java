package se.kth.csc.iprog.dinnerplanner.model;

import javafx.beans.property.*;

/**
 * Class that represents an ingredient for a specific dish.
 */
public class Ingredient {

    private final StringProperty name, unit;
    private DoubleProperty quantity;
    private final DoubleProperty pricePerUnit, price;

    /**
     * Creates Ingredient class.
     *
     * @param name Name of the ingredient.
     * @param quantity Initial Quantity of this ingredient.
     * @param unit Unit of measurement
     * @param pricePerUnit Price per unit of the ingredient.
     */
    public Ingredient(String name, double quantity, String unit, double pricePerUnit){
        this.name = new SimpleStringProperty(name);
        this.quantity = new SimpleDoubleProperty(quantity);
        this.unit = new SimpleStringProperty(unit);
        this.pricePerUnit = new SimpleDoubleProperty(pricePerUnit);
        this.price = new SimpleDoubleProperty();
        this.price.bind(this.quantity.multiply(this.pricePerUnit));
    }

    // Access to Property values observable binding.

    /**
     * @return The name property
     */
    public ReadOnlyStringProperty nameProperty() {
        return name;
    }

    /**
     * @return The Quantity Property.
     */
    public DoubleProperty quantityProperty() {
        return quantity;
    }

    /**
     * @return The unit property
     */
    public ReadOnlyStringProperty unitProperty() {
        return unit;
    }

    /**
     * @return The pricePerUnit property.
     */
    public ReadOnlyDoubleProperty pricePerUnitProperty() {
        return pricePerUnit;
    }

    /**
     * @return The total price calculated from the quantity.
     */
    public ReadOnlyDoubleProperty priceProperty() {
        return price;
    }

    // Setters

    /**
     * Sets the number of units of this ingredient.
     * @param quantity Quantity of the ingredient.
     */
    public void setQuantity(double quantity) {
        this.quantity.set(quantity);
    }

    // Getters

    public double getPricePerUnit() {
        return pricePerUnit.get();
    }

    public double getQuantity() {
        return quantity.get();
    }

    public String getUnit() {
        return unit.get();
    }

    public String getName() {
        return name.get();
    }

    public double getPrice() {
        return price.get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingredient)) return false;

        Ingredient that = (Ingredient) o;
        if (!name.equals(that.name)) return false;
        if (!pricePerUnit.equals(that.pricePerUnit)) return false;
        if (!unit.equals(that.unit)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + unit.hashCode();
        result = 31 * result + pricePerUnit.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "pricePerUnit=" + pricePerUnit +
                ", quantity=" + quantity +
                ", unit=" + unit +
                ", name=" + name +
                '}';
    }
}
