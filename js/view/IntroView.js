/**
 * Created by Michael Hotan on 2/24/14.
 */

function IntroView(container, model) {

    this.button = container.find('#startButton');

    container.find('#welcomeView').find('p').first().text('Welcome to the Dinner Planner, the most wickidly awesome dinner' +
        ' planning experience you will ever had.  Do you like to entertain your guest with excitement and danger?' +
        ' Then you came to the right place! Press the button below to start your dinner planning experience.');

}