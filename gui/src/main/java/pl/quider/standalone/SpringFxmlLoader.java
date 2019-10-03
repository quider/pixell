package pl.quider.standalone;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

@Component
public class SpringFxmlLoader {

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * Gets Parent scene as {@link FXMLLoader} instance
     *
     * @param url resource url
     * @return parent object (control) or exception thrown
     */
    public Object load(URL url) {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(clazz -> applicationContext.getBean(clazz));
        loader.setLocation(url);
        //todo: findout
//        loader.setResources(ResourceBundle.getBundle(resources));
        try {
            return loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void closeWindowOnButtonEvent(Button button) {
        Window window = button.getScene().getWindow();
        ((Stage) window).close();
        System.gc();
    }

    public void openWindow(String fileName, String title, Modality modality, StageStyle style) {
        Parent window = (Parent) load(getClass().getResource(fileName));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        stage.setTitle(title);
        stage.setScene(new Scene(window));
        stage.show();
    }
}

