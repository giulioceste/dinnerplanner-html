//this view is for adding the selected dishes to the current menu items
var AddedDishView = function(container, dish) {
//    this.rootDiv = container.find("#addedDishDiv");//div that the dishes will be added
//    this.parentDiv = container.find("#overallView");//the root div

    //this div creates a row class. So that every added dish goes to the next line.
    var upperDiv = $("<div>");
    upperDiv.addClass("row");

    //this is the cancel button
    this.cancelButton = $("<button class='btn btn-default btn-danger btn-xs col-lg-2 col-md-2 " +
        "col-sm-2 col-xs-2'><span class='glyphicon glyphicon-remove'></span></button>");
    this.cancelButton.attr("id","" + dish.id);

    var nameDiv = $("<div>");
    nameDiv.addClass("col-lg-2 col-md-2 col-sm-2 col-xs-2");
    var addedDishName = $("<span>");
    addedDishName.attr("id","addedDishName");
    addedDishName.html(dish.id);
    nameDiv.append(addedDishName);

//    div.append(this.addedDishName);
//    upperDiv.append(div);
//    this.rootDiv.append(upperDiv);
//    this.parentDiv.append(this.rootDiv);

    var priceDiv = $("<div>");
    priceDiv.addClass("col-lg-8 col-md-8 col-sm-8 col-xs-8");
    var addedDishPrice = $("<span>");
    addedDishPrice.attr("id","addedDishPrice");
    addedDishPrice.html(dish.name);
    priceDiv.append(addedDishPrice);

//    div.append(this.addedDishPrice);
//    upperDiv.append(div);
//    upperDiv.append(this.cancelButton);
//    this.rootDiv.append(upperDiv);
//    this.parentDiv.append(this.rootDiv);

    upperDiv.append(nameDiv);
    upperDiv.append(priceDiv);
    upperDiv.append(this.cancelButton);
    container.append(upperDiv);
}

