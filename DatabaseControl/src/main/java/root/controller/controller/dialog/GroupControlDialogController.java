package root.controller.controller.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import root.controller.controller.MainController;
import root.database.model.Group;
import root.database.model.SportType;
import root.database.service.GroupService;
import root.database.service.SportTypeService;

public class GroupControlDialogController {


    @FXML
    private TextField nameTextField;

    @FXML
    private ComboBox<SportType> sportTypeComboBox;

    private GroupService groupService;
    private SportTypeService sportTypeService;

    private Group group;
    private MainController mainController;

    @FXML
    public void initialize() {
        groupService = new GroupService();
        sportTypeService = new SportTypeService();

        for (var sportType : sportTypeService.findAll()) {
            sportTypeComboBox.getItems().add(sportType);
        }
    }

    private void close() {
        Stage thisStage = (Stage) nameTextField.getScene().getWindow();
        thisStage.close();
    }

    public void setGroup(Group group) {
        this.group = group;
        nameTextField.setText(group.getName());
        sportTypeComboBox.getSelectionModel().select(group.getSportType());
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
            if (group == null) {
                group = new Group();
            }
            group.setName(nameTextField.getText());
            group.setSportType(sportTypeComboBox.getSelectionModel().getSelectedItem());
            groupService.update(group);
            mainController.refresh();
            close();
        }
    }

}
