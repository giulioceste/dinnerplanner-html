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


   var box = $("<div>");
     // Add all the necessary classes to layout this view correctly.
     box.style.width="960px";

     // <h1 id="title" class="section-title">Lasagne</h1>
     var recipeHeading = $("<h1>");
     recipeHeading.addClass("section-title")
     recipeHeading.html("Lasagne");
     box.append(recipeHeading);

     // <div class="row">
     var recipeBody = $("<div>");
     recipeBody.addClass("row");
     recipeBody.appendTo(box);

     // <div id="dishIngredients" class="col-xs-6">
     var dishDescription = $("<div>");
     dishDescription.addClass("col-xs-6");
     dishDescription.attr("id","dishDescription");
     dishDescription.appendTo(recipeBody);

     // <div id="dishIngredients" class="col-xs-6">
     var dishIngredients = $("<div>");
     dishIngredients.addClass("col-xs-6");
     dishIngredients.attr("id","dishIngredients");
     dishIngredients.appendTo(recipeBody);

     // <div id="dishPreparation">
     var dishPreparation = $("<div>");
     dishPreparation.attr("id","dishPreparation");
     box.append(dishPreparation);

    //This function gets called when there is a change at the model
    this.update = function(arg) {
        var numGuest = model.getNumberOfGuests();
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