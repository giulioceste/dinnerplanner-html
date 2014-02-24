/**
 * Created by mhotan_dev on 2/22/14.
 */

function DishPortfolioController(view, dish) {

    // Register the view with the onclick listener
    // Use our special link class as a filter.
    view.root.on('click', '.dp-link', onDishSelected);

    // The function that handles the callback.
    function onDishSelected(event) {
        event.preventDefault();
        // Notify any observers that the specific dish wants to be selected.
        notifyObservers(dish);
    }

    /*****************************************
     Observable implementation for when a dish is selected
     *****************************************/

    var observers = [];

    this.addObserver = function(observer)
    {
        observers.push(observer);
    }

    var notifyObservers = function(arg) {
        for(var i=0; i<observers.length; i++) {
            observers[i].dishSelected(arg);
        }
    }
}