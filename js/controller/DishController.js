/**
 * Created by mhotan on 2/26/14.
 */

var DishController = function(view, dish, model) {

    // All listening classes that must implement
    // all the menu options controller listener functions.
    var listeners = [];

    // Add the listener.
    this.addListener = function(listener) {
        listeners.push(listener);
    }

    this.removeListener = function(listener) {
        listeners = $.grep(listeners, function(value) {
            return value != listener;
        });
    }

    var notifyBackPressed = function() {
        for (var i = 0; i < listeners.length; i++) {
            listeners[i].onBackPressed();
        }
    }

    view.backButton.on('click', function() {
        notifyBackPressed()
    });

    view.confirmButton.on('click', function() {
        model.addDishToMenu(dish.id);
        notifyBackPressed();
    });

}