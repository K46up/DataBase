package root.controller.controller.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import root.controller.controller.MainController;
import root.database.model.SportType;
import root.database.service.SportTypeService;

public class SportTypeControlDialogController {


    @FXML
    private TextField nameTextField;


    private SportType sportType;
    private MainController mainController;
    private SportTypeService sportTypeService;

    @FXML
    public void initialize() {
        sportTypeService = new SportTypeService();
    }

    private void close() {
        Stage thisStage = (Stage) nameTextField.getScene().getWindow();
        thisStage.close();
    }

    public void setSportType(SportType sportType) {
        this.sportType = sportType;
        nameTextField.setText(sportType.getName());
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    private boolean validateInput() {
        return nameTextField.getText().length() != 0;
    }

    @FXML
    public void save() {
        if (validateInput()) {
            if (sportType == null) {
                sportType = new SportType();
            }
            sportType.setName(nameTextField.getText());
            sportTypeService.update(sportType);
            mainController.refresh();
            close();
        }
    }

}
