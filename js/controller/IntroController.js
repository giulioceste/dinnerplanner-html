/**
 * Created by mhotan_dev on 2/24/14.
 */

function IntroController(view, model) {



    // All listening classes that must implement
    // all the menu options controller listener functions.
    var listeners = [];

    // Add the listener.
    this.addListener = function(listener) {
        listeners.push(listener);
    }

    var notifyListeners = function() {
        for (var i = 0; i < listeners.length; i++) {
            listeners[i].proceedPastIntro();
        }
    }

    view.button.on('click', function() {
       notifyListeners();
    });

}