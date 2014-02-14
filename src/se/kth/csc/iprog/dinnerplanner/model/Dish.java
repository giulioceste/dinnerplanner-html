package se.kth.csc.iprog.dinnerplanner.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import loader.ImageLoader;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

public class Dish {

    public static final int STARTER = 1;
    public static final int MAIN = 2;
    public static final int DESSERT = 3;

    private final StringProperty name, image, description;
    private final IntegerProperty type; // starter (1), main (2) or desert (3)
    private final ObservableList<Ingredient> ingredients;

    /**
     * Creates a dish with standard
     *
     * @param name Name of the dish
     * @param type Type of the dish
     * @param image Image to associate to this dish.
     * @param description Description about this dish.
     */
    public Dish(String name, int type, String image, String description) {
        // Name of the dish should be never be null.
        if (name == null)
            throw new IllegalArgumentException("Name of dish cannot be null");

        // Safely assign the description of the dish.
        if (description == null) {
            description = "";
        }

        // Make sure we have a correct type.
        if (!(type == 1 || type == 2 || type == 3))
            throw new IllegalArgumentException("Invalid type: " + type);
        try {
            Image img = ImageLoader.load(image);
        } catch (FileNotFoundException e) {
            image = "no-image.png";
        }

        this.name = new SimpleStringProperty(name);
        this.type = new SimpleIntegerProperty(type);
        this.image = new SimpleStringProperty(image);
        this.description = new SimpleStringProperty(description);
        ingredients = FXCollections.observableArrayList();
    }

    // JavaFX Properties.
    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty imageProperty() {
        return image;
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public IntegerProperty typeProperty() {
        return type;
    }

    public ObservableList<Ingredient> ingredientsPropert() {
        return FXCollections.unmodifiableObservableList(ingredients);
    }

    // Standard Getters.

    public String getName() {
        return name.get();
    }

    public int getType() {
        return type.get();
    }

    public String getImage() {
        return image.get();
    }

    public String getDescription() {
        return description.get();
    }

    public Set<Ingredient> getIngredients(){
        return new HashSet<Ingredient>(ingredients);
    }

    // Mutators
    public void addIngredient(Ingredient ing){
        if (ingredients.contains(ing)) return;
        ingredients.add(ing);
    }

    public void removeIngredient(Ingredient ing){
        ingredients.remove(ing);
    }

    public boolean contains(String filter){
        if(name.get().toLowerCase().contains(filter.toLowerCase())){
            return true;
        }
        for(Ingredient i : ingredients){
            if(i.getName().toLowerCase().contains(filter.toLowerCase())){
                return true;
            }
        }
        return false;
    }

    public double getPrice()
    {
        double count = 0;
        for(Ingredient i: ingredients)
        {
            count += i.getPrice();
        }
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dish)) return false;

        Dish dish = (Dish) o;
        if (!description.equals(dish.description)) return false;
        if (!image.equals(dish.image)) return false;
        if (!ingredients.equals(dish.ingredients)) return false;
        if (!name.equals(dish.name)) return false;
        if (!type.equals(dish.type)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + image.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + ingredients.hashCode();
        return result;
    }
}
