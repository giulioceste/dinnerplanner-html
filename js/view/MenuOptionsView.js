/**
 * A view that shows the dishes in a portfolio layout.  Also allows the ability
 * to search.
 *
 * Created by Michael Hotan on 2/23/14.
 */
var MenuOptionsView = function(container, model) {

    // Declare all the member fields

    // Obtain access to the root
    this.root = container;
    // Create our own reference to the model and container
    this.model = model;
    this.portfolio = container.find('#dish-portfolio');
    this.filterField = container.find('#filter');


}

