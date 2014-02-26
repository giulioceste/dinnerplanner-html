/**
 * Created by sandstroh on 2/21/14.
 */

var SummaryController = function(view, model) {

    // All listening classes that must implement
    // all the menu options controller listener functions.
    var listeners = [];

    // Add the listener.
    this.addListener = function(listener) {
        listeners.push(listener);
    }

    var notifyListeners = function() {
        for (var i = 0; i < listeners.length; i++) {
            listeners[i].onBackPressed();
        }
    }

    view.goBackButton.click(function() {
        notifyListeners();
    });
}