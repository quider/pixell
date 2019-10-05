package pl.quider.standalone.mainwindow;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@Slf4j
public class MainWindowController implements Initializable {

    @FXML
    private MenuBar menuBar;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.debug("Setting menu as system Menu Bar {}", true);
        menuBar.setUseSystemMenuBar(true);
    }
}
