package loader;

import javafx.scene.image.Image;

/**
 * Created by halitanil on 08.02.2014.
 */
public class ImageLoader {

    private static final ClassLoader instance = new ImageLoader().getClass().getClassLoader();

    public static Image load(String fileName) {
        return new Image(instance.getResourceAsStream("images/" + fileName));
    }
}
