package se.kth.csc.iprog.dinnerplanner.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mhotan_dev on 2/14/14.
 */
public class MockData {

    public static List<Dish> getMockDishes() {
        List<Dish> dishes = new ArrayList<Dish>();
        //Adding some example data, you can add more
        Dish dish1 = new Dish("French toast",Dish.STARTER,"toast.jpg","In a large mixing bowl, beat the eggs. Add the milk, brown sugar and nutmeg; stir well to combine. Soak bread slices in the egg mixture until saturated. Heat a lightly oiled griddle or frying pan over medium high heat. Brown slices on both sides, sprinkle with cinnamon and serve hot.");
        Ingredient dish1ing1 = new Ingredient("eggs", 0.5, "", 1 / .5);
        Ingredient dish1ing2 = new Ingredient("milk", 30, "ml", 6.0 / 30);
        Ingredient dish1ing3 = new Ingredient("brown sugar", 7, "g", 1.0 / 7.0);
        Ingredient dish1ing4 = new Ingredient("ground nutmeg", 0.5, "g", 24);
        Ingredient dish1ing5 = new Ingredient("white bread", 2, "slices", 1);
        dish1.addIngredient(dish1ing1);
        dish1.addIngredient(dish1ing2);
        dish1.addIngredient(dish1ing3);
        dish1.addIngredient(dish1ing4);
        dish1.addIngredient(dish1ing5);
        dishes.add(dish1);

        Dish dish2 = new Dish("Meat balls",Dish.MAIN,"meatballs.jpg","Preheat an oven to 400 degrees F (200 degrees C). Place the beef into a mixing bowl, and season with salt, onion, garlic salt, Italian seasoning, oregano, red pepper flakes, hot pepper sauce, and Worcestershire sauce; mix well. Add the milk, Parmesan cheese, and bread crumbs. Mix until evenly blended, then form into 1 1/2-inch meatballs, and place onto a baking sheet. Bake in the preheated oven until no longer pink in the center, 20 to 25 minutes.");
        Ingredient dish2ing1 = new Ingredient("extra lean ground beef", 115, "g", 20.0 / 115);
        Ingredient dish2ing2 = new Ingredient("sea salt", 0.7, "g", 3.0 / 0.7);
        Ingredient dish2ing3 = new Ingredient("small onion, diced", 0.25, "", 2.0 / .25);
        Ingredient dish2ing4 = new Ingredient("garlic salt", 0.6, "g", 3.0 / .6);
        Ingredient dish2ing5 = new Ingredient("Italian seasoning", 0.3, "g", 3.0 / .3);
        Ingredient dish2ing6 = new Ingredient("dried oregano", 0.3, "g", 3.0 / .3);
        Ingredient dish2ing7 = new Ingredient("crushed red pepper flakes", 0.6, "g", 3.0 / .6);
        Ingredient dish2ing8 = new Ingredient("Worcestershire sauce", 16, "ml", 7.0 / 16);
        Ingredient dish2ing9 = new Ingredient("milk", 20, "ml", 4.0 / 20);
        Ingredient dish2ing10 = new Ingredient("grated Parmesan cheese", 5, "g", 8.0 / 5);
        Ingredient dish2ing11 = new Ingredient("seasoned bread crumbs", 115, "g", 4.0 / 115);
        dish2.addIngredient(dish2ing1);
        dish2.addIngredient(dish2ing2);
        dish2.addIngredient(dish2ing3);
        dish2.addIngredient(dish2ing4);
        dish2.addIngredient(dish2ing5);
        dish2.addIngredient(dish2ing6);
        dish2.addIngredient(dish2ing7);
        dish2.addIngredient(dish2ing8);
        dish2.addIngredient(dish2ing9);
        dish2.addIngredient(dish2ing10);
        dish2.addIngredient(dish2ing11);
        dishes.add(dish2);

        for (int i = 0; i < 10; i++) {
            Dish dish3 = new Dish("Ice Cream " + i, Dish.DESSERT, "icecream.jpg", "Go out and buy some");
            dish3.addIngredient(new Ingredient("Heavy cream", 2, "cup", 2));
            dish3.addIngredient(new Ingredient("Whole Milk", 1, "cup", 6));
            dish3.addIngredient(new Ingredient("Sugar", 2.0 / 3.0, "cup", 1));
            dish3.addIngredient(new Ingredient("Vanilla extract", 1, "tsp", 1));
            dish3.addIngredient(new Ingredient("Vanilla Bean", 1, "ea", 2));
            dishes.add(dish3);
        }
        return dishes;
    }

}
