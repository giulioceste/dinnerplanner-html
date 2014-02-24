/**
 * A View class that presents a single dish presented in a portfolio.
 *
 * Created by Michael Hotan on 2/22/14.
 */

var DishPortfolioView = function(container, dish) {

    this.dish = dish;
    this.root = $("<div>");
    // Add all the necessary classes to layout this view correctly.
    this.root.addClass("col-lg-3 col-md-4 col-sm-6 col-xs-12 mix web-design mix_all portfolio-root");

    // The containing Bootstrap panel class.
    var panel = $("<div>");
    panel.addClass("panel panel-default item");
    this.root.append(panel);

    // The header of the panel class.
    var panelHeading = $("<div>");
    panelHeading.addClass("panel-heading");
    panelHeading.appendTo(panel);

    // Create a reference for the link.
    var ref = $("<a href='#'></a>");
    ref.addClass('dp-link dp-image');
    // Add the reference to the panel header.
    ref.appendTo(panelHeading);

    // Image to add to the link image
    var image = $("<img class='img-responsive item-img' src='images/" +
        this.dish.image + "' alt='Work 1'>");
    image.appendTo(ref);

    // The body text of the items.
    var panelBody = $("<div>");
    panelBody.addClass("panel-body");
    panelBody.appendTo(panel);

    // Create the header of the dish.
    var ref2 = $("<a href='#'></a>");
    ref2.addClass('dp-link dp-label');
    var titleHeader = $('<h4>');
    titleHeader.html(this.dish.name);
    titleHeader.appendTo(ref2);
    ref2.appendTo(panelBody);

    // Create the block for the description.
    var description = $("<p>");
    description.addClass("item-description");
    description.html(this.dish.type);
    description.appendTo(panelBody);

    //    Append this view to the container.
    container.append(this.root);

    // Hides this view.
    this.hide = function() {
        this.root.hide();
    }

    // Shows the view.
    this.show = function() {
        this.root.show();
    }
}

