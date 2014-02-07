package loader;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;

/**
 * Created by halitanil on 07.02.2014.
 */
public final class ViewLoader {

    public static void load(Node node, String fileName, Object controller) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(node.getClass().getResource(
                fileName));
        fxmlLoader.setRoot(node);
        fxmlLoader.setController(controller);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
