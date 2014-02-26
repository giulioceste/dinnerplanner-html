var AddedDishController = function(view, dish, model ) {

    // Cancel button removes the dish from the model.
    view.cancelButton.click(function(){
        model.removeDishFromMenu(dish.id);
    });
}