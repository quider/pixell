package pl.quider.standalone.mainwindow;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MainWindowController implements Initializable {

    @FXML
    private MenuBar menuBar;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuBar.setUseSystemMenuBar(true);
    }
}
