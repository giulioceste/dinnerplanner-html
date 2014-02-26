//ExampleViewController Object constructor
var CurrentMenuViewController = function(view, model ) {

    // All listening classes that must implement
    // all the menu options controller listener functions.
    var listeners = [];

    // Add the listener.
    this.addListener = function(listener) {
        listeners.push(listener);
    }

    var notifyListeners = function() {
        for (var i = 0; i < listeners.length; i++) {
            listeners[i].dinnerConfirmed();
        }
    }

    view.confirmButton.click(function(){
        notifyListeners();
    });

    view.numberOfGuests.change(function(){
        model.setNumberOfGuests(view.numberOfGuests.val());
    });

};