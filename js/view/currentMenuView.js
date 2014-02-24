var CurrentMenuView;
CurrentMenuView = function (container, model) {

    this.myButton = container.find("#testbutton");
    this.numberOfGuests = container.find("#numberOfGuest");

    //Register an observer to the model
    model.addObserver(this);

    //This function gets called when there is a change at the model
    this.update = function (arg) {
        this.numberOfGuests.html(model.getNumberOfGuests());
    }

    this.update(model);
};