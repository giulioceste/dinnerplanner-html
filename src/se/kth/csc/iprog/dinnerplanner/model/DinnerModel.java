package se.kth.csc.iprog.dinnerplanner.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.util.*;

public class DinnerModel implements IDinnerModel{

    /**
     * Number of guests currently attending the dinner event.
     */
    private final IntegerProperty numberOfGuests;

    /**
     * Mapping between dish type and the actual selected dishes.
     */
    private final ObservableMap<Integer, Dish> selectedDishes;

    /**
     * All the available dishes for the user.
     */
    private final Set<Dish> dishes = new HashSet<Dish>();

    /**
     * Singleton instance of this.
     */
    private static DinnerModel instance;

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
     * The constructor of the overall model. Set the default values here
     */
    private DinnerModel() {
//        listeners = new ArrayList<OnModelChangedListener>();

        // Number of guest should be observable
        numberOfGuests = new SimpleIntegerProperty(1);
        // Create an observable mapping between selected dishes and
        selectedDishes = FXCollections.observableMap(new HashMap<Integer, Dish>());

        // Return all the mock dishes.
        dishes.addAll(MockData.getMockDishes());
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

    /**
     * @return The read only integer property representing the number of guests.
     */
    public ReadOnlyIntegerProperty numberOfGuestsProperty() {
        return numberOfGuests;
    }

    /**
     * @return Number of guests.
     */
    public int getNumberOfGuests() {
        return numberOfGuests.get();
    }

    public ObservableMap<Integer, Dish> getSelectedDishes() {
        return selectedDishes;
    }

    @Override
    public void setNumberOfGuests(int numberOfGuests) {
        if (numberOfGuests < 1) return; // Can't set to negative number
        if (numberOfGuests == getNumberOfGuests()) return; // No Change.
        this.numberOfGuests.setValue(numberOfGuests);
    }

    @Override
    public Dish getSelectedDish(int type) {
        return selectedDishes.get(type);
    }

    @Override
    public Set<Dish> getFullMenu() {
        return new HashSet<Dish>(selectedDishes.values());
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
        selectedDishes.put(dish.getType(), dish);
    }

    @Override
    public boolean removeFromMenu(Dish dish) {
        if (!selectedDishes.containsValue(dish)) return false;
        selectedDishes.remove(dish.getType());
        return true;
    }

}
