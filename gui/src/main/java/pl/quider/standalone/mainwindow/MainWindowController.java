package pl.quider.standalone.mainwindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Slf4j
public class MainWindowController implements Initializable {

    @FXML
    private MenuBar menuBar;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtFolder;

    @FXML
    private ListView<?> list;

    @FXML
    private TabPane tabPane;

    @FXML
    private Button btOpen;

    @FXML
    void onOpenClick(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        final var file = directoryChooser.showDialog(menuBar.getScene().getWindow());
        final ExecutorService executorService = Executors.newCachedThreadPool();
        if (file != null && file.isDirectory()) {
            for (File listFile : file.listFiles(f -> true)) {
//                executorService.submit();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Nie został wybrany żaden katalog!", ButtonType.OK);
        }

    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        assert menuBar != null : "fx:id=\"menuBar\" was not injected: check your FXML file 'main.fxml'.";
        assert txtFolder != null : "fx:id=\"txtFolder\" was not injected: check your FXML file 'main.fxml'.";
        assert list != null : "fx:id=\"list\" was not injected: check your FXML file 'main.fxml'.";
        assert tabPane != null : "fx:id=\"tabPane\" was not injected: check your FXML file 'main.fxml'.";
        assert btOpen != null : "fx:id=\"btOpen\" was not injected: check your FXML file 'main.fxml'.";
        log.debug("Setting menu as system Menu Bar {}", true);
        menuBar.setUseSystemMenuBar(true);
    }
}
