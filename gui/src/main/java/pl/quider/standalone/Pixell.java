package pl.quider.standalone;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@ComponentScan("pl.quider.standalone")
@Configuration
public class Pixell extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Pixell.class);
        FXMLLoader load = new FXMLLoader();
        load.setLocation(getClass().getResource("/main.fxml"));
        stage.setTitle("Faktury");
        stage.setScene(new Scene(load.load()));
        stage.show();

        System.out.println(context.isRunning());
    }
}
