package se.kth.csc.iprog.dinnerplanner.model;

/**
 * Class that represents an ingredient for a specific dish.
 */
public class Ingredient {

    private final String name, unit;
    double quantity;
    private final double price;
     // can be empty, for example in case of eggs (there is not unit)

    /**
     * Creates Ingredient class.
     *
     * @param name Name of the ingredient.
     * @param quantity Initial Quantity of this ingredient.
     * @param unit Unit of measurement
     * @param price Price of the ingredient.
     * // TODO: Change price to price per unit.  That way we can calculate price as qty changes.
     */
    public Ingredient(String name, double quantity, String unit, double price){
        this.name = name;
        this.quantity=quantity;
        this.unit = unit;
        this.price = price;
    }

    public String getName() {
        return name;
    }
    public double getQuantity() {
        return quantity;
    }
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
    public String getUnit() {
        return unit;
    }
    public double getPrice() {
        return price;
    }


}
