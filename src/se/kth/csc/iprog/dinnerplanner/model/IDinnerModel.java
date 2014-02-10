package se.kth.csc.iprog.dinnerplanner.model;

import java.util.Set;

/**
 *
 */
public interface IDinnerModel {

    /**
     *
     * @return
     */
	public int getNumberOfGuests();

    /**
     *
     *
     * @param numberOfGuests Number of guests invited to the event.
     */
	public void setNumberOfGuests(int numberOfGuests);
	
	/**
	 * Returns the dish that is on the menu for selected type (1 = starter, 2 = main, 3 = desert).
	 */
	public Dish getSelectedDish(int type);
	
	/**
     * The full menu consists of everything the user has selected.  This method
     * returns a copy of the set containing the full menu.
     *
	 * @return all the dishes on the menu.
	 */
	public Set<Dish> getFullMenu();
	
	/**
	 * Returns all ingredients for all the dishes on the menu.
	 */
	public Set<Ingredient> getAllIngredients();
	
	/**
	 * Returns the total price of the menu (all the ingredients multiplied by number of guests).
	 */
	public float getTotalMenuPrice();

    /**
     * Adds a dish to the full menu.  Null values are ignored.
     *
     * @param dish Dish to add to menu
     */
    public void addToMenu(Dish dish);
}
