/**
 * Created by sandstroh on 2/20/14.
 */

var DinnerOverviewView = function(container, model) {

    this.starterDish = container.find("#starterDish");
    this.mainDish = container.find("#mainDish");
    this.dessertDish = container.find("#dessertDish");

    this.totalCost = container.find("#totalCost");

    this.printButton = container.find("#printButton");

    // Initialize the variables
    this.starterImage = $("<img>");
    this.starterName = $("<span>");
    this.starterPrice = $("<span>");
    this.starterDish.append(this.starterImage);
    this.starterDish.append(this.starterName);
    this.starterDish.parent().append(this.starterPrice);

    this.mainImage = $("<img>");
    this.mainName = $("<span>");
    this.mainPrice = $("<span>");
    this.mainDish.append(this.mainImage);
    this.mainDish.append(this.mainName);
    this.mainDish.parent().append(this.mainPrice);

    this.dessertImage = $("<img>");
    this.dessertName = $("<span>");
    this.dessertPrice = $("<span>");
    this.dessertDish.append(this.dessertImage);
    this.dessertDish.append(this.dessertName);
    this.dessertDish.parent().append(this.dessertPrice);

//    <!-- starter dish -->
//    var starter = model.getDish(model.getSelectedDish('starter'));
//    if (typeof starter == 'undefined') {
//        $(starterDish).hide();
//    } else {
//
//        this.starterImage.attr("src", "images/" + starter.image);
//
//
//        this.starterName.html(starter.name);
//
//
//        // TODO: price = costs for dish * number of guests
//        this.starterPrice.html("TODO" + " SEK");
//
//        this.starterDish.append(this.starterImage);
//        this.starterDish.append(this.starterName);
//        this.starterDish.parent().append(this.starterPrice);
//    }

//    <!-- main dish -->
//    var maindish = model.getDish(model.getSelectedDish('main dish'));
//    if (typeof maindish == 'undefined') {
//        $(mainDish).hide();
//    } else {
//        this.mainImage = $("<img>");
//        this.mainImage.attr("src", "images/" + maindish.image);
//
//        this.mainName = $("<span>");
//        this.mainName.html(maindish.name);
//
//        this.mainPrice = $("<span>");
//        this.mainPrice.html("TODO" + " SEK");
//
//        this.mainDish.append(this.mainImage);
//        this.mainDish.append(this.mainName);
//        this.mainDish.parent().append(this.mainPrice);
//    }
//
//    <!-- dessert dish -->
//    var dessert = model.getDish(model.getSelectedDish('dessert'));
//    if (typeof dessert == 'undefined') {
//        $(dessertDish).hide();
//    } else {
//        this.dessertImage = $("<img>");
//        this.dessertImage.attr("src", "images/" + dessert.image);
//
//        this.dessertName = $("<span>");
//        this.dessertName.html(dessert.name);
//
//        this.dessertPrice = $("<span>");
//        this.dessertPrice.html("TODO" + " SEK");
//
//        this.dessertDish.append(this.dessertImage);
//        this.dessertDish.append(this.dessertName);
//        this.dessertDish.parent().append(this.dessertPrice);
//    }

//    this.totalCost.html(model.getTotalMenuPrice() + " SEK");

    /*****************************************
     Observer implementation
     *****************************************/

    //Register an observer to the model
    model.addObserver(this);

    //This function gets called when there is a change at the model
    this.update = function(arg){

        <!-- starter dish -->
        var starter = model.getDish(model.getSelectedDish('starter'));
        if (typeof starter == 'undefined') {
            this.starterDish.hide();
        } else {
            this.starterImage.attr("src", "images/" + starter.image);
            this.starterName.html(starter.name);
            this.starterPrice.html(starter + " SEK");
        }

        <!-- main dish -->
        var maindish = model.getDish(model.getSelectedDish('main dish'));
        if (typeof maindish == 'undefined') {
            this.mainDish.hide();
        } else {
            this.mainImage.attr("src", "images/" + maindish.image);
            this.mainName.html(maindish.name);
            this.mainPrice.html(maindish.price + " SEK");
        }

        <!-- dessert dish -->
        var dessert = model.getDish(model.getSelectedDish('dessert'));
        if (typeof dessert == 'undefined') {
            this.dessertDish.hide();
        } else {
            this.dessertImage.attr("src", "images/" + dessert.image);
            this.dessertName.html(dessert.name);
            this.dessertPrice.html(dessert.price + " SEK");
        }

        this.totalCost.text(model.getTotalMenuPrice() + " SEK");
    }

    // Update the model.
    this.update(model); // Model is not used just used to match parameter signature
}