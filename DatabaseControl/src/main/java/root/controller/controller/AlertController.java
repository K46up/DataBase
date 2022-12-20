package root.controller.controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class AlertController {

    @FXML
    private Text label;

    public void setMessage(String message) {
        label.setText(message);
    }

    

}
