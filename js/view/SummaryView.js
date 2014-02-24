/**
 * Created by sandstroh on 2/21/14.
 */
var GoBackTitleView = function(container, model) {

    this.numberOfGuests = container.find("#numberOfGuests");
    this.goBackButton = container.find("#goBackButton");

    //Set the initial values of the components
    this.numberOfGuests.html(model.getNumberOfGuests());

    // Populate the HTML document dynamically.

    /*****************************************
     Observer implementation
     *****************************************/

    // Register an observer to the model
    model.addObserver(this);

    // This function gets called when there is a change at the model
    this.update = function(arg){
        this.numberOfGuests.html(model.getNumberOfGuests() + " people");
    }

    // Hides this view.
    this.hide = function() {
        container.hide();
    }

    // Shows the view.
    this.show = function() {
        container.show();
    }

}