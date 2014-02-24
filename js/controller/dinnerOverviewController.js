/**
 * Created by sandstroh on 2/20/14.
 */

var DinnerOverviewViewController = function(view, model) {

    view.printButton.click(function() {
        window.alert("Printing...");
    });

    // All listening classes that must implement
    // all the menu options controller listener functions.
    var listeners = [];

    // Add the listener.
    this.addListener = function(listener) {
        listeners.push(listener);
    }

    var notifyListeners = function(func) {
        for (var i = 0; i < listeners.length; i++) {
            listeners[i][func]();
        }
    }

    var printMenu = function() {
        notifyListeners('printMenu');
    }

}