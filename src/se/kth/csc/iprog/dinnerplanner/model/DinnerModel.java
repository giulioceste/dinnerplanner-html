package se.kth.csc.iprog.dinnerplanner.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class DinnerModel implements IDinnerModel{

    /**
     * Number of guests currently attending the dinner event.
     */
    private int numberOfGuests;

    /**
     * Holds the user's selected dishes.
     */
    private Dish[] selectedDishes;

    /**
     * All the available dishes for the user.
     */
    private final Set<Dish> dishes = new HashSet<Dish>();

    /**
     * Singleton instance of this.
     */
    private static DinnerModel instance;

    /**
     * Notifiers that the model has changed.
     */
    private final List<OnModelChangedListener> listeners;

    /**
     * Returns the single instance of the Dinner Model.
     *
     * @return The singleton instance of this class.
     */
    public static final DinnerModel getInstance() {
        if (instance == null) {
            instance = new DinnerModel();
        }
        return instance;
    }

    /**
     * TODO: For Lab2 you need to implement the IDinnerModel interface.
     * When you do this you will have all the needed fields and methods
     * for the dinner planner (number of guests, selected dishes, etc.).
     */

    /**
     * The constructor of the overall model. Set the default values here
     */
    private DinnerModel() {
        listeners = new ArrayList<OnModelChangedListener>();

        setNumberOfGuests(0);
        selectedDishes = new Dish[3];
        //Adding some example data, you can add more
        Dish dish1 = new Dish("French toast",Dish.STARTER,"toast.jpg","In a large mixing bowl, beat the eggs. Add the milk, brown sugar and nutmeg; stir well to combine. Soak bread slices in the egg mixture until saturated. Heat a lightly oiled griddle or frying pan over medium high heat. Brown slices on both sides, sprinkle with cinnamon and serve hot.");
        Ingredient dish1ing1 = new Ingredient("eggs",0.5,"",1);
        Ingredient dish1ing2 = new Ingredient("milk",30,"ml",6);
        Ingredient dish1ing3 = new Ingredient("brown sugar",7,"g",1);
        Ingredient dish1ing4 = new Ingredient("ground nutmeg",0.5,"g",12);
        Ingredient dish1ing5 = new Ingredient("white bread",2,"slices",2);
        dish1.addIngredient(dish1ing1);
        dish1.addIngredient(dish1ing2);
        dish1.addIngredient(dish1ing3);
        dish1.addIngredient(dish1ing4);
        dish1.addIngredient(dish1ing5);
        dishes.add(dish1);

        Dish dish2 = new Dish("Meat balls",Dish.MAIN,"meatballs.jpg","Preheat an oven to 400 degrees F (200 degrees C). Place the beef into a mixing bowl, and season with salt, onion, garlic salt, Italian seasoning, oregano, red pepper flakes, hot pepper sauce, and Worcestershire sauce; mix well. Add the milk, Parmesan cheese, and bread crumbs. Mix until evenly blended, then form into 1 1/2-inch meatballs, and place onto a baking sheet. Bake in the preheated oven until no longer pink in the center, 20 to 25 minutes.");
        Ingredient dish2ing1 = new Ingredient("extra lean ground beef",115,"g",20);
        Ingredient dish2ing2 = new Ingredient("sea salt",0.7,"g",3);
        Ingredient dish2ing3 = new Ingredient("small onion, diced",0.25,"",2);
        Ingredient dish2ing4 = new Ingredient("garlic salt",0.6,"g",3);
        Ingredient dish2ing5 = new Ingredient("Italian seasoning",0.3,"g",3);
        Ingredient dish2ing6 = new Ingredient("dried oregano",0.3,"g",3);
        Ingredient dish2ing7 = new Ingredient("crushed red pepper flakes",0.6,"g",3);
        Ingredient dish2ing8 = new Ingredient("Worcestershire sauce",16,"ml",7);
        Ingredient dish2ing9 = new Ingredient("milk",20,"ml",4);
        Ingredient dish2ing10 = new Ingredient("grated Parmesan cheese",5,"g",8);
        Ingredient dish2ing11 = new Ingredient("seasoned bread crumbs",115,"g",4);
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

        for(int i = 0 ; i < 30; i++)
        {
            Dish dish3 = new Dish("Ice Cream " + i, Dish.DESERT, "icecream.jpg", "Go out and buy some");
            Ingredient dish3ing9 = new Ingredient("milk",20,"ml",4);
            Ingredient dish3ing10 = new Ingredient("grated Parmesan cheese",5,"g",8);
            Ingredient dish3ing11 = new Ingredient("seasoned bread crumbs",115,"g",4);
            dish3.addIngredient(dish3ing9);
            dish3.addIngredient(dish3ing10);
            dish3.addIngredient(dish3ing11);
            dishes.add(dish3);
        }
    }

    /**
     * Returns the set of dishes of specific type. (1 = starter, 2 = main, 3 = desert).
     */
    public Set<Dish> getDishes(){
        return new HashSet<Dish>(dishes);
    }

    /**
     * Returns the set of dishes of specific type. (1 = starter, 2 = main, 3 = desert).
     */
    public Set<Dish> getDishesOfType(int type){
        Set<Dish> result = new HashSet<Dish>();
        for(Dish d : dishes){
            if(d.getType() == type){
                result.add(d);
            }
        }
        return result;
    }

    /**
     * Returns the set of dishes of specific type, that contain filter in their name
     * or name of any ingredient.
     */
    public Set<Dish> filterDishesOfType(int type, String filter){
        Set<Dish> result = new HashSet<Dish>();
        for(Dish d : dishes){
            if(d.getType() == type && d.contains(filter)){
                result.add(d);
            }
        }
        return result;
    }


    @Override
    public int getNumberOfGuests()
    {
        return numberOfGuests;
    }

    @Override
    public void setNumberOfGuests(int numberOfGuests) {
        if (numberOfGuests < 0) return; // Can't set to negative number
        if (numberOfGuests == this.numberOfGuests) return; // No Change.
        int oldVal =  this.numberOfGuests;
        this.numberOfGuests = numberOfGuests;
        for (OnModelChangedListener listener : listeners) {
            listener.onNumberOfGuestChanged(this.numberOfGuests, oldVal);
        }
    }

    @Override
    public Dish getSelectedDish(int type) {
        return selectedDishes[type - 1];
    }

    @Override
    public Set<Dish> getFullMenu() {
        Set<Dish> menuDishes = new HashSet<Dish>();
        for(Dish dish: selectedDishes) {
            // Ignore dishes that are not set yet.
            if (dish == null) continue;
            // Add the dish to the outgoing set of selected dishes.
            menuDishes.add(dish);
        }
        return menuDishes;
    }

    @Override
    public Set<Ingredient> getAllIngredients() {
        Set<Ingredient> allIngredients = new HashSet<Ingredient>();
        for(Dish d: getFullMenu())
            allIngredients.addAll(d.getIngredients());

        return allIngredients;
    }

    @Override
    public float getTotalMenuPrice() {
        double count = 0;
        for(Dish d: getFullMenu())
            count += d.getPrice();
        return (float)count;
    }

    @Override
    public void addToMenu(Dish dish) {
        if (dish == null) return;
        switch (dish.getType()) {
            case Dish.STARTER:
            case Dish.MAIN:
            case Dish.DESERT:
                Dish current = selectedDishes[dish.getType() - 1];
                if (current != null) {
                    // Check if the dish is already placed
                    if (dish.equals(current)) return;
                    // Remove dish from menu.
                    removeFromMenu(current);
                }
                selectedDishes[dish.getType() - 1] = dish;
                break;
            default:
                Logger.getGlobal().warning("Incompatible Dish type " + dish);
        }

        for (OnModelChangedListener listener: listeners) {
            listener.onDishAdded(dish);
        }
    }

    @Override
    public boolean removeFromMenu(Dish dish) {
        Set<Dish> selected = getFullMenu();
        if (!selected.contains(dish))
            return false;
        selectedDishes[dish.getType() - 1] = null;
        for (OnModelChangedListener listener: listeners) {
            listener.onDishRemoved(dish);
        }
        return true;
    }

    /**
     * Adds a listener to this.  The listener
     *
     * @param listener Listener to register with this listener
     */
    public void addListener(OnModelChangedListener listener) {
        if (listener == null) return;
        if (this.listeners.contains(listener)) return;
        this.listeners.add(listener);
    }

    /**
     * Remove the listener from this.
     *
     * @param listener Listener to remove.
     * @return Whether the listener was successfully removed.
     */
    public boolean removeListener(OnModelChangedListener listener) {
        return listeners.remove(listener);
    }

    /**
     * Notifies Model has changed
     */
    public interface OnModelChangedListener {

        /**
         * Notifies listener that a dish was added to the menu.
         *
         * @param added Dish that was added.
         */
        public void onDishAdded(Dish added);

        /**
         * Notifies listener that a dish was removed from the menu.
         *
         * @param removed The dish removed
         */
        public void onDishRemoved(Dish removed);

        /**
         * Notifies the number of guests attending the event has been changed.
         *
         * @param newAmount new amount of guest.
         * @param oldAmount old amount of guest.
         */
        public void onNumberOfGuestChanged(int newAmount, int oldAmount);

    }
}
