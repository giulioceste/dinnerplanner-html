var CurrentMenuView;
CurrentMenuView = function (container, model) {

    // This is the only button that we need to expose to the controller
    this.confirmButton = container.find("#confirmButton");

    this.numberOfGuests = container.find("#numberOfGuest");
    var pendingPrice = container.find("#pendingCost");
    var totalCost = container.find('#totalCost');
    var dishDiv = container.find('#addedDishDiv');

    // Register this view as an observer with the model
    model.addObserver(this);

    //This function gets called when there is a change at the model
    this.update = function (arg) {

        // Add all the dishes
        dishDiv.empty();
        $.each(model.getFullMenu(), function(index, dish) {
            if (dish != undefined && dish != null) {
                var dishView = new AddedDishView(dishDiv, dish);
                var dishController = new AddedDishController(dishView, dish, model);
            }
        });

        // Set the total cost
        totalCost.text('SEK ' + model.getTotalMenuPrice());

        // Set the number of guests
        this.numberOfGuests.val(model.getNumberOfGuests());
    }

    this.update(model);
};