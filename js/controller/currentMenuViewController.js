//ExampleViewController Object constructor
var CurrentMenuViewController = function(view, model ) {

    view.myButton.click(function(){
        window.alert("sometext");
    });

    view.numberOfGuests.change(function(){
        window.alert("input changed: ");
    });
}