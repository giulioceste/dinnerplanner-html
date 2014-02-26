/**
 * Created by Michael Hotan on 2/24/14.
 */
var DishView = function(container, dish, model) {

    // JQuery object references values
    var dishProperties = container.find('.dish');
    var name = dishProperties.find('.name');
    var image = dishProperties.find('.image');
    var details = dishProperties.find('.details');
    var table = container.find('table');
    var preparation = container.find('.preparation');

    // Back button
    this.backButton = container.find('#back-button');
    // Confirm button
    this.confirmButton = container.find('#confirm-dish-button');

    //Register an observer to the model
    model.addObserver(this);


 //This function gets called when there is a change at the model
    this.update = function(arg) {
        var numGuest = model.getNumberOfGuests();
        document.getElementById("peopleChanging").innerHTML= numGuest;
        // TODO update the number of guests in ingredients

        $.each(dish.ingredients, function(index, ingredient) {
            var row = $('<tr>');

            var cell = $('<td>');
            cell.addClass('')

        });

    }

    // Set the immutable fields.
    name.html(dish.name);
    image.data('src', 'images/' + dish.image);
    details.html(dish.type);
    preparation.html(dish.description);
}