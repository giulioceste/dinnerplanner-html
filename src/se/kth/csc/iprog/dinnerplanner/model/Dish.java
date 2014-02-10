package se.kth.csc.iprog.dinnerplanner.model;

import javafx.scene.image.Image;
import loader.ImageLoader;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

public class Dish {

    public static final int STARTER = 1;
    public static final int MAIN = 2;
    public static final int DESERT = 3;

    private final String name;
    private final int type; // starter (1), main (2) or desert (3)
    private final String image;
    private final String description;
    private final Set<Ingredient> ingredients;

    public Dish(String name, int type, String image, String description) {
        if (name == null)
            throw new IllegalArgumentException("Name of dish cannot be null");
        if (description == null) {
            description = "";
        }
        if (!(type == 1 || type == 2 || type == 3))
            throw new IllegalArgumentException("Invalid type: " + type);
        try {
            Image img = ImageLoader.load(image);
        } catch (FileNotFoundException e) {
            image = "no-image.png";
        }

        this.name = name;
        this.type = type;
        this.image = image;
        this.description = description;
        ingredients = new HashSet<Ingredient>();
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public Set<Ingredient> getIngredients(){
        return new HashSet<Ingredient>(ingredients);
    }

    public void addIngredient(Ingredient ing){
        ingredients.add(ing);
    }

    public void removeIngredient(Ingredient ing){
        ingredients.remove(ing);
    }

    public boolean contains(String filter){
        if(name.toLowerCase().contains(filter.toLowerCase())){
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
        if (type != dish.type) return false;
        if (description != null ? !description.equals(dish.description) : dish.description != null) return false;
        if (image != null ? !image.equals(dish.image) : dish.image != null) return false;
        if (!name.equals(dish.name)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + type;
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
