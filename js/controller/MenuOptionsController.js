/**
 * Controller for the Menu Options View.  Controls the user
 * select a specific group of dishes to view.
 *
 * Created by Michael Hotan on 2/23/14.
 */

var MenuOptionsController = function(view, model) {

    // Selector for type of dish.
    this.type = 'all';
    var thisController = this;

    // Populate the view with all the dishes.
    var populateView = function(type, filter) {
        // Empty out the portfolio.
        view.portfolio.empty();
        // Go through all the dishes in the model and add a view
        $.each(view.model.getAllDishes(type, filter), function(index, dish) {
            var dishView = new DishPortfolioView(view.portfolio, dish);
            var controller = new DishPortfolioController(dishView, dish);
            controller.addObserver(thisController);
        });
    }

    // Update the view with the current views.
    this.update = function() {
        var filter = view.filterField.val().trim();
        populateView(thisController.type, filter);
    }

    // Handle search button
    view.root.find('#filter-button').on('click', function() {
        thisController.update();
        console.log('Search button pressed');
    });

    // Handle enter in search bar.
    view.root.find('#filter').on('keyup', function(event) {
        event.preventDefault();
        console.log('Enter key pressed');
        // The key event is the enter key so process the current filter.
        if (event.keyCode != 13) {
            return false;
        }
        thisController.update();
    });

    // Add an onclick listener to every one of the
    view.root.find('.dropdown-menu').find('a').on('click', function(event) {
        event.preventDefault(); // Prevent the default action

        console.log('Clicked drop down item ' + $(this).data('value'));
        //
        thisController.type = $(this).data('value');
        // Set the button for the dropdown to the appropiate text
        view.root.find('#dishes-type-dropdown').html($(this).text() +
            "<span class='caret'></span>");
        thisController.update();
    });

    // All listening classes that must implement
    // all the menu options controller listener functions.
    var listeners = [];

    // Add the listener.
    this.addListener = function(listener) {
        listeners.push(listener);
    }

    var notifyListeners = function(dish) {
        for (var i = 0; i < listeners.length; i++) {
            listeners[i].dishSelected(dish);
        }
    }

    // This view
    this.dishSelected = function(dish) {
        // notify the listener for all.

        notifyListeners(dish);
    }

    // Initially populate the view.
    this.update();
}