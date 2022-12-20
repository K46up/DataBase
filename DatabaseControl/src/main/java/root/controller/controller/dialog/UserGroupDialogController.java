package root.controller.controller.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import root.controller.controller.MainController;
import root.database.model.UserGroup;
import root.database.service.UserGroupService;

public class UserGroupDialogController {
    @FXML
    private TextField nameTextField;

    private UserGroup userGroup;
    private MainController mainController;

    private UserGroupService userGroupService;

    @FXML
    public void initialize() {
        userGroupService = new UserGroupService();
    }

    private void close() {
        Stage thisStage = (Stage) nameTextField.getScene().getWindow();
        thisStage.close();
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
        nameTextField.setText(userGroup.getName());
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
            if (userGroup == null) {
                userGroup = new UserGroup();
            }
            userGroup.setName(nameTextField.getText());
            userGroupService.update(userGroup);
            mainController.refresh();
            close();
        }
    }
}
