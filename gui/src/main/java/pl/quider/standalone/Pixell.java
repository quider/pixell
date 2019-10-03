package pl.quider.standalone;


import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@ComponentScan("pl.quider.standalone")
@PropertySource("classpath:/application.yml")
@Configuration
public class Pixell extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Pixell.class);
        SpringFxmlLoader springFxmlLoader = (SpringFxmlLoader) context.getBean("springFxmlLoader");
        Parent load = (Parent) springFxmlLoader.load(getClass().getResource("/main.fxml"));
        stage.setTitle("Mozaika");
        stage.setScene(new Scene(load));
        stage.show();

        System.out.println(context.isRunning());
    }
}
