package loader;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

/**
 * Created by halitanil on 07.02.2014.
 */
public final class ViewLoader {

    /**
     * Loads an fxml document for a given view.
     *
     * @param node The view class that is loading the fxml document
     * @param fileName name of the file to upload
     * @param controller Controller class that has all internal members and implements all the callback methods.
     * @throws IOException
     */
    public static void load(Node node, String fileName, Object controller) throws IOException {
        URL url = ViewLoader.class.getClassLoader().getResource("layouts/" + fileName);
        if (url == null) {
            String error = "Unable to find view " + fileName;
            Logger.getGlobal().severe(error);
            throw new FileNotFoundException(error);
        }

        FXMLLoader fxmlLoader = new FXMLLoader(url);
        fxmlLoader.setRoot(node);
        fxmlLoader.setController(controller);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            Logger.getGlobal().severe("Unable to load view " + fileName + " due to " + exception.getMessage());
            throw new RuntimeException(exception);
        }
    }

}
