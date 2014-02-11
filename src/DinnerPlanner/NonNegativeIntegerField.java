package DinnerPlanner;

import javafx.scene.control.TextField;

/**
 * Created by mhotan_dev on 2/11/14.
 */
public class NonNegativeIntegerField extends TextField {

    @Override public void replaceText(int start, int end, String text) {
        // If the replaced text would end up being invalid, then simply
        // ignore this call!
        if (!text.matches("[0-9]*")) {
            super.replaceText(start, end, text);
        }
    }

    @Override public void replaceSelection(String text) {
        if (!text.matches("[0-9]*")) {
            super.replaceSelection(text);
        }
    }

}
