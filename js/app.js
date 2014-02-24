
// Short hand for document ready.
$(function() {

    //The global variable so we can access it from other controller and views
    window.stage = "starter";

    // Obtain access
    var mainPane = $('#main-pane');
    var headerPane = $('#header-pane');

    //We instantiate our model
    var model = new DinnerModel();

    var introView = new IntroView($('#intro-pane'), model);
    var introController = new IntroController(introView, model);
    introController.addListener(this);

    var menuOptionsView = new MenuOptionsView($('#menu-options-view'), model);
    var menuOptionsController = new MenuOptionsController(menuOptionsView, model);
    menuOptionsController.addListener(this);

    var goBackTitleView = new GoBackTitleView($("#summary-header-pane"), model);
    var goBackTitleViewController = new SummaryController(goBackTitleView, model);


//    var dinnerOverview = new DinnerOverviewView($("#dinnerOverview"), model);
//    var dinnerOverviewController = new DinnerOverviewViewController(dinnerOverview, model);

    // groups of views.
    var headerGroup = ['#summary-header-pane'];
    var mainGroup = ['#dinnerOverview', '#intro-pane',
        '#preparation-pane', '#split-pane'];
    var leftGroup = ['#current-menu-pane'];
    var rightGroup = ['#dish-pane', '#menu-options-view'];

    var showFromGroup = function(view, showOrHide, viewGroup) {
        for (var v in viewGroup) {
            // Only show the view if
            // the input view is found and showOrHide is set to true.
            $(v).toggle(view == v && showOrHide);
        }
    }

    // Functions that handle showing views.
    var showFromHeaderGroup = showInGroup(headerGroup);
    var showFromMainGroup = showInGroup(mainGroup);
    var showFromLeftGroup = showInGroup(leftGroup);
    var showFromRightGroup = showInGroup(rightGroup);

    // Show the intro view.
    var showIntro = function() {
        showFromMainGroup('#intro-pane');
        showFromHeaderGroup('');
    }

    // Call this when the user selects to proceed pas the intro screen.
    var proceedAfterIntro = function() {
        // Hide the intro view and proceed to the next screen.
        $('.container').css('background-image', 'none');
        showFromMainGroup('#split-pane');
        showFromRightGroup('#menu-options-view');
        showFromLeftGroup('#current-menu-pane');
        showFromHeaderGroup('');
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    // Call backs from other controllers
    //////////////////////////////////////////////////////////////////////////////////////////

    // Menu Options Controller
    this.dishSelected = function(dish) {
        // Show the view for a specific dish.
        console.log('Dish selected for ' + dish.name);
    }

    // Summary View Controller
    this.backToEditMenu = function() {
        console.log('Back to edit dinner requested');

    }

    // Summary view controller.
    this.printMenu = function() {
        console.log('Print menu requested.');
        showFromMainGroup('#preparation-pane');
    }

    this.proceedPastIntro = function() {
        console.log('Intro page complete.');
        proceedAfterIntro();
    }



   showIntro();
});

// A function that
function showInGroup(viewGroup) {
    var group = viewGroup;
    function showOrHide(view) {
        for (var i = 0; i < group.length; i++) {
            // Only show the view if
            // the input view is found and showOrHide is set to true.
            $(group[i]).toggle(view == group[i]);
        }
    }
    return showOrHide;
}