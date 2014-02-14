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
        this.quantity = quantity;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingredient)) return false;

        Ingredient that = (Ingredient) o;

        if (Double.compare(that.price, price) != 0) return false;
        if (Double.compare(that.quantity, quantity) != 0) return false;
        if (!name.equals(that.name)) return false;
        if (!unit.equals(that.unit)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name.hashCode();
        result = 31 * result + unit.hashCode();
        temp = Double.doubleToLongBits(quantity);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
